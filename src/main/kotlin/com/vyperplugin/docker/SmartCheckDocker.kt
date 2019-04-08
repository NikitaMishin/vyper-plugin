package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerCertificateException
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig


class SmartCheckDocker() {
    val toolName = "smartcheck.jar"
    val dockerBindDir = "/app/s"
    var incompleCommand = arrayOf("sh", "-c")
    val IMAGE = "smartcheck_image"
    var pluginDockerClient = PluginDockerClient.INSTANCE


    @Throws(DockerException::class, InterruptedException::class, DockerCertificateException::class)
    fun analyzeFileinBindDir(bindDir: String, filename: String): List<String> {
        val hostConfig = HostConfig.builder()
                .binds("$bindDir:$dockerBindDir")
                .build()
        val completeCommand = incompleCommand + arrayOf("java -jar $toolName -p $dockerBindDir/$filename")
        val containerConfig = ContainerConfig.builder()
                .image(IMAGE)
                .hostConfig(hostConfig)
                .cmd(*completeCommand)
                .build()


        val creation = pluginDockerClient.dockerClient.createContainer(containerConfig)
        val id = creation.id()!!


        val logs: String
        val stream = pluginDockerClient.dockerClient.attachContainer(id,
                DockerClient.AttachParameter.LOGS, DockerClient.AttachParameter.STDOUT,
                // DockerClient.AttachParameter.STDERR,
                DockerClient.AttachParameter.STREAM)

        pluginDockerClient.dockerClient.startContainer(id)

        logs = stream.readFully()

        //pluginDockerClient.dockerClient.stopContainer(id, 20)

        pluginDockerClient.dockerClient.removeContainer(id)
        return logs.lines()
    }


    companion object {
        //is _image exists
    }

}
