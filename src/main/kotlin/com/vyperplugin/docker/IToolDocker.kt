package com.vyperplugin.docker

abstract class IToolDocker {
    val pluginDockerClient = PluginDockerClient.INSTANCE
    val dockerBindDir = "/app/s"
    protected abstract var IMAGE: String

}