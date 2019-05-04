package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig


class SmartCheckDocker : IToolDocker() {
    private val toolName = "smartcheck.jar"
    override var IMAGE = "smartcheck_image"
    private var incompleteCommand = arrayOf("sh", "-c")

    @Throws(DockerException::class, InterruptedException::class)
    fun analyzeFileinBindDir(bindDir: String, filename: String): List<String> {

        val hostConfig = HostConfig.builder()
                .binds("$bindDir:$dockerBindDir")
                .build()

        val completeCommand = incompleteCommand + arrayOf("java -jar $toolName -p $dockerBindDir/$filename")
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
                // DockerClient.AttachParameter.STDERR, ignore antlr errors
                DockerClient.AttachParameter.STREAM)

        pluginDockerClient.dockerClient.startContainer(id)

        logs = stream.readFully()
        pluginDockerClient.dockerClient.removeContainer(id)
        return logs.lines()
    }

    companion object {
        //is _image exists
    }

}
