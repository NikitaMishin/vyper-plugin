package org.vyperlang.plugin.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperTypes.*
import org.vyperlang.plugin.references.*

abstract class VyperVarLiteralMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperVarLiteral {
    override val referenceNameElement: PsiElement get() = findChildByType(IDENTIFIER)!!
    override val referenceName: String get() = referenceNameElement.text

    /**
     * Converts the var literal to a reference.
     */
    override fun getReference(): VyperReference = getReference(node.psi.parent)

    private fun getReference(parent: PsiElement?): VyperReference = when (parent) {
        is VyperModuleReferenceDirective -> VyperInterfaceReference(this)
        is VyperEventLogExpression -> VyperEventLogReference(this)
        is VyperStructExpression -> VyperStructReference(this)
        is VyperStructExpressionMember -> VyperStructMemberReference(this)
        is VyperMemberAccessExpression -> VyperMemberAccessReference(this, parent)
        is VyperPrimaryExpression ->
            if (parent.parent is VyperAssignmentExpression && parent.parent.parent is VyperFunctionCallArgument && parent == parent.parent.firstChild) {
                VyperKeywordArgumentReference(this)
            } else {
                VyperVarLiteralReference(this)
            }
        else -> VyperVarLiteralReference(this) // reference itself
    }
}

abstract class VyperFunctionDefMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperFunctionDefinition {
    override val referenceNameElement: PsiElement get() = findChildByType(IDENTIFIER)!!
    override val referenceName: String get() = referenceNameElement.text
    override val modifiers: List<PsiElement> get() = findChildrenByType(FUNCTION_MODIFIER)
    override val parameters: VyperFunctionArgs? get() = findChildByType(FUNCTION_ARGS)
    override val parameterTypes: List<VyperType> get() = parameters?.paramDefList?.map { it.type } ?: emptyList()
    override val returns: VyperType? get() = findChildByType(TYPE)
    override val isConstructor: Boolean get() = name == "__init__"
    override fun getIcon(flags: Int) = IconLoader.getIcon("/icons/VyperFile.svg", javaClass)
}
