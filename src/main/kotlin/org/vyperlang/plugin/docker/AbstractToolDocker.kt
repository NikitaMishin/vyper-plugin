package org.vyperlang.plugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.exception.DockerException
import com.github.dockerjava.api.model.PullResponseItem
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.project.Project
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
    protected abstract var image: String
    protected abstract var imageTag: String

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
                        "<html>Image is not found in your system. " +
                                "It may take a while to download it. " +
                                "Please wait</html>",
                        VyperMessageProcessor.NotificationStatusVyper.INFO,
                        VyperMessageProcessor.NotificationGroupVyper.COMMON,
                        project
                    )
                )
                downloadImage()
            }
            exec()
        } catch (dockerException: DockerException) {
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
                { _, hyperlinkEvent ->
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
        val kek = pluginDockerClient.listImagesCmd().exec()
        val lol = kek.find { it.repoTags.any { k -> k.contains(image, true) } }
        return lol != null
    }

    //TODO make progress bar or notificate user about that
    private fun downloadImage() {
        pluginDockerClient.pullImageCmd(image).withTag(imageTag).exec(VyperPullImageAdapter()).awaitCompletion()
    }

    private class VyperPullImageAdapter : ResultCallback.Adapter<PullResponseItem>() {
        override fun onNext(item: PullResponseItem) {
            super.onNext(item)
        }
    }
}