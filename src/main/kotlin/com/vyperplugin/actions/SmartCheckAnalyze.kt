@file:Suppress("ObsoleteExperimentalCoroutines")

package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.docker.SmartCheckDocker
import com.vyperplugin.gui.smartcheck.AnalysisInformationDialogue
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue
import com.vyperplugin.gui.smartcheck.StartAnalysisDialogue
import com.vyperplugin.gui.smartcheck.StartAnalysisDialogue.ANALYSIS_CANCELLED
import com.vyperplugin.gui.smartcheck.StartAnalysisDialogue.ANALYSIS_FINISHED
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener


class SmartCheckAnalyze(private val vyExtensionRegExp: Regex = Regex(".+\\.vy$")) : AnAction() {

    private val smartcheckDocker =  SmartCheckDocker()

    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {
        val files: Array<VirtualFile>? = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }

        //show dialog with progress bar
        val startAnalysisDialog = StartAnalysisDialogue()
        val analysisListener = SmartCheckAnalysisListener(startAnalysisDialog)

        startAnalysisDialog.addPropertyChangeListener(analysisListener)
        GlobalScope.async {
            startAnalysisDialog.display()
        }
        startAnalyze(files, analysisListener)

    }


    private fun getClickedFiles(e: AnActionEvent): Array<VirtualFile>? =
            PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(e.dataContext)


    private fun startAnalyze(filenames: Array<VirtualFile>, propertyChangeListener: PropertyChangeListener) {
        // i think it should be blocking call
        val analysisInformation = filenames
                .map { Pair(it.path, translateAnalysisInfoToString(analyzeVyperFile(it))) }

//        //wait for finish
//        // when finish manually call to close
        propertyChangeListener.propertyChange(
                PropertyChangeEvent(this, ANALYSIS_FINISHED, null, null))

        analysisInformation.forEach {
            run {
                val frame = AnalysisInformationDialogue()
                frame.setAnalysisInfo(it.first, it.second)
                frame.display()
            }
        }

    }

    private fun analyzeVyperFile(file: VirtualFile): List<String> = smartcheckDocker
                .analyzeFileinBindDir(file.parent.path, file.path.split("/").last())

    private fun translateAnalysisInfoToString(information: List<String>): String {
        return "<html>${information.reduce { acc, elem -> acc.plus("<p>"+elem+"</p>") }}<p>Completed</p></html>"
    }

}


// follow to cancel operation in form and complete result in actual analysis
class SmartCheckAnalysisListener(private val startAnalysis: StartAnalysisDialogue) : PropertyChangeListener {
    override fun propertyChange(p0: PropertyChangeEvent?) {
        if (p0?.propertyName.equals(ANALYSIS_CANCELLED)) {
            startAnalysis.close()
        } else if (p0?.propertyName.equals(ANALYSIS_FINISHED)) {
            startAnalysis.close()
        }
    }
}

