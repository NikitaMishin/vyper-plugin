package com.vyperplugin

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.vyperplugin.psi.VyperTypes.*
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Defaults

class VyperHighlighterFactory : SingleLazyInstanceSyntaxHighlighterFactory() {
    override fun createHighlighter() = VyperHighlighter()
}

class VyperHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = VyperLexerAdapter()


    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> {
        return pack(tokenMapping[tokenType])
    }

    companion object {
        val VYPER_KEYWORD = TextAttributesKey.createTextAttributesKey("VYPER_KEYWORD", Defaults.KEYWORD)
        val VYPER_OPERATOR = TextAttributesKey.createTextAttributesKey("VYPER_OPERATOR", Defaults.OPERATION_SIGN)
        val VYPER_STRING_LITERAL = TextAttributesKey.createTextAttributesKey("VYPER_TEXT_LITERAL", Defaults.STRING)
        val VYPER_NUMBER_LITERAL = TextAttributesKey.createTextAttributesKey("VYPER_NUMBER_LITERAL", Defaults.NUMBER)
        val VYPER_IDENTIFIER = TextAttributesKey.createTextAttributesKey("VYPER_IDENTIFIER", Defaults.IDENTIFIER)
        val VYPER_MODIFICATOR = TextAttributesKey.createTextAttributesKey("VYPER_MODIFICATOR", Defaults.KEYWORD)
        val VYPER_BOOLEAN_LITERAL = TextAttributesKey.createTextAttributesKey("VYPER_BOOLEAN_LITERAL", Defaults.KEYWORD)
        val VYPER_CONSTANT = TextAttributesKey.createTextAttributesKey("VYPER_CONSTANT")
        val VYPER_UNIT_TYPE = TextAttributesKey.createTextAttributesKey("VYPER_UNIT_TYPE", Defaults.KEYWORD)
        val VYPER_REFERENCE_TYPE = TextAttributesKey.createTextAttributesKey("VYPER_REFERENCE_TYPE", Defaults.KEYWORD)
        val VYPER_VALUE_TYPE = TextAttributesKey.createTextAttributesKey("VYPER_VALUE_TYPE", Defaults.KEYWORD)
        val VYPER_COMMENT = TextAttributesKey.createTextAttributesKey("VYPER_COMMENT", Defaults.LINE_COMMENT)

        private val tokenMapping: MutableMap<IElementType, TextAttributesKey> =
                HashMap<IElementType, TextAttributesKey>()
                        .plus(modificators().map { it to VYPER_MODIFICATOR })
                        .plus(constants().map { it to VYPER_CONSTANT })
                        .plus(valueTypes().map { it to VYPER_VALUE_TYPE })
                        .plus(referenceTypes().map { it to VYPER_REFERENCE_TYPE })
                        .plus(unitTypes().map { it to VYPER_UNIT_TYPE })
                        .plus(boolLiteral().map { it to VYPER_BOOLEAN_LITERAL })
                        .plus(stringLiterals().map { it to VYPER_STRING_LITERAL })
                        .plus(numericLiterals().map { it to VYPER_NUMBER_LITERAL })
                        .plus(operators().map { it to VYPER_OPERATOR })
                        .plus(keywords().map { it to VYPER_KEYWORD })
                        .plus(identifier().map { it to VYPER_IDENTIFIER })
                        .plus(comment().map { it to VYPER_COMMENT }).toMutableMap()


        private fun keywords() = setOf<IElementType>(
                IMPORT, AS, FROM,
                CONTRACT, IMPLEMENTS, STRUCT, DEF, UNITS,
                IF, ELSE, FOR, BREAK, CONTINUE, RAISE, RETURN,
                MAP, ELIF, EVENT, PASS, SELF, ASSERT, CLEAR
        )

        private fun boolLiteral() = setOf<IElementType>(BOOLEANLITERAL)

        private fun numericLiterals() = setOf<IElementType>(
                FIXEDNUMBER, HEXLITERAL, DECIMALNUMBER
        )

        private fun stringLiterals() = setOf<IElementType>(
                MULTILINESTRINGTOKEN, STRINGLITERALDOUBLE, STRINGLITERALDOUBLEB,
                STRINGLITERALSINGLE, STRINGLITERALSINGLEB
        )


        private fun modificators() = setOf<IElementType>(
                PUBLIC, PRIVATE, PAYABLE, NONREENTRANT, MODIFYING, CONSTANT
        )


        private fun constants() = setOf<IElementType>(
                MAX_INT128, MAX_DECIMAL, MAX_UINT256, MIN_DECIMAL, MIN_INT128, ZERO_ADDRESS, EMPTY_BYTES32
        )


        private fun valueTypes() = setOf<IElementType>(
                INT128, UINT256, BYTES32, ADDRESS, FIXED, BOOL, STRING, INDEXED_DATA
        )

        private fun referenceTypes() = setOf<IElementType>(
                STRUCT_TYPE // map and list not here because already would be higlighted
        )

        private fun unitTypes() = setOf<IElementType>(
                UNIT_TYPE //u sure? TIMESTAMP, TIMEDLTA, and so on
        )

        private fun operators() = setOf<IElementType>(
                NOT, ASSIGN, PLUS_ASSIGN, MINUS_ASSIGN, MULT_ASSIGN, DIV_ASSIGN, PERCENT_ASSIGN,
                PLUS, MINUS, MULT, DIV, EXPONENT,
                LESS, MORE, LESSEQ, MOREEQ,
                AND, OR,
                EQ, NEQ, TO, PERCENT
        )

        private fun identifier() = setOf<IElementType>(
                IDENTIFIER
        )

        private fun comment() = setOf<IElementType>(
                COMMENT
        )

    }
}
