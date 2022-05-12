package com.vyperplugin

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

/**
 * messageProcessor for displaying messages to user
 */
object VyperMessageProcessor {

    private const val NOTIFICATION_ID_COMPILER = "COMPILER"
    private const val NOTIFICATION_ID_TEST = "RUN"
    private const val NOTIFICATION_ID_ANALYZER = "ANALYZE"
    private const val NOTIFICATION_ID_COMMON = "COMMON"

    enum class NotificationStatusVyper {
        INFO,
        ERROR,
        WARNING,
        LOGGING
    }

    private fun NotificationStatusVyper.convertNotify() = when (this) {
        NotificationStatusVyper.INFO -> NotificationType.INFORMATION
        NotificationStatusVyper.ERROR -> NotificationType.ERROR
        NotificationStatusVyper.WARNING -> NotificationType.WARNING
        NotificationStatusVyper.LOGGING -> NotificationType.INFORMATION
    }

    enum class NotificationGroupVyper {
        COMPILER,
        RUN,
        ANALYZE,
        COMMON,
    }

    private fun NotificationGroupVyper.convertNotification() = when (this) {
        NotificationGroupVyper.COMPILER -> NOTIFICATION_ID_COMPILER
        NotificationGroupVyper.RUN -> NOTIFICATION_ID_TEST
        NotificationGroupVyper.ANALYZE -> NOTIFICATION_ID_ANALYZER
        NotificationGroupVyper.COMMON -> NOTIFICATION_ID_COMMON
    }

    /**
     * Notification that displayed to user for specific project
     */
    data class VyperNotification(
        val notificationListener: NotificationListener?,
        val title: String,
        val message: String,
        val status: NotificationStatusVyper,
        val group: NotificationGroupVyper,
        val project: Project
    )


    /**
     * Notificate user in ballon about something
     */
    fun notificateInBalloon(vyperNotification: VyperNotification) {
        val notification = when (vyperNotification.status) {
            NotificationStatusVyper.LOGGING ->
                NotificationGroupManager
                    .getInstance()
                    .getNotificationGroup(vyperNotification.group.convertNotification())
                    .createNotification(
                        vyperNotification.title,
                        vyperNotification.message,
                        vyperNotification.status.convertNotify()
                    )
            else -> NotificationGroupManager
                .getInstance()
                .getNotificationGroup(vyperNotification.group.convertNotification())
                .createNotification(
                    vyperNotification.title,
                    vyperNotification.message,
                    vyperNotification.status.convertNotify()
                )
        }.setImportant(false) // or true.

        notification.notify(vyperNotification.project)
    }


}

