package com.vyperplugin.annotators


import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.vyperplugin.analyze.VyperAnalyzer
import com.vyperplugin.psi.VyperFile
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.io.File

class VyperSmartCheckListener(val project: Project) : PropertyChangeListener {
    override fun propertyChange(evt: PropertyChangeEvent?) {

        val data = evt!!.newValue as VyperAnalyzer.SmartCheckData
        //TODO : compiler and implement this for all messages
        ApplicationManager.getApplication().runReadAction {
//            val virtFile = VfsUtil.findFile(File("/home/gerwant/IdeaProjects/test/src/com/company/test.vy").toPath(), true)
            val psiFile = PsiManager.getInstance(project).findFile(data.file)
            //what if user picks another file?
            val dm = PsiDocumentManager.getInstance(project).getDocument(psiFile!!)
            for(report in data.smartCheckData) {
                val start = dm!!.getLineStartOffset(report.line - 1)
                val end = dm.getLineEndOffset(report.line - 1)
                val message = report.ruleId
                SmartCheckOutput.messages.add(SmartCheckMessage(TextRange(start, end), message))
            }
            DaemonCodeAnalyzer.getInstance(project).restart()


        }
    }


    fun listenAnalysis() {
        VyperAnalyzer.addListener(this)
    }

}

class SmartCheckAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperFile) {
            for (message in SmartCheckOutput.messages) {
                holder.createWeakWarningAnnotation(message.range, message.message)
            }
            SmartCheckOutput.messages = mutableListOf()
        }
    }
}

object SmartCheckOutput {
    var messages : MutableList<SmartCheckMessage> = mutableListOf()
}

data class SmartCheckMessage(val range: TextRange, val message : String )