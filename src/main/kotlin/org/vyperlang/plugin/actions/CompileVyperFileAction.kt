package org.vyperlang.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import org.vyperlang.plugin.compile.VyperCompiler
import org.vyperlang.plugin.compile.VyperParameters
import org.vyperlang.plugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue
import org.vyperlang.plugin.settings.VyperSettings


class CompileVyperFileAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val files = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return org.vyperlang.plugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue().display()
        }


        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }


        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Compiling Vyper") {
            override fun run(indicator: ProgressIndicator) {
                VyperCompiler.compile(
                    VyperParameters(
                        ModuleManager.getInstance(project).modules.first(), project, files,
                        VyperSettings.INSTANCE.getCompilerParamsArray(),
                        VyperSettings.INSTANCE.generateStubs, VyperSettings.INSTANCE.fileExtension
                    )
                )
            }
        })
    }

}