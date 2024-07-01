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
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.docker.VyperCompilerDocker
import org.vyperlang.plugin.toolWindow.VyperWindow
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport


data class VyperParameters(
    val module: Module,
    val project: Project,
    val files: List<VirtualFile>,
    val compilerParameters: List<String>,
    val generateStub: Boolean,
    val stubExtension: String
)

object VyperCompiler {
    data class CompilerError(val msg: String, val line: Int)
    data class CompilerMessage(val file: VirtualFile, val error: List<CompilerError>)

    private val propertyChangeSupport = PropertyChangeSupport(this)

    fun addListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    private const val COMPILATION_FAILED = "Compilation failed"
    private const val COMPILATION_EMPTY = "Compilation empty"
    private const val COMPILATION_SUCCESS = "Compilation succeed"

    fun compile(params: VyperParameters, indicator: ProgressIndicator) = params.files.map {
        val compiler = VyperCompilerDocker(params.project, it, indicator, *params.compilerParameters.toTypedArray())
        reportResult(it, params, compiler.run())
    }

    private fun reportResult(
        file: VirtualFile,
        params: VyperParameters,
        result: ToolResult?
    ): ToolResult? {
        when (result?.statusDocker) {
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
                addMessage(CompilerMessage(file, parseCompilerOutput(result.stderr)))
                notify(
                    params.project, file, COMPILATION_FAILED,
                    "<html>Vyper failed to compile</html>",
                    VyperMessageProcessor.NotificationStatusVyper.ERROR
                )
            }

            StatusDocker.EMPTY_OUTPUT -> {
                displayOutputOnToolWindow(params.project, file, listOf("No bytecode is generated"))
                notify(
                    params.project, file, COMPILATION_EMPTY,
                    "<html>No bytecode is generated</html>",
                    VyperMessageProcessor.NotificationStatusVyper.WARNING
                )
            }

            null -> {} // ignore, error is reported by `VyperCompilerDocker`
        }
        return result
    }

    private fun notify(
        project: Project, file: VirtualFile, title: String, htmlWithLink: String,
        status: VyperMessageProcessor.NotificationStatusVyper
    ) {
        VyperMessageProcessor.notificateInBalloon(
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
    }

    private val regBaseError =
        Regex(
            """vyper\.exceptions\.\w+Exception:\s+line\s+(\d+).*$""",
            setOf(RegexOption.MULTILINE)
        )
    private val regError =
        Regex("""File.+,\s+line\s+(\d+)\s[^>]*""", setOf(RegexOption.MULTILINE))

    private fun parseCompilerOutput(lines: List<String>): List<CompilerError> {
        val stderr = lines.joinToString("\n")
        val arrRegBase = regBaseError.findAll(stderr).toMutableList().map {
            CompilerError(it.groups[0]!!.value, it.groups[1]!!.value.toInt())
        }.toMutableList()
        val arrRegError = regError.findAll(stderr).toList().map {
            CompilerError(it.groups[0]!!.value, it.groups[1]!!.value.toInt())
        }
        arrRegBase.addAll(arrRegError)
        return arrRegBase
    }

    private fun addMessage(message: CompilerMessage) =
        propertyChangeSupport.firePropertyChange("COMPILER", null, message)

    private fun displayOutputOnToolWindow(project: Project, file: VirtualFile, output: List<String>) =
        ApplicationManager.getApplication().invokeLater({
            VyperWindow.replaceTextInTabsWindow(
                project,
                VyperWindow.VyperWindowTab.COMPILER_TAB,
                "${file.path}:\n${output.joinToString("\n")}"
            )
        }, ModalityState.any())
}
