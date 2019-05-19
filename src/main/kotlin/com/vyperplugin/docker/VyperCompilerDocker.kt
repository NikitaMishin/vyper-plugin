package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig


/**
 * Vyper compiler that runs inside docker container
 */
class VyperCompilerDocker(var bindDir: String, var fullPathToFile: String,
                          var args: Array<String> = arrayOf()) : AbstractToolDocker() {
    override var IMAGE = "murmulla/vyper_and_vyper_run:version2"
    private val toolName = "vyper"

    override fun exec(): ToolResult {

        val filename = fullPathToFile.split('/').last()
        return compileFile(bindDir, filename, args)
    }


    //TODO add timelimit
    //TODO swap to long running containers
    @Throws(DockerException::class, InterruptedException::class)
    fun compileFile(bindDir: String, filename: String, args: Array<String> = arrayOf()): ToolResult {

        val hostConfig = HostConfig.builder()
                .binds("$bindDir/:$dockerBindDir")
                .build()

        val completeCommand: Array<String> = (args + filename)

        val containerConfig = ContainerConfig.builder()
                .image(IMAGE)
                .hostConfig(hostConfig)
                .workingDir(dockerBindDir)
                .cmd(*completeCommand)
                .entrypoint(toolName)
                .build()

        val creation = pluginDockerClient.dockerClient.createContainer(containerConfig)
        val id = creation.id()!!

        val streamSTDOUT = pluginDockerClient.dockerClient.attachContainer(id,
                DockerClient.AttachParameter.STDOUT, DockerClient.AttachParameter.STREAM)
        val streamSTDERR = pluginDockerClient.dockerClient.attachContainer(id,
                DockerClient.AttachParameter.STDERR, DockerClient.AttachParameter.STREAM)


        pluginDockerClient.dockerClient.startContainer(id)
        val logSTDOUT = streamSTDOUT.readFully()
        val logSTDERR = streamSTDERR.readFully()


        pluginDockerClient.dockerClient.removeContainer(id)

        if (logSTDERR.isBlank() && logSTDOUT.isNotBlank()) {
            return ToolResult(logSTDOUT, "", fullPathToFile, StatusDocker.SUCCESS)
        }
        if (logSTDERR.isBlank() && logSTDOUT.isBlank()) {
            return ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
        }
        if (logSTDERR.isNotBlank()) {
            return ToolResult("", logSTDERR, fullPathToFile, StatusDocker.FAILED)
        }

        // check
        throw IllegalAccessError()
    }

}