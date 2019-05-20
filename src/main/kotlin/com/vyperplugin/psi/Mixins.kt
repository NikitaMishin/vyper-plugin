package com.vyperplugin.psi

import com.intellij.formatting.blocks.prev
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.util.io.endsWithName
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.tree.IElementType
import com.vyperplugin.psi.VyperTypes.*
import com.vyperplugin.references.*

abstract class VyperVarLiteralMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperVarLiteral {

    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference(): VyperReference {
        val parent = node.psi.parent
        val grandparent = parent.parent
        return when {
            parent is VyperMemberAccessExpression && parent.varLiteral == node.psi -> VyperMemberAccessReference(this, parent)
            //Call expr - > MemberAccess -> VarLitral
            //Call expr -> PrimaryExpr -> VarLiteral
            grandparent is VyperCallElement -> VyperCallReference(grandparent)
            else -> VyperVarLiteralReference(this)
        }
//        if (parent is VyperCallElement) return VyperCallReference(parent)
//        if (grandparent is VyperCallElement) return VyperCallReference(grandparent)
        //member access
//        if (parent is VyperSelfAccessExpression) return VyperSelfAccessReference(this,parent)
//        if (parent is VyperMemberAccessExpression && parent.varLiteral == node.psi) return VyperMemberAccessReference(this,parent)

    }
}



abstract class VyperCallElement(node: ASTNode) : VyperNamedElementImpl(node), VyperCallExpression {

    override val referenceNameElement: PsiElement

        get() = {
            val sibl = node.findChildByType(LPAREN)?.treePrev
             when{
                sibl!!.psi is VyperPrimaryExpression -> findLastChildByType(VAR_LITERAL, sibl)?.lastChildNode?.psi ?: firstChild
                 //else selfAccess
                else ->  findLastChildByType(VAR_LITERAL, sibl)?.lastChildNode?.psi ?: firstChild
            }
        }()
    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference() = VyperCallReference(this)

}

abstract class VyperStructTypeMixin(node : ASTNode) : VyperNamedElementImpl(node), VyperStructType {
    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference(): VyperReference? {
        return VyperStructTypeReference(this)
    }
}

abstract class VyperFunctionDefMixin(node : ASTNode) : VyperNamedElementImpl(node), VyperFunctionDefinition {
    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override val modifiers: List<PsiElement>
        get() = findChildrenByType<PsiElement>(FUNCTION_MODIFIER)

    override val parameters: VyperFunctionArgs?
        get() = findChildByType(FUNCTION_ARGS)

    override val parameterTypes: List<VyperType>
        get() = parameters?.paramDefList?.map { it.type } ?: emptyList()

    override val returns: VyperType?
        get() = findChildByType(TYPE)

    override val isConstructor: Boolean
        get() = name == "__init__"

    override fun getIcon(flags: Int) = IconLoader.getIcon("/icons/jar-gray.png")
}