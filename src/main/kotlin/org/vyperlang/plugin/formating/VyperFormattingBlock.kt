package org.vyperlang.plugin.formating

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.formatter.FormatterUtil
import org.vyperlang.plugin.psi.VyperFunctionDefinition
import org.vyperlang.plugin.psi.VyperLocalVariableDefinition
import org.vyperlang.plugin.psi.VyperStatement
import org.vyperlang.plugin.psi.VyperStructDeclaration
import org.vyperlang.plugin.psi.VyperTypes.*
import java.util.*

class VyperFormattingBlock(
    private val astNode: ASTNode,
    private val alignment: Alignment?,
    private val indent: Indent,
    private val wrap: Wrap?,
    private val codeStyleSettings: CodeStyleSettings,
    private val spacingBuilder: SpacingBuilder
) : ASTBlock {
    private val nodeSubBlocks: List<Block> by lazy { buildSubBlocks() }
    private val isNodeIncomplete: Boolean by lazy { FormatterUtil.isIncomplete(node) }

    override fun getSubBlocks(): List<Block> = nodeSubBlocks

    private fun buildSubBlocks(): List<Block> {
        val blocks = ArrayList<Block>()
        var child = astNode.firstChildNode
        while (child != null) {
            val childType = child.elementType
            if (child.textRange.length == 0) {
                child = child.treeNext
                continue
            }
            if (childType === TokenType.WHITE_SPACE) {
                child = child.treeNext
                continue
            }
            val e = buildSubBlock(child)
            blocks.add(e)
            child = child.treeNext
        }
        return Collections.unmodifiableList(blocks)
    }

    private fun buildSubBlock(child: ASTNode): Block {
        val indent = calcIndent(child)
        return VyperFormattingBlock(child, alignment, indent, null, codeStyleSettings, spacingBuilder)
    }

    private fun calcIndent(child: ASTNode): Indent {
        val childType = child.elementType
        val type = astNode.elementType
        val result = when {
            child is PsiComment && type in listOf(
                FUNCTION_DEFINITION,
                STRUCT_DECLARATION
            ) -> Indent.getNormalIndent()
            childType is VyperStatement && type is VyperFunctionDefinition -> Indent.getNormalIndent()
            childType is VyperLocalVariableDefinition && type is VyperStructDeclaration -> Indent.getNormalIndent()

            // fields inside structs
//            type == VyperTypes.STRUCT_DECLARATION && childType == VyperTypes.LOCAL_VARIABLE_DEFINITION -> Indent.getNormalIndent()

            // inside a block, list of parameters, etc.
//            parentType in listOf(BLOCK, ENUM_DEFINITION, ASSEMBLY_BLOCK, PARAMETER_LIST, INDEXED_PARAMETER_LIST) -> Indent.getNormalIndent()

            // all expressions inside parens should have indentation when lines are split
//            parentType in listOf(IF_STATEMENT, WHILE_STATEMENT, DO_WHILE_STATEMENT, FOR_STATEMENT) && childType != BLOCK -> {
//                Indent.getNormalIndent()
//            }

            // all function calls

            else -> Indent.getNoneIndent()
        }
        return result
    }

    private fun newChildIndent(childIndex: Int): Indent? = when (node.elementType) {
        in listOf(STRUCT_DECLARATION, FUNCTION_DEFINITION) -> {
            val lbraceIndex = subBlocks.indexOfFirst { it is ASTBlock && it.node!!.elementType == COLON }
            if (lbraceIndex != -1 && lbraceIndex < childIndex) {
                Indent.getNormalIndent()
            } else {
                Indent.getNoneIndent()
            }
        }
        else -> Indent.getNoneIndent()
    }

    override fun getNode(): ASTNode = astNode
    override fun getTextRange(): TextRange = astNode.textRange
    override fun getWrap(): Wrap? = wrap
    override fun getIndent(): Indent = indent
    override fun getAlignment(): Alignment? = alignment

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes =
        ChildAttributes(newChildIndent(newChildIndex), null)

    override fun isIncomplete(): Boolean = isNodeIncomplete

    override fun isLeaf(): Boolean = astNode.firstChildNode == null
}
