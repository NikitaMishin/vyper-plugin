package com.vyperplugin.docker

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.model.Bind
import com.github.dockerjava.api.model.Frame
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.Volume

class SmartCheckDocker(var bindDir: String, var fullPathToFile: String) : AbstractToolDocker() {

    override var IMAGE = "murmulla/smartcheck"
    override var IMAGE_TAG = "version1"

    private val toolName = "smartcheck.jar"
    private var incompleteCommand = arrayOf("sh", "-c")

    inner class SmartCheckAdapter : ResultCallback.Adapter<Frame>() {
        override fun onNext(item: Frame) {
            logs.add(item.toString())
        }
    }

    private val logs = mutableListOf<String>()

    override fun exec(): ToolResult {

        val filename = fullPathToFile.split("/").last()
        val logs = analyzeFileInBindDir(filename)
        return if (logs.isEmpty()) {
            ToolResult("", "", fullPathToFile, StatusDocker.EMPTY_OUTPUT)
        } else {
            ToolResult(logs, "", fullPathToFile, StatusDocker.SUCCESS)
        }
    }

    fun analyzeFileInBindDir(filename: String): String {

        val hostConfig = HostConfig()
            .withBinds(Bind(bindDir, Volume(dockerBindDir)))

        val completeCommand = incompleteCommand + arrayOf("java -jar $toolName -p $dockerBindDir/$filename")
        val creation = pluginDockerClient
            .createContainerCmd("$IMAGE:$IMAGE_TAG")
            .withHostConfig(hostConfig)
            .withCmd(*completeCommand)
            .exec()

        val id = creation.id

        pluginDockerClient.startContainerCmd(id).exec()

        pluginDockerClient
            .logContainerCmd(id)
            .withStdOut(true)
            .withFollowStream(true)
            .exec(SmartCheckAdapter())
            .awaitCompletion()

        pluginDockerClient.removeContainerCmd(id).exec()
        return logs.joinToString("\n") { it.removePrefix("STDOUT: ") }
    }
}
