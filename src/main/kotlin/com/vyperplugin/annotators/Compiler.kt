package com.vyperplugin.annotators


import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.codeInsight.daemon.impl.AnnotationHolderImpl
import com.intellij.codeInsight.highlighting.HighlightManager
import com.intellij.codeInsight.highlighting.HighlightManagerImpl
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.AnnotationSession
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.vyperplugin.analyze.VyperAnalyzer
import com.vyperplugin.psi.VyperFile
import com.vyperplugin.toolWindow.VyperWindow
import java.awt.Color
import java.awt.Font
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.io.File

class VyperCompilerListener(val project: Project) : PropertyChangeListener {
    override fun propertyChange(evt: PropertyChangeEvent?) {

        val data = evt!!.newValue as VyperAnalyzer.SmartCheckData
        //TODO : compiler and implement this for all messages
        ApplicationManager.getApplication().runReadAction {
            val virtFile = VfsUtil.findFile(File("/home/gerwant/IdeaProjects/test/src/com/company/test.vy").toPath(), true)
            val psiFile = PsiManager.getInstance(project).findFile(virtFile!!)
            //what if user picks another file?
            val dm = PsiDocumentManager.getInstance(project).getDocument(psiFile!!)
            val start = dm!!.getLineStartOffset(data.smartCheckData[0].line - 1)
            val end = dm.getLineEndOffset(data.smartCheckData[0].line - 1)
            val message = data.smartCheckData[0].ruleId
            CompilerOutput.messages.add(CompilerMessage(TextRange(start,end),message))
            DaemonCodeAnalyzer.getInstance(project).restart()
//            CompilerOutput.messages = mutableListOf()

        }
    }


    fun listenAnalyssis() {
        VyperAnalyzer.addListener(this)
    }

}

class CompilerAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperFile) {
            for (message in CompilerOutput.messages) {
                holder.createErrorAnnotation(message.range, message.message)
            }
            CompilerOutput.messages = mutableListOf()
        }
    }
}

object CompilerOutput {
    var messages : MutableList<CompilerMessage> = mutableListOf()
}

data class CompilerMessage(val range: TextRange, val message : String )