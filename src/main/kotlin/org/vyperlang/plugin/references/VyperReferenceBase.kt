package org.vyperlang.plugin.references

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.impl.source.resolve.ResolveCache
import org.vyperlang.plugin.completion.VyperCompleter.createLookup
import org.vyperlang.plugin.psi.VyperElement
import org.vyperlang.plugin.psi.VyperNamedElement
import org.vyperlang.plugin.psi.VyperReferenceElement

abstract class VyperReferenceBase<T : VyperReferenceElement>(element: T)
    : PsiPolyVariantReferenceBase<T>(element), VyperReference {

    /* Highlights the element which is referenced */
    override fun calculateDefaultRangeInElement() = TextRange(0, element.textRange.length)

    /** Gets the auto-complete variants for the reference. */
    override fun getVariants(): Array<out LookupElement> = getAlternatives().map { createLookup(it) }.toTypedArray()

    /**
     * Gets the possible elements that the reference can resolve to, with caching.
     * @return The possible elements.
     */
    final override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> =
        ResolveCache.getInstance(element.project)
            .resolveWithCaching(this, { r, _ -> r.multiResolve().map(::PsiElementResolveResult).toTypedArray() }, true, false)

    /**
     * Resolves the reference to one or more elements.
     */
    override fun multiResolve() = this.getAlternatives().filterIsInstance<VyperNamedElement>().filter { it.name == element.name }

    /**
     * Implemented in PsiPolyVariantReferenceBase.
     * Calls multiResolve() and returns the element if there's a single result.
     */
    override fun resolve(): VyperElement? = super.resolve() as VyperElement?

    // protected open fun doRename(identifier: PsiElement, newName: String) { check(identifier.elementType == IDENTIFIER);  identifier.replace(SolPsiFactory(identifier.project).createIdentifier(newName.replace(".sol", ""))) }
    // override fun handleElementRename(newName: String): PsiElement { doRename(element.referenceNameElement, newName); return element }

}