package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.VyperMessageProcessor
import com.vyperplugin.analyze.MythXAnalyzer
import com.vyperplugin.docker.StatusDocker
import com.vyperplugin.docker.VyperCompilerDocker
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue


class MythXAnalyzeAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {
        val project = e.project ?: return

        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }

        val files: Array<VirtualFile>? = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        //
        //TODO FIX RIGHT NOW ONLY ONE FILE
        //TODO FIX, not good
        val file = files.first()

        //task for analyzer
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "analyzing with MythX, response may take some time...") {
            override fun run(indicator: ProgressIndicator) {

                val res = VyperCompilerDocker(
                        file.parent.path, file.path,
                        arrayOf("-f", "bytecode,bytecode_runtime")).execWrapper(project)
                when (res.statusDocker) {
                    StatusDocker.SUCCESS -> {
                        val (bytecode, bytecode_runtime) = res.stdout.lines()
                        MythXAnalyzer.analyze(bytecode, bytecode_runtime, file.path, project)
                    }
                    else -> {
                        VyperMessageProcessor.notificateInBalloon(
                                VyperMessageProcessor.VyperNotification(
                                        null,
                                        "MythX", "File ${file.name} contains errors",
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





