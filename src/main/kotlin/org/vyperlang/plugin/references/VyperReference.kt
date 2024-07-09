package org.vyperlang.plugin.references

import com.intellij.psi.PsiPolyVariantReference
import org.vyperlang.plugin.psi.VyperElement

interface VyperReference : PsiPolyVariantReference {

    /** Returns the referencing element. */
    override fun getElement(): VyperElement

    /**
     * Implemented in PsiPolyVariantReferenceBase.
     * Calls multiResolve() and returns the element if there's a single result.
     */
    override fun resolve(): VyperElement?

    /** Resolves the reference to one or more elements. */
    fun multiResolve(): Collection<VyperElement>

    /**
     * Returns a list of elements that the `element` could also reference.
     * This is used for code completion.
     */
    fun getAlternatives(): Collection<VyperElement>
}
