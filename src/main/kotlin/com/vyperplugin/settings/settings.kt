package com.vyperplugin.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

@State(name = "VyperSettings", storages = [Storage("other.xml")])
class VyperSettings : PersistentStateComponent<VyperSettings> {

    var generateStubs: Boolean = false
    var genarateOutputPath: String = "srcGenVyper"
    var fileExtension: String = ".txt" // right now would be just .txt

    var usesCustomAccount: Boolean = false
    var network: String = "" //>>? Ethereum main net?
    var address: String = ""
    var password: String = ""


    val defaultAddress = "0xae6f8a4948487ed4f4d9d319c6c7b135fe19a419"
    val defaultPassword = "Qwertyqwerty!1"

    var compilerParams: String = " "
    fun getCompilerParamsArray(): Array<String> {
        if (compilerParams == " " || compilerParams == "") return arrayOf<String>()
        val ls = mutableListOf<String>()

        for (el in compilerParams.split(" ")) {
            //ls.addAll(el.split(","))
            ls.add(el)
        }

        return ls.toTypedArray()

    }


    override fun getState(): VyperSettings? = this

    override fun loadState(state: VyperSettings) =
            XmlSerializerUtil.copyBean(state, this)

    companion object {
        val INSTANCE: VyperSettings
            get() = ServiceManager.getService(VyperSettings::class.java)
    }
}


class VyperSettingsConfigurable(private val _settings: VyperSettings) : SearchableConfigurable, Configurable.NoScroll {
    private var _panel: VyperConfigurablePanel? = null

    private val name: String = "Vyper"

    @Nls
    override fun getDisplayName(): String = name

    override fun getHelpTopic(): String {
        return "preferences.Vyper"
    }

    override fun createComponent(): JComponent? {
        _panel = VyperConfigurablePanel()
        return _panel!!.mainPanel
    }

    override fun isModified(): Boolean = _panel!!.isModified(_settings)

    @Throws(ConfigurationException::class)
    override fun apply() = _panel!!.apply(_settings)

    override fun reset() = _panel!!.reset(_settings)

    override fun disposeUIResources() {
        _panel = null
    }

    override fun getId(): String = helpTopic

//    fun getQuickFix(project: Project): Runnable =
//            Runnable { ShowSettingsUtil.getInstance().editConfigurable(project, this) }
}
