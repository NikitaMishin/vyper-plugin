package com.vyperplugin

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint
import javax.swing.event.HyperlinkListener

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
    private fun NotificationStatusVyper.convertMessage() = when (this) {
        NotificationStatusVyper.INFO -> MessageType.INFO
        NotificationStatusVyper.ERROR -> MessageType.ERROR
        NotificationStatusVyper.WARNING -> MessageType.WARNING
        NotificationStatusVyper.LOGGING -> MessageType.INFO
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
                    .createNotification(vyperNotification.title, vyperNotification.message, vyperNotification.status.convertNotify())
            else -> NotificationGroupManager
                .getInstance()
                .getNotificationGroup(vyperNotification.group.convertNotification())
                .createNotification(vyperNotification.title, vyperNotification.message, vyperNotification.status.convertNotify())
        }.setImportant(false) // or true.

        notification.notify(vyperNotification.project)
    }

    /**
     * TODO not use this method, not ready use @notificateInBalloon
     */
    fun notificateInPopup(
        htmlText: String, project: Project, time: Long, status: NotificationStatusVyper,
        hyperlinkListener: HyperlinkListener
    ) {
        val statusBar = WindowManager.getInstance().getIdeFrame(project)
        val popup =
            JBPopupFactory.getInstance().createHtmlTextBalloonBuilder(
                htmlText,
                status.convertMessage(), hyperlinkListener
            ).setFadeoutTime(time)
        popup.createBalloon().show(RelativePoint.getCenterOf(statusBar!!.component), Balloon.Position.atRight)
    }


}

