package org.vyperlang.plugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.compile.VyperCompiler
import org.vyperlang.plugin.compile.VyperParameters
import org.vyperlang.plugin.gui.NoFilesWithVyperAreSelectedDialogue
import org.vyperlang.plugin.settings.VyperSettings

private const val COMPILING_MESSAGE = "Compiling Vyper"
private val EXTENSION = VyperFileType.INSTANCE.defaultExtension

class CompileVyperFileAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val files = PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(e.dataContext)?.filter { it.extension == EXTENSION }
            ?.toList()

        if (files.isNullOrEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        ApplicationManager.getApplication().runWriteAction {
            FileDocumentManager.getInstance().saveAllDocuments()
        }

        val module = ModuleManager.getInstance(project).modules.first()
        val settings = VyperSettings.INSTANCE
        val params = VyperParameters(module, project, files, settings.getCompilerParamsArray(), settings.generateStubs, settings.fileExtension)
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, COMPILING_MESSAGE) {
            override fun run(indicator: ProgressIndicator) { VyperCompiler.compile(params, indicator) }
        })
    }
}
