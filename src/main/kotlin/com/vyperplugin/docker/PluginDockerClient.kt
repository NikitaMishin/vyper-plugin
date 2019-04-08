package com.vyperplugin.docker

import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerCertificateException


class PluginDockerClient @Throws(DockerCertificateException::class) constructor() {
    // error throws specifcally when no group docker defined

    var dockerClient: DockerClient = DefaultDockerClient.fromEnv().build()

    companion object {
        var INSTANCE = PluginDockerClient()
    }
}