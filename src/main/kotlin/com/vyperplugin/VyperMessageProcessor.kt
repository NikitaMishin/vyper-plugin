package com.vyperplugin

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationListener
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

    private const val NOTIFICATION_ID_COMPILER = "Vyper Compiler"
    private const val NOTIFICATION_ID_TEST = "Vyper-run"
    private const val NOTIFICATION_ID_ANALYZER = "Vyper Aalyzers"
    private const val NOTIFICATION_ID_COMMON = "Vyper Common"

    enum class NotificationStatusVyper {
        INFO,
        ERROR,
        WARNING,
        LOGGING
    }

    private fun NotificationStatusVyper.convert() = when (this) {
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

    private fun NotificationGroupVyper.convert() = when (this) {
        VyperMessageProcessor.NotificationGroupVyper.COMPILER -> NOTIFICATION_ID_COMPILER
        VyperMessageProcessor.NotificationGroupVyper.RUN -> NOTIFICATION_ID_TEST
        VyperMessageProcessor.NotificationGroupVyper.ANALYZE -> NOTIFICATION_ID_ANALYZER
        VyperMessageProcessor.NotificationGroupVyper.COMMON -> NOTIFICATION_ID_COMMON
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
            val project: Project)


    /**
     * Notificate user in ballon about something
     */
    fun notificateInBalloon(vyperNotification: VyperNotification) {
        val notification = when (vyperNotification.status) {
            VyperMessageProcessor.NotificationStatusVyper.LOGGING ->
                NotificationGroup.logOnlyGroup(vyperNotification.group.convert()).createNotification(
                        vyperNotification.title,
                        vyperNotification.message,
                        vyperNotification.status.convert().toNotificationType(),
                        vyperNotification.notificationListener
                )
            else -> NotificationGroup(vyperNotification.group.convert(), NotificationDisplayType.BALLOON, true).createNotification(
                    vyperNotification.title,
                    vyperNotification.message,
                    vyperNotification.status.convert().toNotificationType(),
                    vyperNotification.notificationListener
            )
        }.setImportant(false) // or true.

        notification.notify(vyperNotification.project)
    }

    /**
     * TODO not use this method, not ready use @notificateInBalloon
     */
    fun notificateInPopup(htmlText: String, project: Project, time: Long, status: NotificationStatusVyper,
                          hyperlinkListener: HyperlinkListener) {
        val statusBar = WindowManager.getInstance().getIdeFrame(project)
        val popup =
                JBPopupFactory.getInstance().createHtmlTextBalloonBuilder(htmlText,
                        status.convert(), hyperlinkListener).setFadeoutTime(time)
        popup.createBalloon().show(RelativePoint.getCenterOf(statusBar.component), Balloon.Position.atRight)
    }


}

