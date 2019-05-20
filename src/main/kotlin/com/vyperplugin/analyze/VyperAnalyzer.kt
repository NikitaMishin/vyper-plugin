package com.vyperplugin.analyze

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.VyperMessageProcessor
import com.vyperplugin.docker.SmartCheckDocker
import com.vyperplugin.docker.StatusDocker
import com.vyperplugin.toolWindow.VyperWindow
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

object VyperAnalyzer {

    data class SmartCheckData(
            val smartCheckData: Array<SmartCheckSinglePattern>,
            val file: VirtualFile
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SmartCheckData

            if (!smartCheckData.contentEquals(other.smartCheckData)) return false
            if (file != other.file) return false

            return true
        }

        override fun hashCode(): Int {
            var result = smartCheckData.contentHashCode()
            result = 31 * result + file.hashCode()
            return result
        }
    }

    data class SmartCheckSinglePattern(
            val ruleId: String,
            val patternId: String,
            val severity: String,
            val line: Int,
            val column: Int
    )


    private val propertyChangeSupport = PropertyChangeSupport(this)

    fun addListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    fun removeListener(listener: PropertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(listener)
    }


    private fun addMessage(data: SmartCheckData) {
        propertyChangeSupport.firePropertyChange("ANALYSIS_SMART_CHECK", null, data)
    }

    private val pattern = Regex(
            "ruleId: (\\w+)\npatternId: (\\w+)\nseverity: (\\w+)\nline: (\\d+)\ncolumn: (\\d+)")

    fun smartCheckAnalyze(files: Array<VirtualFile>, project: Project, module: Module) {
        files.map {
            val res = SmartCheckDocker(it.parent.path, it.path).execWrapper(project)
            when (res.statusDocker) {
                StatusDocker.SUCCESS -> {
                    val p = it.path
                    val arr = mutableListOf<SmartCheckSinglePattern>()
                    pattern.findAll(res.stdout).toList().map {
                        val data = SmartCheckSinglePattern(
                                it.groups[1]!!.value,
                                it.groups[2]!!.value,
                                it.groups[3]!!.value,
                                it.groups[4]!!.value.toInt(),
                                it.groups[5]!!.value.toInt()
                        )
                        arr.add(data)
                    }
                    VyperWindow.appendTextToTabsWindow(project, VyperWindow.VyperWindowTab.ANALYZE_TAB,
                            "$p:\n" + res.stdout.drop(1) + '\n')
                    addMessage(SmartCheckData(arr.toTypedArray(), it))
                }
                else -> {
                    //show nothing
                }
            }
        }

        VyperMessageProcessor.notificateInBalloon(
                VyperMessageProcessor.VyperNotification(
                        null, "SmartCheck",
                        "<html>analyzed of files finished. See detail in tool window</html>",
                        VyperMessageProcessor.NotificationStatusVyper.INFO,
                        VyperMessageProcessor.NotificationGroupVyper.ANALYZE,
                        project
                )
        )
    }
}