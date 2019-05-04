package com.vyperplugin.actions.compile

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.actions.VyperAction
import com.vyperplugin.compile.VyperStubGenerator
import com.vyperplugin.docker.VyperCompilerDocker
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue

class CompileVyperFile : VyperAction() {
    private val compilatorDocker = VyperCompilerDocker()

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val files: Array<VirtualFile>? = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        ProgressManager.getInstance().run(object : Task.Backgroundable(project,"Compiling vyper")
        {
            override fun run(indicator: ProgressIndicator) {
                val res = compilatorDocker.compileFile(files.first().parent.path,files.first().path.split("/").last(), arrayOf("-f","abi"))

                VyperStubGenerator.createStubInGenSourceFolder(res.stderr+res.stdout, ModuleManager.getInstance(project).modules.first(),project,"hello",".txt")

            }

        }
        )
    }



}