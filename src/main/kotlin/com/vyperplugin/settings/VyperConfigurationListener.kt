package com.vyperplugin.settings

import com.intellij.util.messages.Topic

interface VyperConfigurationListener {

    fun settingsConfigChanged()

    companion object {
        // message bus
        val TOPIC = Topic.create("Vyper Settings", VyperConfigurationListener::class.java)
    }
}