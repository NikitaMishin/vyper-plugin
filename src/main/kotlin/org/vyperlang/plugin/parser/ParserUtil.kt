package org.vyperlang.plugin.parser

import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.vyperlang.plugin.parser.ParserUtil.Companion.parserStateKey
import org.vyperlang.plugin.parser.ParserUtil.ParserState


class ParserUtil : GeneratedParserUtilBase() {

    class ParserState(builder_: PsiBuilder) {
        private var builder: PsiBuilder = builder_
        var currentIndent: Int = 0
        private val tokenIndentCache = hashMapOf<Int, Int>()

        private fun getPrecedingWhiteSpace(): String {
            var wsOffset = 0
            while (builder.rawLookup(wsOffset - 1) == TokenType.WHITE_SPACE)
                --wsOffset
            val wsStart: Int = builder.rawTokenTypeStart(wsOffset)
            return builder.originalText.subSequence(wsStart, builder.currentOffset).toString()
        }

        private fun getFollowingWhiteSpace(): String {
            var nonWsOffset = 1
            while (builder.rawLookup(nonWsOffset) == TokenType.WHITE_SPACE)
                ++nonWsOffset
            val nonWsStart: Int = builder.rawTokenTypeStart(nonWsOffset)
            return builder.originalText.subSequence(builder.rawTokenTypeStart(1), nonWsStart).toString()
        }

        fun getSpacesLeft() = getPrecedingWhiteSpace().length

        fun getTokenIndent(): Int {

            val tokenStart = builder.currentOffset
            if (tokenIndentCache.containsKey(tokenStart)) {
                return tokenIndentCache[tokenStart]!!
            }

            var indent = -1
            val ws = getPrecedingWhiteSpace()
            val nlPos = ws.lastIndexOf('\n')
            if (nlPos != -1) {
                indent = ws.length - nlPos - 1
            }
            tokenIndentCache[tokenStart] = indent
            return indent
        }
    }

    companion object {
        private val parserStateKey: Key<ParserState> = Key("parser-state")

        fun getParserState(builder_: PsiBuilder) = builder_.getUserData(parserStateKey)

        @JvmStatic
        fun indented(builder_: PsiBuilder, level: Int, parser: Parser): Boolean {
            val state = getParserState(builder_)!!

            val tokenIndent = state.getTokenIndent()
            if (tokenIndent > state.currentIndent) {
                val prevIndent = state.currentIndent
                state.currentIndent = tokenIndent
                val result: Boolean = parser.parse(builder_, level + 1)
                state.currentIndent = prevIndent
                return result
            }
            return false
        }

        /**
         * Indentation is equal to the current indentation level
         */
        @JvmStatic
        fun indEq(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() == state.currentIndent
        }

        /**
         * Indentation is equal to the current indentation level and there are no spaces between the token and the indentation
         */
        @JvmStatic
        fun indEqAndNoSpaces(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            val spaces = state.getSpacesLeft() == 0
            return state.getTokenIndent() == state.currentIndent || spaces
        }

        /**
         * Indentation is greater than the current indentation level
         */
        @JvmStatic
        fun indGt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() > state.currentIndent
        }

        /**
         * Indentation is less than the current indentation level
         */
        @JvmStatic
        fun indLt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            val tokenIndent = state.getTokenIndent()
            return tokenIndent >= 0 && tokenIndent < state.currentIndent
        }

        /**
         * Indentation is not allowed
         */
        @JvmStatic
        fun indNone(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() == -1
        }

        /**
         * Indentation is optional if the token is at the beginning of the line
         */
        @JvmStatic
        fun indOpt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            val tokenInd = state.getTokenIndent()
            return tokenInd == -1 || tokenInd > state.currentIndent
        }

        fun adapt_builder_(
            root: IElementType?,
            builder_: PsiBuilder?,
            parser: PsiParser,
            extendsSet: Array<TokenSet>
        ): PsiBuilder {
            val psiBuilder = GeneratedParserUtilBase.adapt_builder_(root, builder_, parser, extendsSet)
            val state = ParserState(builder_!!)
            psiBuilder.putUserData(parserStateKey, state)
            return psiBuilder
        }

    }

}
