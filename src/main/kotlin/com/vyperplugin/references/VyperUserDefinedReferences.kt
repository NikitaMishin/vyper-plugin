package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.vyperplugin.completion.VyperCompleter
import com.vyperplugin.psi.*
import com.vyperplugin.psi.impl.VyperFunctionDefinitionImpl
import com.vyperplugin.psi.impl.VyperVarLiteralImpl

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element),
    VyperReference {

    override fun multiResolve(): Collection<PsiElement> {
        val kek = VyperResolver.resolveVarLiteral(element)
        return kek
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

    fun resolveFunctionCall(): Collection<FunctionResolveResult> {
        val ref = element.expressionList.firstOrNull()
        return VyperResolver.resolveFunction(element)
    }

    override fun multiResolve(): Collection<PsiElement> {
        return resolveFunctionCall().map { it.psiElement }
    }
}

class VyperStructTypeReference(element: VyperStructType) : VyperReferenceBase<VyperStructType>(element),
    VyperReference {
    override fun getVariants(): Array<out Any> {
        return VyperCompleter.completeTypes()
    }
}
