package com.vyperplugin.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.vyperplugin.psi.VyperTypes.IDENTIFIER
import com.vyperplugin.references.VyperReference
import com.vyperplugin.references.VyperVarLiteralReference

abstract class VyperVarLiteralMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperVarLiteral {

    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference(): VyperReference = VyperVarLiteralReference(this)
}