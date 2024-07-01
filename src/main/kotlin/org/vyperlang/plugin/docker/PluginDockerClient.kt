package org.vyperlang.plugin.docker

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import java.time.Duration

internal val dockerConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build()
internal val dockerHttpClient = ApacheDockerHttpClient.Builder()
    .dockerHost(dockerConfig.dockerHost)
    .sslConfig(dockerConfig.sslConfig)
    .maxConnections(100)
    .connectionTimeout(Duration.ofSeconds(10))
    .responseTimeout(Duration.ofSeconds(15))
    .build()

val PluginDockerClient by lazy { DockerClientImpl.getInstance(dockerConfig, dockerHttpClient)!! }
