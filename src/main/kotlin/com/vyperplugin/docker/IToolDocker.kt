package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient


/**
 * status code of execution
 */
enum class StatusCode {
    EMPTY_OUTPUT,
    SUCCESS,
    FAILED,
    TIME_LIMIT
}

/**
 *  represent results of execution
 */
data class ToolResult(val stdout: String, val stderr: String, val filename: String, val statusCode: StatusCode)

/**
 * represent abstract tool that runs inside a container
 *
 */
abstract class IToolDocker {
    val pluginDockerClient = PluginDockerClient.INSTANCE
    val dockerBindDir = "/app/s"

    /**
     * name of the image container
     */
    protected abstract var IMAGE: String

    /**
     * start container and return result
     */
    abstract fun exec(): ToolResult

    fun isImageExistLocally(): Boolean {
        return pluginDockerClient.dockerClient.listImages(DockerClient.ListImagesParam.byName(IMAGE)).isNotEmpty()
    }

    //TODO make progress bar or notificate user about that
    protected fun downloadImage() {
        pluginDockerClient.dockerClient.pull(IMAGE)
    }

}