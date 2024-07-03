package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import org.vyperlang.plugin.VyperLanguage

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType(): FileType = viewProvider.fileType

    override fun toString(): String = fileType.name

    val statements: List<VyperElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)
}
