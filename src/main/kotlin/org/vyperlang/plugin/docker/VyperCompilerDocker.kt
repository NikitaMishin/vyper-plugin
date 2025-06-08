package org.vyperlang.plugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.exception.DockerException
import com.github.dockerjava.api.model.*
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperMessageProcessor
import javax.swing.event.HyperlinkEvent

/**
 * status code of execution
 */
enum class StatusDocker {
    EMPTY_OUTPUT,
    SUCCESS,
    FAILED,
}

/**
 *  represents results of execution
 */
data class ToolResult(val stdout: String, val stderr: String, val file: VirtualFile, val statusDocker: StatusDocker)

private const val DOCKER_IMAGE_NOT_FOUND_HTML = "<html>Image is not found in your system. " +
        "It may take a while to download it. " +
        "Please wait</html>"

val LOG: Logger = Logger.getInstance(VyperCompilerDocker::class.java)
const val IMAGE = "vyperlang/vyper"
const val PROJECT_BIND_DIR = "/app/project"

/**
 * Vyper compiler that runs inside docker container
 */
class VyperCompilerDocker(
    private val project: Project,
    private val file: VirtualFile,
    version: SemVer?,
    private val indicator: ProgressIndicator?,
    vararg args: String
) {
    private val args = arrayOf(*args)
    private val imageTag = version?.toString() ?: "latest"

    /**
     * preferred way to run docker within a project
     * handles downloading image if not found
     * this function handle docker exceptions and notify uses about possible solution
     * todo #15: add timeout, use long-running containers for performance
     */
    fun run(): ToolResult =
        try {
            if (!hasImage()) {
                downloadImage()
            }
            runContainer()
        } catch (e: DockerException) {
            throw CompilerMissingError(e)
        } catch (e: UnsatisfiedLinkError) {
            throw CompilerMissingError(e)
        } catch (e: InterruptedException) {
            throw CompilerMissingError(e)
        }

    private fun hasImage() =
        PluginDockerClient.listImagesCmd().exec().any { it.repoTags.any { tag -> tag.contains("$IMAGE:$imageTag") } }

    private fun downloadImage(): ResultCallback.Adapter<PullResponseItem>? {
        VyperMessageProcessor.notificateInBalloon(
            VyperMessageProcessor.VyperNotification(
                null, "Docker",
                DOCKER_IMAGE_NOT_FOUND_HTML,
                VyperMessageProcessor.NotificationStatusVyper.INFO,
                VyperMessageProcessor.NotificationGroupVyper.COMMON,
                project
            )
        )
        LOG.info("Pulling docker image $IMAGE:$imageTag")
        return PluginDockerClient.pullImageCmd(IMAGE)
            .withTag(imageTag)
            .exec(VyperPullImageAdapter(indicator))
            .awaitCompletion()
    }

    private fun runContainer(): ToolResult {
        indicator?.text = "Running Vyper compiler"
        indicator?.isIndeterminate = true

        val containerId = PluginDockerClient
            .createContainerCmd("$IMAGE:$imageTag")
            .withHostConfig(
                HostConfig().withBinds(Bind(project.basePath, Volume(PROJECT_BIND_DIR)))
            )
            .withCmd(*this.args, file.path.replace(project.basePath!!, PROJECT_BIND_DIR))
            .withWorkingDir(PROJECT_BIND_DIR)
            .exec()
            .id

        PluginDockerClient.startContainerCmd(containerId).exec()
        LOG.info("Container $containerId started with $IMAGE:$imageTag")

        val frames = VyperFrameStreamAdapter()
        PluginDockerClient
            .logContainerCmd(containerId)
            .withStdErr(true)
            .withStdOut(true)
            .withFollowStream(true)
            .exec(frames)
            .awaitCompletion()

        PluginDockerClient
            .removeContainerCmd(containerId)
            .exec()

        val status = when {
            frames.errors.isNotEmpty() -> StatusDocker.FAILED
            frames.logs.isNotEmpty() -> StatusDocker.SUCCESS
            else -> StatusDocker.EMPTY_OUTPUT
        }
        return ToolResult(frames.logs, frames.errors, file, status)
    }
}

private class VyperFrameStreamAdapter : ResultCallback.Adapter<Frame>() {
    private val streams = HashMap<StreamType, StringBuilder>()
    val errors: String get() = streams[StreamType.STDERR]?.toString() ?: ""
    val logs: String get() = streams[StreamType.STDOUT]?.toString() ?: ""
    override fun onNext(item: Frame) {
        val builder = streams.computeIfAbsent(item.streamType) { StringBuilder() }
        builder.append(String(item.payload))
    }
}

private class VyperPullImageAdapter(private val indicator: ProgressIndicator?) : ResultCallback.Adapter<PullResponseItem>() {
    /**
     * Map of layer id to the `current` and `total` progress of that layer.
     * As the layers get known, the total progress can go backwards a bit, but it's still quite helpful to have.
     */
    private val layerStatus = hashMapOf<String, Pair<Double, Double>>()

    override fun onNext(item: PullResponseItem) {
        if (indicator != null) {
            val current = item.progressDetail?.current?.toDouble()
            val total = item.progressDetail?.total?.toDouble()
            val id = item.id
            if (current != null && total != null && id != null) {
                layerStatus[id] = current to total
            }
            indicator.isIndeterminate = layerStatus.isEmpty()
            indicator.text = "Downloading docker image"
            if (layerStatus.isNotEmpty()) {
                indicator.fraction = layerStatus.values.sumOf { it.first } / layerStatus.values.sumOf { it.second }
            }
        }
        super.onNext(item)
    }
}

class CompilerMissingError(baseError: Throwable) : Exception(baseError) {
    companion object {
        const val ERROR_HTML = "<html>Error running docker.\n" +
                " Do you install <a href=\"https://docs.docker.com/install/\">docker</a>" +
                " and setup docker to run without sudo/root privilege?\n" +
                "For linux, setup docker-group.</html>"
    }

    fun notify(project: Project) = VyperMessageProcessor.VyperNotification(
        { _, hyperlinkEvent ->
            if (hyperlinkEvent.eventType == HyperlinkEvent.EventType.ACTIVATED)
                BrowserUtil.browse(hyperlinkEvent.url)
        },
        "Docker",
        ERROR_HTML,
        VyperMessageProcessor.NotificationStatusVyper.WARNING,
        VyperMessageProcessor.NotificationGroupVyper.COMMON,
        project
    )
}
