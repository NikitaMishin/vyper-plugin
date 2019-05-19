package com.vyperplugin.docker

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig

/**
 * vyper-run contract_name.vy "call1(params1,parems2,parems3);call2()" -i 1,2," "
 * i.e callSequence is "call1(params1,parems2,parems3);call2()"
 * if params is a list then \"[[args] ...] \"
 * init is ArrayOf("-i",1, ",", 2, " \" \" ")
 */
class VyperRunDocker(var bindDir: String, var fullPathToFile: String, var callSequence: String,
                     var init: Array<String> = arrayOf()) : AbstractToolDocker() {

    override var IMAGE: String = "murmulla/vyper_and_vyper_run:version2"
    private val toolName = "vyper-run"
    private val initCommand = "-i"
    override fun exec(): ToolResult = testRun()

    private fun getInput(): Array<String> {
        val filteredSeq = callSequence.dropLastWhile { it == ';' }
        return if (init.contentEquals(arrayOf())) {
            arrayOf(fullPathToFile.split("/").last(), filteredSeq)
        } else {
            val initArgs = mutableListOf<String>()
            for (i in init) {
                initArgs.add(i)
                initArgs.add(",")
            }

            arrayOf(fullPathToFile.split("/").last(), filteredSeq, initCommand,(initArgs.dropLast(1).joinToString("")))
                    //*(initArgs.toTypedArray()))
        }
    }

    private fun testRun(): ToolResult {

        val hostConfig = HostConfig.builder()
                .binds("$bindDir/:$dockerBindDir")
                .build()

        val t = getInput()
        val containerConfig = ContainerConfig.builder()
                .image(IMAGE)
                .hostConfig(hostConfig)
                .workingDir(dockerBindDir)
                .cmd(*t)
                .entrypoint(toolName)
                .build()

        val creation = pluginDockerClient.dockerClient.createContainer(containerConfig)
        val id = creation.id()!!

        val streamSTDOUT = pluginDockerClient.dockerClient.attachContainer(id, DockerClient.AttachParameter.STDIN,
                DockerClient.AttachParameter.STDOUT, DockerClient.AttachParameter.STREAM)
        val streamSTDERR = pluginDockerClient.dockerClient.attachContainer(id, DockerClient.AttachParameter.STDIN,
                DockerClient.AttachParameter.STDERR, DockerClient.AttachParameter.STREAM)


        pluginDockerClient.dockerClient.startContainer(id)
        val logSTDOUT = streamSTDOUT.readFully()
        val logSTDERR = streamSTDERR.readFully()
        pluginDockerClient.dockerClient.removeContainer(id)

        when {
            logSTDERR.isBlank() && logSTDOUT.isNotBlank() ->
                return ToolResult(logSTDOUT, "", fullPathToFile, StatusDocker.SUCCESS)
            logSTDERR.isBlank() && logSTDOUT.isBlank() ->
                return ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
            logSTDERR.isNotBlank() ->
                return ToolResult("", logSTDERR, fullPathToFile, StatusDocker.FAILED)

        }
        // just for
        throw IllegalStateException()
    }

}