package com.vyperplugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.vyperplugin.Vyper
import com.vyperplugin.VyperFileType

import javax.swing.*

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, Vyper.INSTANCE) {

    override fun getFileType(): FileType {
        return VyperFileType.INSTANCE
    }

    override fun toString(): String {
        return "Vyper File"
    }

    override fun getIcon(flags: Int): Icon? {
        return super.getIcon(flags)
    }
}
