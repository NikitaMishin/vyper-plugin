package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import org.vyperlang.plugin.VyperLanguage

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType(): FileType = viewProvider.fileType

    override fun toString(): String = fileType.name

    val elements: List<VyperElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)

    val selfElements: MutableList<VyperNamedElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperFunctionDefinition::class.java, VyperStateVariableDeclaration::class.java)

    val functions: List<VyperFunctionDefinition> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperFunctionDefinition::class.java)

    val stateVariables: List<VyperStateVariableDeclaration> get () = PsiTreeUtil.getChildrenOfAnyType(this, VyperStateVariableDeclaration::class.java)
}
