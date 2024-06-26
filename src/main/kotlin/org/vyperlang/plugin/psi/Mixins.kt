package org.vyperlang.plugin.psi


import com.intellij.lang.ASTNode
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperTypes.*
import org.vyperlang.plugin.psi.impl.VyperCallExpressionImpl
import org.vyperlang.plugin.references.VyperCallReference
import org.vyperlang.plugin.references.VyperMemberAccessReference
import org.vyperlang.plugin.references.VyperReference
import org.vyperlang.plugin.references.VyperVarLiteralReference

abstract class VyperVarLiteralMixin(node: ASTNode) : VyperNamedElementImpl(node),
    org.vyperlang.plugin.psi.VyperVarLiteral {

    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference(): VyperReference {
        val parent = node.psi.parent
        val grandparent = parent.parent
//        println(grandparent)
        return when {
            grandparent is org.vyperlang.plugin.psi.impl.VyperCallExpressionImpl && parent is org.vyperlang.plugin.psi.VyperMemberAccessExpression -> {
                VyperMemberAccessReference(
                    this, parent
                )
            }
            parent is org.vyperlang.plugin.psi.VyperMemberAccessExpression && parent.varLiteral == node.psi -> {
                VyperMemberAccessReference(
                    this,
                    parent
                )
            }
            //Call expr - > MemberAccess -> VarLitral
            //Call expr -> PrimaryExpr -> VarLiteral
            else -> {
                VyperVarLiteralReference(this)
            }
        }
//        if (parent is VyperCallElement) return VyperCallReference(parent)
//        if (grandparent is VyperCallElement) return VyperCallReference(grandparent)
        //member access
//        if (parent is VyperSelfAccessExpression) return VyperSelfAccessReference(this,parent)
//        if (parent is VyperMemberAccessExpression && parent.varLiteral == node.psi) return VyperMemberAccessReference(this,parent)

    }
}


abstract class VyperCallElement(node: ASTNode) : VyperNamedElementImpl(node),
    org.vyperlang.plugin.psi.VyperCallExpression {

    override val referenceNameElement: PsiElement
        get()  //else selfAccess
        {
            val sibl = node.findChildByType(LPAREN)?.treePrev
            //else selfAccess
            return when (sibl!!.psi) {
                is org.vyperlang.plugin.psi.VyperPrimaryExpression -> findLastChildByType(VAR_LITERAL, sibl)?.lastChildNode?.psi
                    ?: firstChild
                //else selfAccess
                else -> findLastChildByType(VAR_LITERAL, sibl)?.lastChildNode?.psi ?: firstChild
            }
        }
    override val referenceName: String
        get() = referenceNameElement.text

    override fun getReference() = VyperCallReference(this)

}

abstract class VyperStructTypeMixin(node: ASTNode) : VyperNamedElementImpl(node),
    org.vyperlang.plugin.psi.VyperStructType {
    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text


}

abstract class VyperFunctionDefMixin(node: ASTNode) : VyperNamedElementImpl(node),
    org.vyperlang.plugin.psi.VyperFunctionDefinition {
    override val referenceNameElement: PsiElement
        get() = findChildByType(IDENTIFIER)!!

    override val referenceName: String
        get() = referenceNameElement.text

    override val modifiers: List<PsiElement>
        get() = findChildrenByType(FUNCTION_MODIFIER)

    override val parameters: org.vyperlang.plugin.psi.VyperFunctionArgs?
        get() = findChildByType(FUNCTION_ARGS)

    override val parameterTypes: List<org.vyperlang.plugin.psi.VyperType>
        get() = parameters?.paramDefList?.map { it.type } ?: emptyList()

    override val returns: org.vyperlang.plugin.psi.VyperType?
        get() = findChildByType(TYPE)

    override val isConstructor: Boolean
        get() = name == "__init__"

    override fun getIcon(flags: Int) = IconLoader.getIcon("/icons/VyperFile.svg", javaClass)
}