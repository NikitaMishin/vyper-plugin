package com.vyperplugin.gui


import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.vyperplugin.run.VyperTestParameters
import java.awt.GridLayout
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import javax.swing.*

object VyperRunContractCase {
    const val SINGLE = "SINGLE"
    const val MULTIPLE = "MULTIPLE"
}


//TODO Browsable contract, more friendly UI
class VyperRunMenuSingle(
    private var project: Project,
    private var module: Module,
    private var file: VirtualFile
) : JDialog() {

    private lateinit var contentPane: JPanel
    private lateinit var buttonRun: JButton
    private lateinit var buttonCancel: JButton
    private lateinit var contractName: JTextField
    private lateinit var funcName: JTextField

    private lateinit var initArgsPanel: JPanel
    private lateinit var funcArgsPanel: JPanel

    private lateinit var initArgsArray: Array<JTextField>
    private lateinit var funcArgsArray: Array<JTextField>


    private var propertyChangeSupport = PropertyChangeSupport(this)

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    /**
     * @args  names of arguments of function method
     * @init names of  arguments of init method
     */
    private fun addFields(args: Array<String>, init: Array<String>) {
        if (init.isNotEmpty()) {
            initArgsPanel.layout = GridLayout(init.size, 2, 5, 12)
            initArgsArray = init.map {
                run {
                    val label = JLabel("$it:")
                    val textField = JTextField()
                    initArgsPanel.add(label, GridLayout())
                    initArgsPanel.add(textField, GridLayout())
                    textField
                }
            }.toTypedArray()
        } else {
            initArgsArray = arrayOf()
        }

        if (args.isNotEmpty()) {
            funcArgsPanel.layout = GridLayout(args.size, 2, 5, 12)
            funcArgsArray = args.map {
                run {
                    val label = JLabel("$it:")
                    val textField = JTextField()
                    funcArgsPanel.add(label, GridLayout())
                    funcArgsPanel.add(textField, GridLayout())
                    textField
                }
            }.toTypedArray()
        } else {
            funcArgsArray = arrayOf()
        }
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

    fun displaySingle(contractName: String, funcName: String, args: Array<String>, init: Array<String>) {
        addFields(args, init)
        this.contractName.text = contractName
        this.funcName.text = funcName
        this.contractName.isEditable = false
        this.funcName.isEditable = false
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    private fun onRun() {
        buttonRun.isEnabled = false // called only once
        val testInput =
            VyperTestParameters(
                project, module, file, funcName.text,
                initArgsArray.map { it.text }.toTypedArray(),
                funcArgsArray.map { it.text }.toTypedArray()
            )
        propertyChangeSupport.firePropertyChange(VyperRunContractCase.SINGLE, null, testInput)
        dispose()
    }

    private fun onCancel() = dispose()


}
