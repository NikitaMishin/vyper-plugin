package com.vyperplugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.util.io.FileUtil
import java.awt.Component
import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JTextField

class VyperConfigurablePanel {
    internal lateinit var mainPanel: JPanel
    // deployment panel
    private lateinit var deployPanel: JPanel
    private lateinit var usesCustomAccount: JCheckBox
    private lateinit var address: JTextField
    private lateinit var network: JTextField
    //private lateinit var password: JPasswordField
    private lateinit var password: JTextField


    // createStubInGenSourceFolder stubs
    private lateinit var generateStubsPanel: JPanel
    private lateinit var generateStubs: JCheckBox
    private lateinit var genOutputPath: JTextField
    //


    private lateinit var compilerPanel: JPanel
    // compiler
    private lateinit var compilerParams: JTextField
//    private lateinit var fileExtension: JTextField

    init {
        usesCustomAccount.addActionListener {
            updateDeployStatus()
        }
        generateStubs.addActionListener() {
            updateGenerateStubsStatus()
        }
    }

    private fun updateDeployStatus() {
        val enabled = usesCustomAccount.isSelected
        deployPanel.setAll({ it.isEnabled = enabled }, usesCustomAccount)
    }

    private fun updateGenerateStubsStatus() {
        val enabled = generateStubs.isSelected
        generateStubsPanel.setAll({ it.isEnabled = enabled }, generateStubs)
    }

    private fun JPanel.setAll(action: (Component) -> Unit, vararg except: Component) {
        this.components.asSequence()
                .filterNot { except.contains(it) }
                .forEach {
                    (it as? JPanel)?.setAll(action, *except)
                    action(it)
                }
    }


    fun apply(settings: VyperSettings) {
        validateFields()

        settings.usesCustomAccount = usesCustomAccount.isSelected
        settings.address = address.text
        settings.password = password.text
        settings.network = network.text

        settings.generateStubs = generateStubs.isSelected
        settings.genarateOutputPath = genOutputPath.text

        compilerParams.text = compilerParams.text.replace("\\s+".toRegex()," ")
                .dropWhile { it==' '}.dropLastWhile { it==' '}

        settings.compilerParams = compilerParams.text

        ApplicationManager.getApplication().messageBus.syncPublisher(VyperConfigurationListener.TOPIC).settingsConfigChanged()
    }


    fun reset(settings: VyperSettings) {
        usesCustomAccount.isSelected = settings.usesCustomAccount
        generateStubs.isSelected = settings.generateStubs
        genOutputPath.text = FileUtil.toSystemDependentName(settings.genarateOutputPath)
        address.text = settings.address
        password.text = settings.password
        network.text = settings.network
        compilerParams.text = settings.compilerParams
        updateDeployStatus()
        updateGenerateStubsStatus()
    }

    private fun validateFields() {
        if (usesCustomAccount.isSelected) {
            if (address.text.isBlank()) {
                throw  ConfigurationException("No address is provided")
            }
            if (password.text.isBlank()) {
                throw  ConfigurationException("No password is provided")
            }
            if (network.text.isBlank()) {
                throw  ConfigurationException("No network is provided")
            }
        }
        if (generateStubs.isSelected) {
            if (genOutputPath.text.isBlank()) {
                throw  ConfigurationException("No gen path is provided")
            }
        }
    }


    fun isModified(settings: VyperSettings): Boolean =
            usesCustomAccount.isSelected != settings.usesCustomAccount ||

            address.text != settings.address ||
                    password.text != settings.password ||
                    network.text != settings.network ||

                    compilerParams.text != settings.compilerParams ||

                    generateStubs.isSelected != settings.generateStubs ||
                    genOutputPath.text != settings.genarateOutputPath.trim()

}