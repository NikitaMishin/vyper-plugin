package com.vyperplugin.gui

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.run.VyperTestParameters
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import javax.swing.*

class VyperRunContractMultiple(
    private var project: Project,
    private var module: Module,
    private var file: VirtualFile
) : JDialog() {

    lateinit var contentPane: JPanel
    lateinit var buttonRun: JButton
    lateinit var buttonCancel: JButton
    lateinit var initArgs: JTextArea
    lateinit var contractName: JTextField
    lateinit var functionCalls: JTextArea

    private var propertyChangeSupport = PropertyChangeSupport(this)

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    fun display() {
        this.contractName.text = file.name
        this.contractName.isEditable = false
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    private fun onRun() {
        buttonRun.isEnabled = false

        val testInput =
            VyperTestParameters(
                project, module, file,
                functionCalls.text,
                initArgs.text.lines().filter { it != "" }.toTypedArray(),
                arrayOf()
            )

        propertyChangeSupport.firePropertyChange(VyperRunContractCase.MULTIPLE, null, testInput)
        dispose()
    }


    init {
        setContentPane(contentPane)
        isModal = true
        getRootPane().defaultButton = buttonRun

        buttonRun.addActionListener { onRun() }

        buttonCancel.addActionListener { onCancel() }

        // call onCancel() when cross is clicked
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                onCancel()
            }
        })

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
            { onCancel() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )
    }


    private fun onCancel() {
        dispose()
    }
}
