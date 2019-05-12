package com.vyperplugin

import com.intellij.openapi.project.Project

/**
 * messageProcessor for displaying messages to user
 */
object VyperMessageProcessor {

    fun showNotification(vyperNotification: VyperNotification) {
        //TODO
        //simple
    }

    enum class MessageStatus{
        INFO,
        ERROR,
        WARNING,
    }

    data class VyperNotification(val message:String, val status: MessageStatus, val project:Project)

}

