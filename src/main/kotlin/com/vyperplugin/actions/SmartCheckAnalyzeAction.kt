package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.analyze.SmartCheckAnalyzer
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue


class SmartCheckAnalyzeAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {
        val project = e.project ?: return

        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }

        val files: Array<VirtualFile>? =
            getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        //task for analyzer
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Analyzing with smartCheck...") {
            override fun run(indicator: ProgressIndicator) {
                SmartCheckAnalyzer.smartCheckAnalyze(files, project)
            }
        })

    }
}



