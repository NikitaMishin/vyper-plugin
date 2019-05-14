package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.vyperplugin.psi.VyperElement
import com.vyperplugin.psi.VyperVarLiteral

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {

    override fun multiResolve(): Collection<PsiElement> {
        return VyperResolver.resolveVarLiteral(element)
    }

    override fun getVariants(): Array<out Any> {
        return arrayOf()
    }
}