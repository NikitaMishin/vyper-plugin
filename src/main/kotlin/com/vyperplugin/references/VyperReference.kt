package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import org.vyperlang.plugin.psi.VyperElement

interface VyperReference : PsiPolyVariantReference {

    override fun getElement(): VyperElement

    override fun resolve(): VyperElement?

    fun multiResolve(): Collection<PsiElement>

}
