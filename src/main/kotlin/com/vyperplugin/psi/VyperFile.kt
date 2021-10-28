package com.vyperplugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import com.vyperplugin.VyperFileType
import com.vyperplugin.VyperLanguage

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType(): FileType {
        return VyperFileType.INSTANCE
    }

    override fun toString(): String {
        return "Vyper File"
    }

    fun getStatements(): List<VyperElement> =
        PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)
}
