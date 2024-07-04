package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import org.vyperlang.plugin.completion.VyperCompleter
import org.vyperlang.plugin.psi.VyperCallElement
import org.vyperlang.plugin.psi.VyperMemberAccessExpression
import org.vyperlang.plugin.psi.VyperVarLiteral

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

class VyperStructMemberReference(element: VyperCallElement) : VyperReferenceBase<VyperCallElement>(element), VyperReference {

    private fun resolveFunctionCall(): Collection<FunctionResolveResult> {
        return VyperResolver.resolveFunction(element)
    }

    override fun multiResolve(): Collection<PsiElement> {
        return resolveFunctionCall().map { it.psiElement }
    }
}
