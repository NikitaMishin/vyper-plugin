package com.vyperplugin.compile

import com.intellij.notification.NotificationListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.VyperMessageProcessor
import com.vyperplugin.VyperStubGenerator
import com.vyperplugin.docker.StatusDocker
import com.vyperplugin.docker.VyperCompilerDocker
import com.vyperplugin.toolWindow.VyperWindow


data class VyperParameters(
        val module: Module,
        val project: Project,
        val files: Array<VirtualFile>,
        val compilerParameters: Array<String>,
        val generateStub: Boolean,
        val stubExtension: String
)

object VyperCompiler {

    private const val COMPILATION_FAILED = "Compilation failed"
    private const val COMPILATION_EMPTY = "Compilation empty"
    private const val COMPILATION_SUCCESS = "Compilation succeed"
    private const val compilationNavigateHtml = "<html><a href=cite>navigate to file</a></html>"
    private const val compilationEmptyHtml = "<html>No bytecode is generated</html>"

    fun compile(params: VyperParameters) {
        for (file in params.files) {
            val parent = file.parent.path
            val fullPath = file.path
            val result = VyperCompilerDocker(parent, fullPath, params.compilerParameters).exec()

            when {
                params.generateStub && result.statusDocker == StatusDocker.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                    VyperStubGenerator.createStubInGenSourceFolder(
                            result.stdout, params.module, params.project, fullPath, params.stubExtension)
                    notify(params.project, file, COMPILATION_SUCCESS,
                            "<html>Bytecode in tool window and in generated folder</html>",
                            VyperMessageProcessor.NotificationStatusVyper.INFO)

                }
                !params.generateStub && result.statusDocker == StatusDocker.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                    notify(params.project, file, COMPILATION_SUCCESS,
                            "<html>Bytecode in tool window</html>",
                            VyperMessageProcessor.NotificationStatusVyper.INFO)
                }
                params.generateStub && result.statusDocker == StatusDocker.FAILED -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stderr)
                    notify(params.project, file, COMPILATION_FAILED,
                            compilationNavigateHtml,
                            VyperMessageProcessor.NotificationStatusVyper.ERROR)
                }

                !params.generateStub && result.statusDocker == StatusDocker.FAILED -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stderr)
                    notify(params.project, file, COMPILATION_FAILED,
                            compilationNavigateHtml,
                            VyperMessageProcessor.NotificationStatusVyper.ERROR)
                }

                !params.generateStub && result.statusDocker == StatusDocker.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    notify(params.project, file, COMPILATION_EMPTY,
                            compilationEmptyHtml,
                            VyperMessageProcessor.NotificationStatusVyper.WARNING)
                }
                params.generateStub && result.statusDocker == StatusDocker.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    notify(params.project, file, COMPILATION_EMPTY,
                            compilationEmptyHtml,
                            VyperMessageProcessor.NotificationStatusVyper.WARNING)
                }

            }

        }
    }

    private fun notify(project: Project, file: VirtualFile, title: String, htmlWithLink: String,
                       status: VyperMessageProcessor.NotificationStatusVyper) {
        VyperMessageProcessor.notificateInBalloon(
                VyperMessageProcessor.VyperNotification(
                        NotificationListener { not, _ ->
                            val open = OpenFileDescriptor(project, file,
                                    0, 0)
                            open.navigate(true)
                            not.expire()
                        }, title, htmlWithLink, status,
                        VyperMessageProcessor.NotificationGroupVyper.COMPILER, project
                )
        )
    }


    private fun displayOutputOnToolWindow(project: Project, path: String, output: String) =
            ApplicationManager.getApplication().invokeLater({
                VyperWindow.appendTextToTabsWindow(
                        project, VyperWindow.VyperWindowTab.COMPILER_TAB, "$path:\n$output")
            }, ModalityState.any())

}
