package org.vyperlang.plugin.references

import org.vyperlang.plugin.psi.*

class VyperVarLiteralReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = VyperResolver.lexicalDeclarations(element)
}

class VyperMemberAccessReference(element: VyperVarLiteral, private var member: VyperMemberAccessExpression) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = VyperResolver.resolveMemberAccess(member)

    override fun multiResolve() = this.getAlternatives().filter { it.name == member.varLiteral.name }
}

class VyperStructReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = element.file.structs
}

class VyperInterfaceReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = VyperResolver.resolveInterfaces(element)
}

class VyperStructMemberReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = VyperResolver.resolveStructMembers(element)
}

class VyperEventLogReference(element: VyperVarLiteral) : VyperReferenceBase<VyperVarLiteral>(element), VyperReference {
    override fun getAlternatives() = element.file.events
}
