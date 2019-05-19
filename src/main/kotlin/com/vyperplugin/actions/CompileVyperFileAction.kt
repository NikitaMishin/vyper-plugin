package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.vyperplugin.compile.VyperCompiler
import com.vyperplugin.compile.VyperParameters
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue
import com.vyperplugin.settings.VyperSettings


class CompileVyperFileAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val files = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Compiling vyper") {
            override fun run(indicator: ProgressIndicator) {
                VyperCompiler.compile(VyperParameters(
                        ModuleManager.getInstance(project).modules.first(), project, files,
                        VyperSettings.INSTANCE.getCompilerParamsArray(),
                        VyperSettings.INSTANCE.generateStubs, VyperSettings.INSTANCE.fileExtension)
                )
            }
        })
    }

}