package com.vyperplugin.gui


import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*



class VyperRunMenuSingle : JDialog() {
    private val contentPane: JPanel? = null
    private val buttonRun: JButton? = null
    private val buttonCancel: JButton? = null
    private val contractName: JTextField? = null
    private val funcName: JTextField? = null
    private val initArgs: JTextField? = null
    private val funcArgs: JTextField? = null

    init {
        setContentPane(contentPane)
        isModal = true
        getRootPane().defaultButton = buttonRun
        buttonRun!!.addActionListener { onOK() }
        buttonCancel!!.addActionListener { onCancel() }

        // call onCancel() when cross is clicked
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                onCancel()
            }
        })

        // call onCancel() on ESCAPE
        contentPane!!.registerKeyboardAction({ onCancel() }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
    }

    private fun onOK() {
        // add your code here
        dispose()
    }

    private fun onCancel() {
        // add your code here if necessary
        dispose()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val dialog = VyperRunMenuSingle()
            dialog.pack()
            dialog.isVisible = true
            System.exit(0)
        }
    }
}
