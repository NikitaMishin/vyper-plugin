package org.vyperlang.plugin

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class VyperInterfaceFileType : LanguageFileType(VyperLanguage) {

    companion object {
        @JvmStatic
        val INSTANCE = VyperInterfaceFileType()
    }

    override fun getName(): String {
        return "Vyper interface file"
    }

    override fun getDisplayName(): String {
        return "Vyper interface file"
    }

    override fun getDescription(): String {
        return "Vyper language interface file"
    }

    override fun getDefaultExtension(): String {
        return "vyi"
    }

    override fun getIcon(): Icon {
        return VyperIcons.INTERFACE_FILE
    }
}
