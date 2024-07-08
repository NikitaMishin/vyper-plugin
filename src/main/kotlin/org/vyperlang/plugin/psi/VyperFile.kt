package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import org.vyperlang.plugin.VyperLanguage

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType() = viewProvider.fileType

    override fun toString() = fileType.name

    val elements: List<VyperElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)

    val selfElements: MutableList<VyperNamedElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperFunctionDefinition::class.java, VyperStateVariableDeclaration::class.java)

    val interfaces: List<VyperInterfaceDeclaration> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperInterfaceDeclaration::class.java)

    val imports: List<VyperImportDirective> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperImportDirective::class.java)

}
