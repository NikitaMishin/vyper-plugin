package org.vyperlang.plugin.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var myComponent: AppSettingsComponent? = null
    private val component get() = myComponent!!

    // A default constructor with no arguments is required because
    // this implementation is registered as an applicationConfigurable
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String? {
        return "Vyper Settings"
    }

//    override fun getPreferredFocusedComponent(): JComponent {
//        return component.preferredFocusedComponent
//    }

    override fun createComponent(): JComponent {
        myComponent = AppSettingsComponent()
        return component.panel
    }

    override fun isModified(): Boolean {
        val state: AppSettings.State = AppSettings.instance.state
        return component.executor != state.executor || component.localPaths != state.localPaths
    }

    override fun apply() {
        AppSettings.instance.state = AppSettings.State(
            executor = component.executor,
            localPaths = component.localPaths
        )
    }

    override fun reset() {
        AppSettings.instance.state = AppSettings.State()
    }

    override fun disposeUIResources() {
        myComponent = null
    }
}