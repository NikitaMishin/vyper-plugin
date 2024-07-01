package org.vyperlang.plugin.compile

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.vyperlang.plugin.VyperMessageProcessor
import org.vyperlang.plugin.VyperStubGenerator
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.VyperCompilerDocker
import org.vyperlang.plugin.toolWindow.VyperWindow
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport


data class VyperParameters(
    val module: Module,
    val project: Project,
    val files: Array<VirtualFile>,
    val compilerParameters: Array<String>,
    val generateStub: Boolean,
    val stubExtension: String
)

object VyperCompiler {
    data class CompilerError(val msg: String, val line: Int)
    data class CompilerMessage(val file: VirtualFile, val error: List<CompilerError>)

    private val propertyChangeSupport = PropertyChangeSupport(this)

    internal var output: String? = null
        private set

    fun addListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    private const val COMPILATION_FAILED = "Compilation failed"
    private const val COMPILATION_EMPTY = "Compilation empty"
    private const val COMPILATION_SUCCESS = "Compilation succeed"
    private const val COMPILATION_NAVIGATE_HTML = "<html><a href=cite>navigate to file</a></html>"
    private const val COMPILATION_EMPTY_HTML = "<html>No bytecode is generated</html>"
    private const val BYTECODE_IN_WINDOW_HTML = "<html>Bytecode in tool window</html>"

    fun compile(params: VyperParameters) {
        for (file in params.files) {
            val parent = file.parent.path
            val fullPath = file.path
            val result = VyperCompilerDocker(parent, fullPath, params.compilerParameters).execWrapper(params.project)

            when {
                params.generateStub && result.statusDocker == StatusDocker.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                    VyperStubGenerator.createStubInGenSourceFolder(
                        result.stdout, params.module, params.project, fullPath, params.stubExtension
                    )
                    notify(
                        params.project, file, COMPILATION_SUCCESS,
                        "<html>Bytecode in tool window and in generated folder</html>",
                        VyperMessageProcessor.NotificationStatusVyper.INFO
                    )

                }
                !params.generateStub && result.statusDocker == StatusDocker.SUCCESS -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stdout)
                    notify(
                        params.project, file, COMPILATION_SUCCESS,
                        BYTECODE_IN_WINDOW_HTML,
                        VyperMessageProcessor.NotificationStatusVyper.INFO
                    )
                }
                params.generateStub && result.statusDocker == StatusDocker.FAILED -> {
                    displayOutputOnToolWindow(params.project, file.path, result.stderr)

                    addMessage(CompilerMessage(file, parseCompilerOutput(result.stderr)))

                    notify(
                        params.project, file, COMPILATION_FAILED,
                        COMPILATION_NAVIGATE_HTML,
                        VyperMessageProcessor.NotificationStatusVyper.ERROR
                    )
                }

                !params.generateStub && result.statusDocker == StatusDocker.FAILED -> {

                    displayOutputOnToolWindow(params.project, file.path, result.stderr)

                    addMessage(CompilerMessage(file, parseCompilerOutput(result.stderr)))

                    notify(
                        params.project, file, COMPILATION_FAILED,
                        COMPILATION_NAVIGATE_HTML,
                        VyperMessageProcessor.NotificationStatusVyper.ERROR
                    )
                }

                !params.generateStub && result.statusDocker == StatusDocker.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    notify(
                        params.project, file, COMPILATION_EMPTY,
                        COMPILATION_EMPTY_HTML,
                        VyperMessageProcessor.NotificationStatusVyper.WARNING
                    )
                }
                params.generateStub && result.statusDocker == StatusDocker.EMPTY_OUTPUT -> {
                    displayOutputOnToolWindow(params.project, file.path, "")
                    notify(
                        params.project, file, COMPILATION_EMPTY,
                        COMPILATION_EMPTY_HTML,
                        VyperMessageProcessor.NotificationStatusVyper.WARNING
                    )
                }
            }

        }
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

    private fun parseCompilerOutput(stderr: String): List<CompilerError> {
        val arrRegBase = regBaseError.findAll(stderr).toMutableList().map {
            CompilerError(it.groups[0]!!.value, it.groups[1]!!.value.toInt())
        }.toMutableList()
        val arrRegError = regError.findAll(stderr).toList().map {
            CompilerError(it.groups[0]!!.value, it.groups[1]!!.value.toInt())
        }
        arrRegBase.addAll(arrRegError)
        return arrRegBase
    }

    private fun addMessage(message: CompilerMessage) {
        propertyChangeSupport.firePropertyChange("COMPILER", null, message)
    }

    private fun displayOutputOnToolWindow(project: Project, path: String, output: String) {
        this.output = output
        ApplicationManager.getApplication().invokeLater({
            VyperWindow.replaceTextInTabsWindow(
                project, VyperWindow.VyperWindowTab.COMPILER_TAB, "$path:\n$output"
            )
        }, ModalityState.any())
    }
}
