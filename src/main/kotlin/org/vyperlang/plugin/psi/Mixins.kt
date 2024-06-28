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

abstract class VyperVarLiteralMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperVarLiteral {
    override val referenceNameElement: PsiElement get() = findChildByType(IDENTIFIER)!!
    override val referenceName: String get() = referenceNameElement.text

    override fun getReference(): VyperReference {
        val parent = node.psi.parent
        val grandparent = parent.parent
        return when {
            grandparent is VyperCallExpressionImpl && parent is VyperMemberAccessExpression ->
                VyperMemberAccessReference(this, parent)
            parent is VyperMemberAccessExpression && parent.varLiteral == node.psi ->
                VyperMemberAccessReference(this, parent)
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
    /**
     * Finds the name of the function being called.
     */
    override val referenceNameElement: PsiElement get() {
        val sibling = node.findChildByType(FUNCTION_CALL_ARGUMENTS)?.treePrev
            ?: throw NullPointerException("No function call arguments found in \"${node.text}\"")
        return findLastChildByType(VAR_LITERAL, sibling)?.lastChildNode?.psi ?: firstChild
    }

    override val referenceName: String get() = referenceNameElement.text
    override fun getReference() = VyperCallReference(this)
}

abstract class VyperStructTypeMixin(node: ASTNode) : VyperNamedElementImpl(node), VyperStructType {
    override val referenceNameElement: PsiElement get() = findChildByType(IDENTIFIER)!!
    override val referenceName: String get() = referenceNameElement.text
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
