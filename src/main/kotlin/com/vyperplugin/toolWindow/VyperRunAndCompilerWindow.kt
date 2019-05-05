package com.vyperplugin.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBScrollPane
import java.awt.Color
import java.awt.Component
import java.awt.Container
import javax.swing.JTextPane
import javax.swing.text.DefaultCaret

/**
 * Window Tool for compiler and run output
 *
 */
object VyperRunAndCompilerWindow {

    val ID_COMPILER_RUN_WINDOW = "Vyper_Tools" // also dispay name name
    val ID_COMPILER_TAB = "Compiler_output"
    val NAME_COMPILER_TEXT_PANE = "Compiler_text_pane"

    /**
     * Append @param text to the end of Compiler window in @param project
     * Should be called in main thread.
     * For example use ApplicationManager.getApplication().invokeLater()
    }
     */
    fun appendTextToCompilerWindow(project: Project, text: String) {
        val doc = getCompilerWindow(project).document
        doc.insertString(doc.length, text, null)
    }

    /**
     * Replace existing text in output with @param replace in @param project
     * Should be called in main thread
     */
    fun replaceTextInCompilerWindow(project: Project, replace: String) {
        val doc = getCompilerWindow(project).document
        doc.remove(0, doc.length)
        doc.insertString(doc.length, replace, null)
    }


    /**
     * Creates compiler tab with scrollable panel with JTextPane for specified @param toolWindow
     */
    private fun createCompilerTab(toolWindow: ToolWindow) {
        val textPane = JTextPane()
        textPane.name = NAME_COMPILER_TEXT_PANE
        textPane.isEditable = false
        val scrollPane = JBScrollPane(textPane)
        val caret = textPane.caret as DefaultCaret
        caret.updatePolicy = DefaultCaret.ALWAYS_UPDATE
        textPane.contentType = "text/html"
        textPane.background = Color.WHITE
        textPane.text = ""
        val content = toolWindow.contentManager.factory.createContent(scrollPane, ID_COMPILER_TAB, false)
        toolWindow.contentManager.addContent(content)
    }


    /**
     * Return concrete component where output would be written
     * If not exists then would be created
     */
    private fun getCompilerWindow(project: Project): JTextPane {
        var toolWindow = ToolWindowManager.getInstance(project).getToolWindow(ID_COMPILER_RUN_WINDOW)
        if (toolWindow == null) {
            toolWindow = ToolWindowManager.getInstance(project)
                    .registerToolWindow(ID_COMPILER_RUN_WINDOW, true, ToolWindowAnchor.BOTTOM)
            createCompilerTab(toolWindow)
        }

        var tab = toolWindow.contentManager.findContent(ID_COMPILER_TAB)
        if (tab == null) {
            //user close this tab
            //toolWindow
            createCompilerTab(toolWindow)
            tab = toolWindow.contentManager.findContent(ID_COMPILER_TAB)
        }

        val textPane = findComponentByUniqueName(NAME_COMPILER_TEXT_PANE, tab.component)

        if (textPane != null) {
            ToolWindowManager.getInstance(project).focusManager.requestFocusInProject(textPane, project)
            return textPane as JTextPane
        }

        throw ClassNotFoundException("No pane exists ")
    }


    private fun findComponentByUniqueName(name: String, parent: Container, depth: Int = 2): Component? {
        if (depth <= 0) return null

        for (p in parent.components) {
            if (p.name == name) {
                return p
            } else {
                if (p !is Container) continue
                val res = findComponentByUniqueName(name, p, depth - 1)
                if (res != null) return res
            }
        }
        return null
    }

}