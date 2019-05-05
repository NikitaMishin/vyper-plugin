package com.vyperplugin.compile

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.docker.VyperCodeStatus
import com.vyperplugin.docker.VyperCompilerDocker
import com.vyperplugin.toolWindow.VyperRunAndCompilerWindow


data class VyperParameters(
        val module: Module,
        val project: Project,
        val files: Array<VirtualFile>,
        val compilerParameters: Array<String>,
        val generateStub: Boolean,
        val stubExtension: String
)

object VyperCompiler {
    private val compilatorDocker = VyperCompilerDocker()

    fun compile(params: VyperParameters) {
        for (file in params.files) {
            val parent = file.parent.path
            val filename = file.path.split("/").last()
            val result = compilatorDocker.compileFile(parent, filename, params.compilerParameters)

            when {
                params.generateStub && result.status == VyperCodeStatus.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                    VyperStubGenerator.createStubInGenSourceFolder(
                            result.stdout, params.module, params.project, filename, params.stubExtension)
                }
                !params.generateStub && result.status == VyperCodeStatus.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                }
                params.generateStub && result.status == VyperCodeStatus.FAILED -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stderr)
                    // notificate throuhg message processor
                }

                !params.generateStub && result.status == VyperCodeStatus.FAILED -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stderr)
                    // notificate throuhg message processor
                }

                !params.generateStub && result.status == VyperCodeStatus.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    // notificate throuhg message processor
                }
                params.generateStub && result.status == VyperCodeStatus.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    // notificate throuhg message processor
                }

            }

        }
    }


    private fun displayOutputOnToolWindow(project: Project, path: String, output: String) =
            ApplicationManager.getApplication().invokeLater({
                VyperRunAndCompilerWindow.appendTextToCompilerWindow(
                        project, "$path:\n$output")
            }, ModalityState.any())

}
