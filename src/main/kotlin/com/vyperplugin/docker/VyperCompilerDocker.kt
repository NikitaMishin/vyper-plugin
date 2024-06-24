package org.vyperlang.plugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.model.Bind
import com.github.dockerjava.api.model.Frame
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.Volume


/**
 * Vyper compiler that runs inside docker container
 */

class VyperCompilerDocker(
    var bindDir: String, var fullPathToFile: String,
    var args: Array<String> = arrayOf()
) : AbstractToolDocker() {
    override var image = "murmulla/vyper_and_vyper_run"
    override var imageTag = "version2"
    private val toolName = "vyper"

    inner class VyperAdapterLogs : ResultCallback.Adapter<Frame>() {
        override fun onNext(item: Frame) {
            logs.add(item.toString())
        }
    }

    private val logs = mutableListOf<String>()

    override fun exec(): ToolResult {

        val filename = fullPathToFile.split('/').last()
        return compileFile(bindDir, filename, args)
    }

    //TODO add timelimit
    //TODO swap to long running containers
    fun compileFile(bindDir: String, filename: String, args: Array<String> = arrayOf()): ToolResult {

        val completeCommand: Array<String> = (args + filename)

        val hostConfig = HostConfig()
            .withBinds(Bind(bindDir, Volume(dockerBindDir)))

        val creation = pluginDockerClient
            .createContainerCmd("$image:$imageTag")
            .withHostConfig(hostConfig)
            .withCmd(*completeCommand)
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
            .exec(VyperAdapterLogs())
            .awaitCompletion()

        val logsError = logs.filter { it.contains("stderr", true) }.map { it.removePrefix("STDERR: ") }
        val logsOut = logs.filter { it.contains("stdout", true) }.map { it.removePrefix("STDOUT: ") }
        pluginDockerClient
            .removeContainerCmd(id)
            .exec()

        return when {
            logsError.isEmpty() && logsOut.isNotEmpty() -> ToolResult(
                logsOut.joinToString(separator = "\n"),
                "",
                fullPathToFile,
                StatusDocker.SUCCESS
            )
            logsError.isEmpty() -> ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
            else -> ToolResult("", logsError.joinToString(separator = "\n"), fullPathToFile, StatusDocker.FAILED)
        }
    }
}