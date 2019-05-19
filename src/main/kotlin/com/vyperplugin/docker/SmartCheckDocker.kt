package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.LogStream
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig


class SmartCheckDocker(var bindDir: String, var fullPathToFile: String) : AbstractToolDocker() {

    override var IMAGE = "murmulla/smartcheck:version1"

    private val toolName = "smartcheck.jar"
    private var incompleteCommand = arrayOf("sh", "-c")

    override fun exec(): ToolResult {

        val filename = fullPathToFile.split("/").last()
        val logs = analyzeFileInBindDir(filename)
        return if (logs.isEmpty()) {
            ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
        } else {
            ToolResult(logs, "", fullPathToFile, StatusDocker.SUCCESS)
        }
    }


    @Throws(DockerException::class, InterruptedException::class)
    fun analyzeFileInBindDir(filename: String): String {

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
        val stream: LogStream = pluginDockerClient.dockerClient.attachContainer(id,
                DockerClient.AttachParameter.LOGS, DockerClient.AttachParameter.STDOUT,
                // DockerClient.AttachParameter.STDERR, ignore antlr errors
                DockerClient.AttachParameter.STREAM)

        pluginDockerClient.dockerClient.startContainer(id)

        logs = stream.readFully()
        pluginDockerClient.dockerClient.removeContainer(id)
        return logs
    }


}
