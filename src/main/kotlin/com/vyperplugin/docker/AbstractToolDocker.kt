package com.vyperplugin.docker

import com.intellij.ide.BrowserUtil
import com.intellij.notification.NotificationListener
import com.intellij.openapi.project.Project
import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerCertificateException
import com.vyperplugin.VyperMessageProcessor
import javax.swing.event.HyperlinkEvent


/**
 * status code of execution
 */
enum class StatusDocker {
    EMPTY_OUTPUT,
    SUCCESS,
    FAILED,
    TIME_LIMIT,
    INTERNAL_ERROR
}

/**
 *  represents results of execution
 */
data class ToolResult(val stdout: String, val stderr: String, val filename: String, val statusDocker: StatusDocker)

/**
 * represents abstract tool that runs inside a container
 *
 */
abstract class AbstractToolDocker {
    val pluginDockerClient = PluginDockerClient.INSTANCE
    val dockerBindDir = "/app/s"

    /**
     * name of the image container
     */
    protected abstract var IMAGE: String

    /**
     * start container and return result
     * may throw exceptions
     */
    abstract fun exec(): ToolResult


    /**
     * preferred way to run docker within a project
     * handles downloading image if not found
     * this function handle docker exceptions and notify uses about possible solution
     */
    fun execWrapper(project: Project): ToolResult {
        return try {
            if (!isImageExistLocally()) {
                VyperMessageProcessor.notificateInBalloon(
                        VyperMessageProcessor.VyperNotification(
                                null, "Docker",
                                "<html>Image not found locally." +
                                        "Could take some time for download it." +
                                        "Please wait</html>",
                                VyperMessageProcessor.NotificationStatusVyper.INFO,
                                VyperMessageProcessor.NotificationGroupVyper.COMMON,
                                project
                        )
                )
                downloadImage()
            }
            exec()
        } catch (dockerCertificateException: DockerCertificateException) {
            notify(project)
            ToolResult("", "", "", StatusDocker.INTERNAL_ERROR)
        } catch (e: InterruptedException) {
            notify(project)
            ToolResult("", "", "", StatusDocker.INTERNAL_ERROR)
        }

    }

    private fun notify(project: Project) {
        VyperMessageProcessor.notificateInBalloon(
                VyperMessageProcessor.VyperNotification(
                        NotificationListener { _, hyperlinkEvent ->
                            if (hyperlinkEvent.eventType == HyperlinkEvent.EventType.ACTIVATED)
                                BrowserUtil.browse(hyperlinkEvent.url)
                        }, "Docker",
                        "<html>Error running docker.\n" +
                                " Do you install <a href=\"https://docs.docker.com/install/\">docker</a>" +
                                " and setup docker to run without sudo/root privilege?\n" +
                                "For linux setup docker-group </html>",
                        VyperMessageProcessor.NotificationStatusVyper.WARNING,
                        VyperMessageProcessor.NotificationGroupVyper.COMMON,
                        project
                )
        )
    }

    private fun isImageExistLocally(): Boolean {
        return pluginDockerClient.dockerClient.listImages(DockerClient.ListImagesParam.byName(IMAGE)).isNotEmpty()
    }

    //TODO make progress bar or notificate user about that
    private fun downloadImage() {
        pluginDockerClient.dockerClient.pull(IMAGE)
    }


}