package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import org.vyperlang.plugin.completion.VyperCompleter
import org.vyperlang.plugin.psi.VyperCallElement
import org.vyperlang.plugin.psi.VyperLocalVariableDefinition
import org.vyperlang.plugin.psi.VyperMemberAccessExpression
import org.vyperlang.plugin.psi.VyperVarLiteral

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element),
    VyperReference {

    override fun multiResolve(): Collection<PsiElement> = VyperResolver.resolveVarLiteral(element)

    override fun getVariants(): Array<out Any> = VyperCompleter.completeVarLiteral(element)
}

class VyperMemberAccessReference(element: VyperVarLiteral, var member: VyperMemberAccessExpression) :
    VyperReferenceBase<VyperVarLiteral>(element), VyperReference {

    override fun multiResolve(): Collection<PsiElement> = VyperResolver.resolveMemberAccess(member)
    override fun getVariants(): Array<out Any> = VyperCompleter.completeMemberAccess(member)

}

class VyperCallReference(element: VyperCallElement) : VyperReferenceBase<VyperCallElement>(element), VyperReference {
    private fun resolveFunctionCall(): Collection<FunctionResolveResult> = VyperResolver.resolveFunction(element)

    override fun multiResolve(): Collection<PsiElement> = resolveFunctionCall().map { it.psiElement }
}

class VyperStructMemberReference(element: VyperVarLiteral, private val structMember: VyperLocalVariableDefinition?) :
    VyperReferenceBase<VyperVarLiteral>(element), VyperReference {

    override fun singleResolve(): PsiElement? = structMember
}
