package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.VyperLanguage

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
