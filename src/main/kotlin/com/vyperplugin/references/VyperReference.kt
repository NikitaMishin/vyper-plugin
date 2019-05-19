package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import com.vyperplugin.psi.VyperElement

interface VyperReference : PsiPolyVariantReference {

    override fun getElement(): VyperElement

    override fun resolve(): VyperElement?

    fun multiResolve(): Collection<PsiElement>

}
