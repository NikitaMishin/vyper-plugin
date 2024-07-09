package org.vyperlang.plugin

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.vyperlang.plugin.psi.VyperTypes.*
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Defaults

class VyperHighlighterFactory : SingleLazyInstanceSyntaxHighlighterFactory() {
    override fun createHighlighter() = VyperHighlighter()
}

/**
 * Class responsible for syntax highlighting in the lexer level
 * https://plugins.jetbrains.com/docs/intellij/syntax-highlighting-and-error-highlighting.html#lexer
 */
class VyperHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = VyperLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> {
        return pack(tokenMapping[tokenType])
    }

    companion object {
        private val VYPER_KEYWORD = TextAttributesKey.createTextAttributesKey("VYPER_KEYWORD", Defaults.KEYWORD)
        private val VYPER_OPERATOR =
            TextAttributesKey.createTextAttributesKey("VYPER_OPERATOR", Defaults.OPERATION_SIGN)
        private val VYPER_STRING_LITERAL =
            TextAttributesKey.createTextAttributesKey("VYPER_TEXT_LITERAL", Defaults.STRING)
        private val VYPER_NUMBER_LITERAL =
            TextAttributesKey.createTextAttributesKey("VYPER_NUMBER_LITERAL", Defaults.NUMBER)
        private val VYPER_IDENTIFIER =
            TextAttributesKey.createTextAttributesKey("VYPER_IDENTIFIER", Defaults.IDENTIFIER)
        private val VYPER_MODIFICATOR = TextAttributesKey.createTextAttributesKey("VYPER_MODIFICATOR", Defaults.FUNCTION_CALL)
        private val VYPER_BOOLEAN_LITERAL =
            TextAttributesKey.createTextAttributesKey("VYPER_BOOLEAN_LITERAL", Defaults.KEYWORD)
        private val VYPER_CONSTANT = TextAttributesKey.createTextAttributesKey("VYPER_CONSTANT")
        private val VYPER_REFERENCE_TYPE =
            TextAttributesKey.createTextAttributesKey("VYPER_REFERENCE_TYPE", Defaults.KEYWORD)
        private val VYPER_VALUE_TYPE = TextAttributesKey.createTextAttributesKey("VYPER_VALUE_TYPE", Defaults.KEYWORD)
        private val VYPER_COMMENT = TextAttributesKey.createTextAttributesKey("VYPER_COMMENT", Defaults.LINE_COMMENT)
        private val VYPER_MULTILINE_STRING =
            TextAttributesKey.createTextAttributesKey("VYPER_MULTILINE_STRING", Defaults.DOC_COMMENT)

        private val tokenMapping: MutableMap<IElementType, TextAttributesKey> =
            HashMap<IElementType, TextAttributesKey>()
                .plus(modificators().map { it to VYPER_MODIFICATOR })
                .plus(constants().map { it to VYPER_CONSTANT })
                .plus(valueTypes().map { it to VYPER_VALUE_TYPE })
                .plus(referenceTypes().map { it to VYPER_REFERENCE_TYPE })
                .plus(boolLiteral().map { it to VYPER_BOOLEAN_LITERAL })
                .plus(stringLiterals().map { it to VYPER_STRING_LITERAL })
                .plus(multilineStringLiteral().map { it to VYPER_MULTILINE_STRING })
                .plus(numericLiterals().map { it to VYPER_NUMBER_LITERAL })
                .plus(operators().map { it to VYPER_OPERATOR })
                .plus(keywords().map { it to VYPER_KEYWORD })
                .plus(identifier().map { it to VYPER_IDENTIFIER })
                .plus(comment().map { it to VYPER_COMMENT }).toMutableMap()

        // todo #29: min, max, empty, constant, range are built-in functions
        private fun keywords() = setOf<IElementType>(
            IMPORT, FROM, AS, INTERFACE, IMPLEMENTS, STRUCT, DEF,
            IF, ELSE, FOR, BREAK, CONTINUE, RAISE, RETURN,
            ELIF, EVENT, PASS, ASSERT, CLEAR, RANGE, LOG
        )

        private fun boolLiteral() = setOf<IElementType>(BOOLEANLITERAL)

        private fun numericLiterals() = setOf<IElementType>(
            FIXEDNUMBER, HEXLITERAL, DECIMALNUMBER
        )

        private fun stringLiterals() = setOf<IElementType>(
            STRINGLITERALDOUBLE, STRINGLITERALDOUBLEB,
            STRINGLITERALSINGLE, STRINGLITERALSINGLEB
        )

        private fun modificators() = setOf<IElementType>(
            PUBLIC, NONREENTRANT, EXTERNAL, INTERNAL, IMMUTABLE, CONSTANT,
            PAYABLE, NONPAYABLE, VIEW, PURE,
        )

        private fun constants() = setOf<IElementType>()

        private fun valueTypes() = setOf<IElementType>(
            INTM, UINTM, BYTESM, ADDRESS, BOOL, STRING, BYTES,
        )

        private fun referenceTypes() = setOf<IElementType>(
            HASHMAP, DYNARRAY, STRUCT_TYPE,
        )

        private fun operators() = setOf<IElementType>(
            NOT, ASSIGN, PLUS_ASSIGN, MINUS_ASSIGN, MULT_ASSIGN, DIV_ASSIGN, PERCENT_ASSIGN,
            PLUS, MINUS, MULT, DIV, EXPONENT,
            LESS, MORE, LESSEQ, MOREEQ,
            AND, OR, EQ, NEQ, TO, PERCENT, IN,
        )

        private fun identifier() = setOf<IElementType>(
            IDENTIFIER
        )

        private fun comment() = setOf<IElementType>(
            COMMENT
        )

        private fun multilineStringLiteral() = setOf<IElementType>(
            MULTILINESTRINGTOKEN
        )
    }
}
