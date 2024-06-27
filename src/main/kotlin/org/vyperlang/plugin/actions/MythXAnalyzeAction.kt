package org.vyperlang.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.vfs.VirtualFile
import org.vyperlang.plugin.VyperMessageProcessor
import org.vyperlang.plugin.analyze.MythXAnalyzer
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.VyperCompilerDocker
import org.vyperlang.plugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue


class MythXAnalyzeAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {
        val project = e.project ?: return

        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }

        val files: Array<VirtualFile>? =
            getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files.isNullOrEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        // TODO: We only take the first file, but we should take all of them
        val file = files.first()

        //task for analyzer
        ProgressManager.getInstance()
            .run(object : Task.Backgroundable(project, "analyzing with MythX, response may take some time...") {
                override fun run(indicator: ProgressIndicator) {
                    val res = VyperCompilerDocker(
                        file.parent.path, file.path,
                        arrayOf("-f", "bytecode,bytecode_runtime")
                    ).execWrapper(project)
                    when (res.statusDocker) {
                        StatusDocker.SUCCESS -> {
                            val (bytecode, bytecode_runtime) = res.stdout.lines()
                            MythXAnalyzer.analyze(bytecode, bytecode_runtime, file.path, project)
                        }
                        else -> {
                            VyperMessageProcessor.notificateInBalloon(
                                VyperMessageProcessor.VyperNotification(
                                    null,
                                    "MythX", "File ${file.name} contains errors: ${res.stderr}",
                                    VyperMessageProcessor.NotificationStatusVyper.ERROR,
                                    VyperMessageProcessor.NotificationGroupVyper.COMMON,
                                    project
                                )
                            )
                        }
                    }

                }
            })
    }
}





