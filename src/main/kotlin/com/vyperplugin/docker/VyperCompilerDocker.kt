package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig


/**
 * Code status of vyper compulation  in docker container
 */
enum class VyperCodeStatus {
    EMPTY_OUTPUT,
    SUCCESS,
    FAILED,
    TIME_LIMIT
}

/**
 * Result of file compilation
 * see vyper status codes
 */
class VyperCompilerResult(val status: VyperCodeStatus, val stdout: String, val stderr: String, val filename: String)


/**
 * Vyper compiler that runs inside docker container
 */
class VyperCompilerDocker : IToolDocker() {
    override var IMAGE = "ethereum/vyper"

    //TODO add timelimit
    //TODO swap to long running containers
    @Throws(DockerException::class, InterruptedException::class)
    fun compileFile(bindDir: String, filename: String, args: Array<String> = arrayOf()): VyperCompilerResult {

        val hostConfig = HostConfig.builder()
                .binds("$bindDir/:$dockerBindDir")
                .build()

        val completeCommand = args + (filename)
        val containerConfig = ContainerConfig.builder()
                .image(IMAGE)
                .hostConfig(hostConfig)
                .workingDir(dockerBindDir)
                .cmd(*completeCommand)
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


        //pluginDockerClient.dockerClient.stopContainer(id,15)
        pluginDockerClient.dockerClient.removeContainer(id)

        if (logSTDERR.isBlank() && logSTDOUT.isNotBlank()) {
            return VyperCompilerResult(VyperCodeStatus.SUCCESS, logSTDOUT, "", filename)
        }
        if (logSTDERR.isBlank() && logSTDOUT.isBlank()) {
            return VyperCompilerResult(VyperCodeStatus.EMPTY_OUTPUT, "", "", filename)
        }
        if (logSTDERR.isNotBlank()) {
            return VyperCompilerResult(VyperCodeStatus.FAILED, "", logSTDERR, filename)
        }

        // check
        throw IllegalAccessError()
    }

}