package com.vyperplugin.analyze


import com.intellij.openapi.project.Project
import com.vyperplugin.VyperMessageProcessor
import com.vyperplugin.internalApi.mythx.APIMythX
import com.vyperplugin.internalApi.mythx.DetectedIssue
import com.vyperplugin.settings.VyperSettings
import com.vyperplugin.toolWindow.VyperWindow
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


object MythXAnalyzer {

    private val api = APIMythX()

    // token alive for ten minutes
    fun analyze(bytecode: String, deployedBytecode: String, fileName: String, project: Project) {
        val settings = VyperSettings.INSTANCE
        val (address, pass) = if (settings.usesCustomAccount) {
            Pair(settings.address, settings.password)
        } else {
            Pair(settings.defaultAddress, settings.defaultPassword)
        }

        GlobalScope.async {
            try {
                val submitResponse = api.partialAnalyze(
                        api.login(address, pass).first!!.jwtTokens,
                        bytecode, deployedBytecode
                )

                if (submitResponse.first!!.status == "ERROR") {
                    notify(project, fileName, "<html>Some errors in submitted code, " +
                            "please check " + "correctness of submitted code for $fileName</html>",
                            VyperMessageProcessor.NotificationStatusVyper.ERROR)
                    return@async
                }


                for (i in 1..api.retryCounts) {

                    val token = api.login(address, pass).first!!.jwtTokens
                    val status = api.getAnalysisStatus(submitResponse.first!!.uuid, token)

                    if (status.first!!.status == "Error") {
                        notify(project, fileName, "<html>Some errors in submitted code, " +
                                "please check " + "correctness of submitted code for $fileName</html>",
                                VyperMessageProcessor.NotificationStatusVyper.ERROR)
                        return@async
                    }
                    if (status.first!!.status == "Finished") {
                        val detectedIssues = api.getDetectedIssue(submitResponse.first!!.uuid, token).first!!
                        processResult(detectedIssues, fileName, project)
                        return@async
                    }

                    delay(api.waitTimeOfAnalysis)
                }
            } catch (e: Exception) {
                notify(project, fileName, "<html>Some errors occurred, please check your" +
                        " password with username or correctness of submitted code in file $fileName</html>",
                        VyperMessageProcessor.NotificationStatusVyper.ERROR)
            }
        }

    }

    private fun processResult(detectedIssues: List<DetectedIssue>, fileName: String, project: Project) {

        var isIssueFound = false
        var buildOutput: StringBuilder = java.lang.StringBuilder()

        buildOutput = buildOutput.append("Analysis with MythX for $fileName :\n")

        detectedIssues.forEach {
            it.issues?.forEach {
                isIssueFound = true

                //diplay to user
                buildOutput = buildOutput.append(
                        """
                                severity:${it.severity}
                                swcTitle:${it.swcID}
                                description of vulnerabilities:
                                    ${it.description.head}
                                    ${it.description.tail}
                                location:

                        """.trimIndent()
                )
                it.locations?.forEachIndexed { index, item ->
                    buildOutput = buildOutput.append("\n${index + 1}) sourceMap:${item.sourceMap} \n")
                }
            }
        }

        if (isIssueFound) {
            // notify user
            notify(project, fileName, "<html>$fileName contains vulnerabilities. " +
                    "See toolWindow for details </html>",
                    VyperMessageProcessor.NotificationStatusVyper.WARNING)
            // add to tab analyze
            VyperWindow.appendTextToTabsWindow(project, VyperWindow.VyperWindowTab.ANALYZE_TAB, "\n$buildOutput")

        } else {
            notify(project, fileName, "<html>$fileName doesn't contain vulnerabilities </html>",
                    VyperMessageProcessor.NotificationStatusVyper.INFO)
        }
    }


    private fun notify(project: Project, fileName: String, html: String,
                       status: VyperMessageProcessor.NotificationStatusVyper) {
        VyperMessageProcessor.notificateInBalloon(
                VyperMessageProcessor.VyperNotification(
                        null, "MythX", html, status,
                        VyperMessageProcessor.NotificationGroupVyper.COMMON, project
                )
        )
    }

}