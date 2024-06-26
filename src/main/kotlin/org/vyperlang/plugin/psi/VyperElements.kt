package org.vyperlang.plugin.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReference
import org.vyperlang.plugin.references.VyperReference

interface VyperElement : PsiElement {
    override fun getReference(): PsiReference?
}

interface VyperNamedElement : VyperElement, PsiNamedElement, NavigatablePsiElement

interface VyperReferenceElement : VyperNamedElement {
    val referenceNameElement: PsiElement
    val referenceName: String

    override fun getReference(): VyperReference?
}

interface VyperFunctionDefElement : VyperReferenceElement {
    val modifiers: List<PsiElement>
    val parameters: org.vyperlang.plugin.psi.VyperFunctionArgs?
    val parameterTypes: List<org.vyperlang.plugin.psi.VyperType>
    val returns: org.vyperlang.plugin.psi.VyperType?
    val isConstructor: Boolean
}