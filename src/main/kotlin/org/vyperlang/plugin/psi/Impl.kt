package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import org.vyperlang.plugin.psi.VyperTypes.IDENTIFIER
import org.vyperlang.plugin.references.VyperReference

abstract class VyperElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), VyperElement {
    override fun getReference(): VyperReference? = null
}

abstract class VyperNamedElementImpl(node: ASTNode) : VyperElementImpl(node), VyperNamedElement,
    PsiNameIdentifierOwner {

    override fun getNameIdentifier(): PsiElement? = findChildByType(IDENTIFIER)

    override fun setName(name: String): PsiElement = node.psi

    override fun getName(): String? = nameIdentifier?.text

    override fun getNavigationElement(): PsiElement = nameIdentifier ?: this

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()
}

abstract class VyperImportImpl(node: ASTNode) : VyperNamedElementImpl(node) {
    override fun getNameIdentifier(): PsiElement? = findLastChildByType(IDENTIFIER)
}
