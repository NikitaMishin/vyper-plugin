package com.vyperplugin.editorActions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.editor.actionSystem.TypedActionHandler

class MyTypedHandler : TypedActionHandler {

    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
        val document = editor.document
        val project = editor.project
        val runnable: Runnable = Runnable { document.insertString(0, "\" Created by gerwant \"\n\n") }
        WriteCommandAction.runWriteCommandAction(project, runnable)

    }
}


class EditorIllustration : AnAction() {
    init {
        val actionManager = EditorActionManager.getInstance()
        val typedAction = actionManager.typedAction
        typedAction.setupHandler(MyTypedHandler())
    }


    override fun actionPerformed(e: AnActionEvent) {
        //Get all the required data from data keys
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        //Access document, caret, and selection
        val document = editor.document
        val selectionModel = editor.selectionModel

        val start = selectionModel.selectionStart
        val end = selectionModel.selectionEnd
        //Making the replacement
        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(start, end, "Replacement")
        }
        selectionModel.removeSelection()
    }


    override fun update(e: AnActionEvent) {
        //Get required data keys
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        //Set visibility only in case of existing project and editor and if some text in the editor is selected
        e.presentation.isVisible = (project != null && editor != null && editor.selectionModel.hasSelection())
    }
}