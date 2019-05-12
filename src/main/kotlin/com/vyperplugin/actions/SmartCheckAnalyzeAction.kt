package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.docker.SmartCheckDocker
import com.vyperplugin.gui.smartcheck.AnalysisInformationDialogue
import com.vyperplugin.gui.smartcheck.AnalysisWaitDialogue
import com.vyperplugin.gui.smartcheck.AnalysisWaitDialogue.*
import com.vyperplugin.gui.smartcheck.NoFilesWithVyperAreSelectedDialogue
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import kotlin.concurrent.thread


class SmartCheckAnalyzeAction : VyperAction() {

    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {

        val files: Array<VirtualFile>? = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            return NoFilesWithVyperAreSelectedDialogue().display()
        }
        //show dialog with progress bar
        val startAnalysisDialog = AnalysisWaitDialogue()
        val analysisListener = SmartCheckAnalysisListener(startAnalysisDialog)

        startAnalysisDialog.addPropertyChangeListener(analysisListener)

        thread(start = true) {
            startAnalyze(files, analysisListener)
        }
        startAnalysisDialog.display()

    }


    private fun startAnalyze(filenames: Array<VirtualFile>, propertyChangeListener: SmartCheckAnalysisListener) {

        val analysisInformation: MutableList<Pair<String, String>> = mutableListOf()
        for (i in 0 until filenames.size) {
            if (propertyChangeListener.statusAnalysis == ANALYSIS_CANCELLED) {
                return
            }
            analysisInformation.add(Pair(filenames[i].path, translateAnalysisInfoToString(analyzeVyperFile(filenames[i]))))
        }

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

    private fun analyzeVyperFile(file: VirtualFile): List<String> =
            SmartCheckDocker(file.parent.path, file.path).exec().stdout.lines()

    private fun translateAnalysisInfoToString(information: List<String>): String {
        return "<html>${information.reduce { acc, elem -> acc.plus("<p>" + elem + "</p>") }}<p>Completed</p></html>"
    }

}


// follow to cancel operation in form and complete result in actual analysis
class SmartCheckAnalysisListener(private val startAnalysis: AnalysisWaitDialogue) : PropertyChangeListener {
    var statusAnalysis: String? = ANALYSIS_START
    override fun propertyChange(p0: PropertyChangeEvent?) {
        if (p0?.propertyName.equals(ANALYSIS_CANCELLED)) {
            startAnalysis.close()
            statusAnalysis = ANALYSIS_CANCELLED
        } else if (p0?.propertyName.equals(ANALYSIS_FINISHED)) {
            statusAnalysis = ANALYSIS_FINISHED
            startAnalysis.close()
        }
    }
}

