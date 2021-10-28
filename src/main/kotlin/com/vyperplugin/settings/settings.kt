package com.vyperplugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

@State(name = "VyperSettings", storages = [Storage("otherConfig.xml")])
class VyperSettings : PersistentStateComponent<VyperSettings> {

    var generateStubs: Boolean = false
    var genarateOutputPath: String = "srcGenVyper"
    var fileExtension: String = ".txt" // right now would be just .txt

    var usesCustomAccount: Boolean = false
    var network: String = "Ethereum" //>>? Ethereum main net?

    val defaultAddress = "0xae6f8a4948487ed4f4d9d319c6c7b135fe19a419"
    val defaultPassword = "Qwertyqwerty!1"

    var address = defaultAddress
    var password = defaultPassword

    var compilerParams: String = " "
    fun getCompilerParamsArray(): Array<String> {
        if (compilerParams == " " || compilerParams == "") return arrayOf()
        val ls = mutableListOf<String>()

        for (el in compilerParams.split(" ")) {
            //ls.addAll(el.split(","))
            ls.add(el)
        }

        return ls.toTypedArray()

    }

    @Override
    @Nullable
    override fun getState(): VyperSettings = this

    @Override
    override fun loadState(@NotNull state: VyperSettings) =
        XmlSerializerUtil.copyBean(state, this)

    companion object {
        @JvmStatic
        val INSTANCE: VyperSettings = ApplicationManager.getApplication().getService(VyperSettings::class.java)
    }
}


class VyperSettingsConfigurable : Configurable {
    private var component: VyperConfigurablePanel? = null
    private val name: String = "Vyper"

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    override fun getDisplayName() = name

    override fun getHelpTopic(): String {
        return "preferences.Vyper"
    }

    @Nullable
    @Override
    override fun createComponent(): JComponent {
        component = VyperConfigurablePanel()
        return component!!.mainPanel
    }

    @Override
    override fun isModified(): Boolean = component!!.isModified(VyperSettings.INSTANCE)

    @Throws(ConfigurationException::class)
    @Override
    override fun apply() = component!!.apply(VyperSettings.INSTANCE)

    @Override
    override fun reset() = component!!.reset(VyperSettings.INSTANCE)

    @Override
    override fun disposeUIResources() {
        component = null
    }


//    fun getQuickFix(project: Project): Runnable =
//            Runnable { ShowSettingsUtil.getInstance().editConfigurable(project, this) }
}
