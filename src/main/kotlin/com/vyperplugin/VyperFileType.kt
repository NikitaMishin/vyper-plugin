package com.vyperplugin

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object VyperFileType  : LanguageFileType(VyperLanguage) {

    override fun getName(): String {
        return "Vyper file"
    }

    override fun getDescription(): String {
        return "Vyper language file"
    }

    override fun getDefaultExtension(): String {
        return "vy"
    }

    override fun getIcon(): Icon? {
        return VyperIcons.FILE
    }
}
