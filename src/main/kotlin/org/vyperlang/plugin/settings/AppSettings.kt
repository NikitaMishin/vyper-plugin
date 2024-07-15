package org.vyperlang.plugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.text.SemVer

enum class VyperExecutor {
    Docker,
    Local
}

@State(name = "org.vyperlang.plugin.settings.AppSettings", storages = [Storage("SdkSettingsPlugin.xml")])
internal class AppSettings(private var state: State = State()) : PersistentStateComponent<AppSettings.State> {
    internal class State(
        val executor: VyperExecutor = VyperExecutor.Docker,
        val localPaths: Map<SemVer, String> = mutableMapOf()
    )

    override fun getState(): State = state

    fun setState(newState: State) {
        state = newState
    }

    override fun loadState(newState: State) {
        state = newState
    }

    companion object {
        val instance: AppSettings get() =
            ApplicationManager.getApplication().getService(AppSettings::class.java)
    }
}