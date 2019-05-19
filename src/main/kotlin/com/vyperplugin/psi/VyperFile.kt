package com.vyperplugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.vyperplugin.VyperFileType
import com.vyperplugin.VyperLanguage
import javax.swing.Icon

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType(): FileType {
        return VyperFileType
    }

    override fun toString(): String {
        return "Vyper File"
    }

    override fun getIcon(flags: Int): Icon? {
        return super.getIcon(flags)
    }
    fun getStatements() : List<VyperElement> = PsiTreeUtil.getChildrenOfAnyType<VyperElement>(this, VyperElement::class.java)
}
