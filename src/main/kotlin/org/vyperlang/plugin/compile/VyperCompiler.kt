package org.vyperlang.plugin.compile

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.module.Module
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.vyperlang.plugin.VyperMessageProcessor
import org.vyperlang.plugin.VyperStubGenerator
import org.vyperlang.plugin.docker.CompilerMissingError
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.docker.VyperCompilerDocker
import org.vyperlang.plugin.toolWindow.VyperWindow


data class VyperParameters(
    val module: Module,
    val project: Project,
    val files: List<VirtualFile>,
    val compilerParameters: List<String>,
    val generateStub: Boolean,
    val stubExtension: String
)

object VyperCompiler {
    private const val COMPILATION_FAILED = "Compilation failed"
    private const val COMPILATION_EMPTY = "Compilation empty"
    private const val COMPILATION_SUCCESS = "Compilation succeed"

    fun compile(params: VyperParameters, indicator: ProgressIndicator? = null) =
        try {
            params.files.map {
                val compiler =
                    VyperCompilerDocker(params.project, it, indicator, *params.compilerParameters.toTypedArray())
                reportResult(it, params, compiler.run())
            }
        } catch(e: CompilerMissingError) {
            e.notify(params.project)
            null
        }

    private fun reportResult(
        file: VirtualFile,
        params: VyperParameters,
        result: ToolResult
    ): ToolResult {
        when (result.statusDocker) {
            StatusDocker.SUCCESS -> {
                displayOutputOnToolWindow(params.project, file, result.stdout)
                if (params.generateStub) {
                    val stub = VyperStubGenerator.createStubInGenSourceFolder(
                        result.stdout, params.module, params.project, file, params.stubExtension
                    )
                    notify(
                        params.project, file, COMPILATION_SUCCESS,
                        "<html>Bytecode generated to ${stub.path}</html>",
                        VyperMessageProcessor.NotificationStatusVyper.INFO
                    )
                }
            }

            StatusDocker.FAILED -> {
                displayOutputOnToolWindow(params.project, file, result.stderr)
                notify(
                    params.project, file, COMPILATION_FAILED,
                    "<html>Vyper failed to compile</html>",
                    VyperMessageProcessor.NotificationStatusVyper.ERROR
                )
            }

            StatusDocker.EMPTY_OUTPUT -> {
                displayOutputOnToolWindow(params.project, file, "No bytecode is generated")
                notify(
                    params.project, file, COMPILATION_EMPTY,
                    "<html>No bytecode is generated</html>",
                    VyperMessageProcessor.NotificationStatusVyper.WARNING
                )
            }
        }
        return result
    }

    private fun notify(
        project: Project, file: VirtualFile, title: String, htmlWithLink: String,
        status: VyperMessageProcessor.NotificationStatusVyper
    ) = VyperMessageProcessor.notificateInBalloon(
        VyperMessageProcessor.VyperNotification(
            { notification, _ ->
                val open = OpenFileDescriptor(
                    project, file,
                    0, 0
                )
                open.navigate(true)
                notification.expire()
            }, title, htmlWithLink, status,
            VyperMessageProcessor.NotificationGroupVyper.COMPILER, project
        )
    )

    private fun displayOutputOnToolWindow(project: Project, file: VirtualFile, output: String) =
        ApplicationManager.getApplication().invokeLater({
            VyperWindow.replaceTextInTabsWindow(
                project,
                VyperWindow.VyperWindowTab.COMPILER_TAB,
                // split output into multiple lines, so it can be displayed in the tool window
                "${file.path}:\n${output.chunked(100).joinToString("\n")}"
            )
        }, ModalityState.any())
}
