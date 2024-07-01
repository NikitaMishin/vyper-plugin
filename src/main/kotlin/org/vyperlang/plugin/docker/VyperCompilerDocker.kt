package org.vyperlang.plugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.exception.DockerException
import com.github.dockerjava.api.model.*
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
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
data class ToolResult(val stdout: List<String>, val stderr: List<String>, val file: VirtualFile, val statusDocker: StatusDocker)

private const val DOCKER_ERROR_HTML = "<html>Error running docker.\n" +
        " Do you install <a href=\"https://docs.docker.com/install/\">docker</a>" +
        " and setup docker to run without sudo/root privilege?\n" +
        "For linux, setup docker-group.</html>"

private const val DOCKER_IMAGE_NOT_FOUND_HTML = "<html>Image is not found in your system. " +
        "It may take a while to download it. " +
        "Please wait</html>"

/**
 * Vyper compiler that runs inside docker container
 */
class VyperCompilerDocker(
    private val project: Project,
    private val file: VirtualFile,
    private val indicator: ProgressIndicator,
    vararg args: String
) {
    private val args = arrayOf(*args)
    private val image = "vyperlang/vyper"
    private val imageTag = "0.3.10"
    private val dockerBindDir = "/app/s"

    /**
     * preferred way to run docker within a project
     * handles downloading image if not found
     * this function handle docker exceptions and notify uses about possible solution
     * TODO add timeout, use long-running containers for performance
     */
    fun run(): ToolResult? =
        try {
            if (!hasImage()) {
                downloadImage()
            }
            runContainer()
        } catch (dockerException: DockerException) {
            notify(project)
            null
        } catch (e: InterruptedException) {
            notify(project)
            null
        }

    private fun notify(project: Project) = VyperMessageProcessor.notificateInBalloon(
        VyperMessageProcessor.VyperNotification(
            { _, hyperlinkEvent ->
                if (hyperlinkEvent.eventType == HyperlinkEvent.EventType.ACTIVATED)
                    BrowserUtil.browse(hyperlinkEvent.url)
            }, "Docker",
            DOCKER_ERROR_HTML,
            VyperMessageProcessor.NotificationStatusVyper.WARNING,
            VyperMessageProcessor.NotificationGroupVyper.COMMON,
            project
        )
    )

    private fun hasImage() =
        PluginDockerClient.listImagesCmd().exec().any { it.repoTags.any { k -> k.contains(image, true) } }

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
        return PluginDockerClient.pullImageCmd(image)
            .withTag(imageTag)
            .exec(VyperPullImageAdapter(indicator))
            .awaitCompletion()
    }

    private fun runContainer(): ToolResult {
        indicator.text = "Running Vyper compiler"
        indicator.isIndeterminate = true

        val containerId = PluginDockerClient
            .createContainerCmd("$image:$imageTag")
            .withHostConfig(
                HostConfig().withBinds(Bind(file.parent.path, Volume(dockerBindDir)))
            )
            .withCmd(*this.args, file.name)
            .withWorkingDir(dockerBindDir)
            .exec()
            .id

        PluginDockerClient.startContainerCmd(containerId).exec()

        val frames = VyperFramesAdapter()
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

private class VyperFramesAdapter : ResultCallback.Adapter<Frame>() {
    val errors = mutableListOf<String>()
    val logs = mutableListOf<String>()
    override fun onNext(item: Frame) {
        when (item.streamType) {
            StreamType.STDERR -> errors
            StreamType.STDOUT -> logs
            else -> throw IllegalStateException("Unknown stream type " + item.streamType)
        }.add(String(item.payload).trim())
    }
}

private class VyperPullImageAdapter(private val indicator: ProgressIndicator) : ResultCallback.Adapter<PullResponseItem>() {
    /**
     * Map of layer id to the `current` and `total` progress of that layer.
     * As the layers get known, the total progress can go backwards a bit, but it's still quite helpful to have.
     */
    private val layerStatus = hashMapOf<String, Pair<Double, Double>>()

    override fun onNext(item: PullResponseItem) {
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
        super.onNext(item)
    }
}
