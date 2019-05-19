package com.vyperplugin.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBScrollPane
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.EventQueue
import javax.swing.JTextPane
import javax.swing.text.DefaultCaret

/**
 * Window Tool for Vyper
 *
 */
object VyperWindow {
    private const val ID_TOOL_WINDOW = "Vyper_Tools" // also dispay name name
    private const val ID_COMPILER_TAB = "Compiler_output"
    private const val ID_RUN_TAB = "Run_output"
    private const val ID_ANALYZE_TAB = "Analyze_output"


    private const val NAME_TAB_TEXT_PANE = "Compiler_text_pane"

    enum class VyperWindowTab {
        COMPILER_TAB,
        RUN_TAB,
        ANALYZE_TAB
    }

    private fun convert(vyperWindowTab: VyperWindowTab): String = when (vyperWindowTab) {
        VyperWindow.VyperWindowTab.COMPILER_TAB -> ID_COMPILER_TAB
        VyperWindow.VyperWindowTab.RUN_TAB -> ID_RUN_TAB
        VyperWindow.VyperWindowTab.ANALYZE_TAB -> ID_ANALYZE_TAB
    }


    /**
     * Append @param text to the end of tab window in @param project
     * Should be called in main thread.
     * For example use ApplicationManager.getApplication().invokeLater()
    }
     */
    fun appendTextToTabsWindow(project: Project, vyperWindowTab: VyperWindowTab, text: String) =
            EventQueue.invokeLater {
                val id = convert(vyperWindowTab)
                val doc = getTabById(project, id).document
                doc.insertString(doc.length, text, null)
            }


    /**
     * Replace existing text in output with @param replace in @param project
     */
    fun replaceTextInTabsWindow(project: Project, vyperWindowTab: VyperWindowTab, replace: String) =
            EventQueue.invokeLater {
                val id = convert(vyperWindowTab)
                val doc = getTabById(project, id).document
                doc.remove(0, doc.length)
                doc.insertString(doc.length, replace, null)
            }


    /**
     * Creates  tab with scrollable panel with JTextPane for specified @param toolWindow
     */
    private fun createTab(toolWindow: ToolWindow, tabName: String) {
        val textPane = JTextPane()
        textPane.name = NAME_TAB_TEXT_PANE
        textPane.isEditable = false
        val scrollPane = JBScrollPane(textPane)
        val caret = textPane.caret as DefaultCaret
        caret.updatePolicy = DefaultCaret.ALWAYS_UPDATE
        textPane.contentType = "text/html"
        textPane.background = Color.WHITE
        textPane.text = ""
        val content = toolWindow.contentManager.factory.createContent(scrollPane, tabName, false)
        toolWindow.contentManager.addContent(content)
    }


    /**
     * Return concrete component where output would be written
     * If not exists then it would be created
     */
    private fun getTabById(project: Project, tabId: String): JTextPane {
        var toolWindow = ToolWindowManager.getInstance(project).getToolWindow(ID_TOOL_WINDOW)
        if (toolWindow == null) {
            toolWindow = ToolWindowManager.getInstance(project)
                    .registerToolWindow(ID_TOOL_WINDOW, true, ToolWindowAnchor.BOTTOM)
            createTab(toolWindow, tabId)
        }

        var tab = toolWindow.contentManager.findContent(tabId)
        if (tab == null) {
            //user close this tab
            //toolWindow
            createTab(toolWindow, tabId)
            tab = toolWindow.contentManager.findContent(tabId)
        }

        val textPane = findComponentByUniqueName(NAME_TAB_TEXT_PANE, tab.component)

        if (textPane != null) {
            ToolWindowManager.getInstance(project).focusManager.requestFocusInProject(textPane, project)
            return textPane as JTextPane
        }

        throw ClassNotFoundException("No pane exists")
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