package com.vyperplugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.model.Bind
import com.github.dockerjava.api.model.Frame
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.Volume


/**
 * vyper-run contract_name.vy "call1(params1,parems2,parems3);call2()" -i 1,2," "
 * i.e callSequence is "call1(params1,parems2,parems3);call2()"
 * if params is a list then \"[[args] ...] \"
 * init is ArrayOf("-i",1, ",", 2, " \" \" ")
 */
class VyperRunDocker(
    var bindDir: String, var fullPathToFile: String, var callSequence: String,
    var init: Array<String> = arrayOf()
) : AbstractToolDocker() {

    inner class VyperRunAdapterLogs : ResultCallback.Adapter<Frame>() {
        override fun onNext(item: Frame) {
            logs.add(item.toString())
        }
    }

    private val logs = mutableListOf<String>()

    override var image = "murmulla/vyper_and_vyper_run"
    override var imageTag = "version2"
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
            arrayOf(fullPathToFile.split("/").last(), filteredSeq, initCommand, (initArgs.dropLast(1).joinToString("")))
            //*(initArgs.toTypedArray()))
        }
    }

    private fun testRun(): ToolResult {
        val hostConfig = HostConfig()
            .withBinds(Bind(bindDir, Volume(dockerBindDir)))

        val t = getInput()
        val creation = pluginDockerClient
            .createContainerCmd("$image:$imageTag")
            .withHostConfig(hostConfig)
            .withCmd(*t)
            .withEntrypoint(toolName)
            .withWorkingDir(dockerBindDir)
            .exec()

        val id = creation.id

        pluginDockerClient.startContainerCmd(id).exec()

        pluginDockerClient
            .logContainerCmd(id)
            .withStdErr(true)
            .withStdOut(true)
            .withFollowStream(true)
            .exec(VyperRunAdapterLogs())
            .awaitCompletion()

        val logsRunError = logs.filter { it.contains("stderr", true) }.map { it.removePrefix("STDERR: ") }
        val logsRunOut = logs.filter { it.contains("stdout", true) }.map { it.removePrefix("STDOUT: ") }
        pluginDockerClient
            .removeContainerCmd(id)
            .exec()

        return when {
            logsRunError.isEmpty() && logsRunOut.isNotEmpty() ->
                ToolResult(logsRunOut.joinToString(separator = "\n"), "", fullPathToFile, StatusDocker.SUCCESS)
            logsRunError.isEmpty() ->
                ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
            else ->
                ToolResult("", logsRunError.joinToString(separator = "\n"), fullPathToFile, StatusDocker.FAILED)

        }
    }

}