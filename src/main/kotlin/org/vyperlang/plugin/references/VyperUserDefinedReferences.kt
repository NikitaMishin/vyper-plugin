package org.vyperlang.plugin.references

import org.vyperlang.plugin.completion.VyperCompleter
import org.vyperlang.plugin.psi.VyperMemberAccessExpression
import org.vyperlang.plugin.psi.VyperVarLiteral

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveVarLiteral(element)

    override fun getVariants() = VyperCompleter.completeVarLiteral(element)
}

class VyperMemberAccessReference(element: VyperVarLiteral, var member: VyperMemberAccessExpression) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveMemberAccess(member)
    
    override fun getVariants() = VyperCompleter.completeMemberAccess(member)
}

/* todo: implement getVariants for the types below */

class VyperStructReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveStruct(element)
}

class VyperInterfaceReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveInterface(element)
}

class VyperStructMemberReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveStructMember(element)
}

class VyperEventLogReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun multiResolve() = VyperResolver.resolveEventLog(element)
}
