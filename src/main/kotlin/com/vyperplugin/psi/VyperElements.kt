package com.vyperplugin.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReference
import com.vyperplugin.references.VyperReference

interface VyperElement: PsiElement {
    override fun getReference(): PsiReference?
}

interface VyperNamedElement: VyperElement, PsiNamedElement, NavigatablePsiElement

interface VyperReferenceElement : VyperNamedElement {
    val referenceNameElement: PsiElement
    val referenceName: String

    override fun getReference(): VyperReference?
}

interface VyperFunctionDefElement : VyperReferenceElement {
    val modifiers: List<PsiElement>
    val parameters: VyperFunctionArgs?
    val parameterTypes: List<VyperType>
    val returns: VyperType?
    val isConstructor: Boolean
}