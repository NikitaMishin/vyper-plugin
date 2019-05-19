package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.vyperplugin.gui.VyperRunContractMultiple
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue
import com.vyperplugin.run.VyperRun
import com.vyperplugin.run.VyperTestParameters

class VyperRunAction : VyperAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }

        val files = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        // display frame only for one
        val frame = VyperRunContractMultiple(project, ModuleManager.getInstance(project).modules.first(), files.first())
        frame.addPropertyChangeListener {
            ProgressManager.getInstance().run(object : Task.Backgroundable(project, "running contract...") {
                override fun run(indicator: ProgressIndicator) {
                    VyperRun.testContractMultipleMethod(it.newValue as VyperTestParameters)
                }
            })
        }

        frame.display()

    }

}