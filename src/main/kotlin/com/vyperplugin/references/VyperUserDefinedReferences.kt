package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.vyperplugin.completion.VyperCompleter
import com.vyperplugin.psi.VyperCallElement
import com.vyperplugin.psi.VyperMemberAccessExpression
import com.vyperplugin.psi.VyperVarLiteral

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element),
    VyperReference {

    override fun multiResolve(): Collection<PsiElement> {
        return VyperResolver.resolveVarLiteral(element)
    }

    override fun getVariants(): Array<out Any> {
        return VyperCompleter.completeVarLiteral(element)
    }
}

class VyperMemberAccessReference(element: VyperVarLiteral, var member: VyperMemberAccessExpression) :
    VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve(): Collection<PsiElement> {
        return VyperResolver.resolveMemberAccess(member)
    }

    override fun getVariants(): Array<out Any> {
        return VyperCompleter.completeMemberAccess(member)
    }

}

class VyperCallReference(element: VyperCallElement) : VyperReferenceBase<VyperCallElement>(element), VyperReference {

    private fun resolveFunctionCall(): Collection<FunctionResolveResult> {
        return VyperResolver.resolveFunction(element)
    }

    override fun multiResolve(): Collection<PsiElement> {
        return resolveFunctionCall().map { it.psiElement }
    }
}
