package com.vyperplugin.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.impl.source.resolve.ResolveCache
import com.vyperplugin.psi.VyperElement
import com.vyperplugin.psi.VyperReferenceElement

abstract class VyperReferenceBase<T : VyperReferenceElement>(element: T) : PsiPolyVariantReferenceBase<T>(element),
    VyperReference {

    //highlights the element which is referenced
    override fun calculateDefaultRangeInElement() = TextRange(0, element.textRange.length)

    override fun getVariants(): Array<out Any> = emptyArray()

    final override fun multiResolve(incompleteCode: Boolean) = ResolveCache.getInstance(element.project)
        .resolveWithCaching(this, { r, _ ->
            r.multiResolve().map(::PsiElementResolveResult).toTypedArray()
        }, true, false)

    //    final override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
//        return this.multiResolve().map(::PsiElementResolveResult).toTypedArray()
//    }
    override fun multiResolve(): Collection<PsiElement> = singleResolve()?.let { listOf(it) } ?: emptyList()

    open fun singleResolve(): PsiElement? = null

//    overridee fun handleElementRename(newName: String): PsiElement {
//        doRename(element.referenceNameElement, newName)
//        return element
//    }

    override fun resolve(): VyperElement? = super.resolve() as VyperElement?


//    protected open fun doRename(identifier: PsiElement, newName: String) {
//        check(identifier.elementType == IDENTIFIER)
//        identifier.replace(SolPsiFactory(identifier.project).createIdentifier(newName.replace(".sol", "")))
//    }

}