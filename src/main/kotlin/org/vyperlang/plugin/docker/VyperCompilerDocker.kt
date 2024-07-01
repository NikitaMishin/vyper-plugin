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
    INTERNAL_ERROR
}

/**
 *  represents results of execution
 */
data class ToolResult(val stdout: String, val stderr: String, val filename: String, val statusDocker: StatusDocker)

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
    fun run(): ToolResult =
        try {
            if (!hasImage()) {
                downloadImage()
            }
            runContainer()
        } catch (dockerException: DockerException) {
            notify(project)
            ToolResult("", "", "", StatusDocker.INTERNAL_ERROR)
        } catch (e: InterruptedException) {
            notify(project)
            ToolResult("", "", "", StatusDocker.INTERNAL_ERROR)
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

    //TODO make progress bar or notificate user about that
    private fun downloadImage(): ResultCallback.Adapter<PullResponseItem>? {
        indicator.text = "Downloading docker image"
        indicator.start()
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

        val logs = VyperAdapterLogs()
        PluginDockerClient
            .logContainerCmd(containerId)
            .withStdErr(true)
            .withStdOut(true)
            .withFollowStream(true)
            .exec(logs)
            .awaitCompletion()

        PluginDockerClient
            .removeContainerCmd(containerId)
            .exec()

        return when {
            logs.errors.isEmpty() && logs.out.isNotEmpty() -> ToolResult(
                logs.out.joinToString(separator = "\n"),
                "",
                file.path,
                StatusDocker.SUCCESS
            )

            logs.errors.isEmpty() -> ToolResult("", "", file.path, StatusDocker.EMPTY_OUTPUT)
            else -> ToolResult("", logs.errors.joinToString(separator = "\n"), file.path, StatusDocker.FAILED)
        }
    }
}

private class VyperAdapterLogs : ResultCallback.Adapter<Frame>() {
    private val items = mutableListOf<String>()
    override fun onNext(item: Frame) {
        items.add(item.toString())
    }

    val errors: List<String> get() = items.filter { it.contains("stderr", true) }.map { it.removePrefix("STDERR: ") }
    val out: List<String> get() = items.filter { it.contains("stdout", true) }.map { it.removePrefix("STDOUT: ") }
}

private class VyperPullImageAdapter(private val indicator: ProgressIndicator) : ResultCallback.Adapter<PullResponseItem>() {
    override fun onNext(item: PullResponseItem) {
        indicator.fraction = item.progressDetail?.current?.toDouble() ?: 0.0
        super.onNext(item)
    }
}
