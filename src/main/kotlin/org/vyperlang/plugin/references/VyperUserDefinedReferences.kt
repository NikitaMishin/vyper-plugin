package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import org.vyperlang.plugin.completion.VyperCompleter
import org.vyperlang.plugin.psi.VyperCallElement
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
    private fun resolveFunctionCall() = VyperResolver.resolveCall(element)

    override fun multiResolve() = resolveFunctionCall()
}

class VyperStructReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun singleResolve(): PsiElement? = VyperResolver.resolveStruct(element)
}

class VyperStructMemberReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun singleResolve(): PsiElement? = VyperResolver.resolveStructMember(element)
}

class VyperEventLogReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve(): Collection<PsiElement> = VyperResolver.resolveEventLog(element)
}
