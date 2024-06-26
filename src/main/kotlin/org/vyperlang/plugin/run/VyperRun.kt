package org.vyperlang.plugin.run

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.vyperlang.plugin.VyperMessageProcessor
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.docker.VyperRunDocker
import org.vyperlang.plugin.toolWindow.VyperWindow


/**
 * represent input for testing
 * @callSequence provides either single method name "method"
 * or sequence of calls with arguments "method1(arg1,arg2);method2();...methodn(argn)"
 * @initArgs either empty if __init__() without args or string of "arg1,arg2,arg3"
 * @callArgs either  empty if called method has no args and called several methods or "arg1,arg2,argn"
 */
data class VyperTestParameters(
    var project: Project,
    val module: Module,
    var file: VirtualFile,
    var callSequence: String,
    var initArgs: Array<String>,
    var callArgs: Array<String>
)

object VyperRun {


    private fun exec(
        bindDir: String,
        fullFilePath: String,
        callSequence: String,
        init: Array<String>,
        project: Project
    ): ToolResult {

        return VyperRunDocker(bindDir, fullFilePath, callSequence, init).execWrapper(project)
    }

    /**
     * test single method of contract with vyper-run inside docker container
     */
    fun testContractSingleMethod(testParams: VyperTestParameters) {
        val pathToFile = testParams.file.path
        val bindDir = testParams.file.parent.path
        val funcName = testParams.callSequence
        val funcArgs = testParams.callArgs.joinToString(",")
        val res = exec(bindDir, pathToFile, "$funcName($funcArgs)", testParams.initArgs, testParams.project)
        processResult(testParams, res)
    }


    /**
     * test multiple methods of contract with vyper-run inside docker container
     */
    fun testContractMultipleMethod(testParams: VyperTestParameters) {
        val pathToFile = testParams.file.path
        val bindDir = testParams.file.parent.path
        val callSequence = testParams.callSequence
        val res = exec(bindDir, pathToFile, callSequence, testParams.initArgs, testParams.project)
        processResult(testParams, res)
    }


    private fun processResult(testParams: VyperTestParameters, res: ToolResult) {
        when (res.statusDocker) {
            StatusDocker.SUCCESS -> {
                VyperWindow.replaceTextInTabsWindow(
                    testParams.project, VyperWindow.VyperWindowTab.RUN_TAB, res.stdout
                )
                notify(
                    "Success", VyperMessageProcessor.NotificationStatusVyper.INFO,
                    testParams.project
                )
            }
            StatusDocker.FAILED -> {
                VyperWindow.replaceTextInTabsWindow(
                    testParams.project, VyperWindow.VyperWindowTab.RUN_TAB, res.stderr
                )
                notify(
                    "Failed", VyperMessageProcessor.NotificationStatusVyper.ERROR,
                    testParams.project
                )
            }
            StatusDocker.EMPTY_OUTPUT -> {
                notify(
                    "Empty output", VyperMessageProcessor.NotificationStatusVyper.WARNING,
                    testParams.project
                )
            }
            else -> {
                // internal error just skip already notified user
            }
        }
    }

    private fun notify(html: String, status: VyperMessageProcessor.NotificationStatusVyper, project: Project) {
        VyperMessageProcessor.notificateInBalloon(
            VyperMessageProcessor.VyperNotification(
                null, "vyper-run", html,
                status, VyperMessageProcessor.NotificationGroupVyper.RUN, project
            )
        )

    }
}
