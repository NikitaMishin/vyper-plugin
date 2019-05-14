package com.vyperplugin.parser

import com.intellij.lang.LighterASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lang.impl.PsiBuilderImpl
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.intellij.util.containers.IntIntHashMap



class ParserUtil: GeneratedParserUtilBase() {

    class ParserState(builder_: PsiBuilder) {

        enum class PrimaryMode {
            NORMAL
        }

        private var builder: PsiBuilder = builder_
        var currentIndent: Int = 0
        private var primaryMode: PrimaryMode = PrimaryMode.NORMAL

        private val tokIndentCache = IntIntHashMap()

        private fun getPrecedingWhiteSpace(): String {
            var wsOffset = 0
            while (builder.rawLookup(wsOffset - 1) == TokenType.WHITE_SPACE)
                --wsOffset
            val wsStart: Int = builder.rawTokenTypeStart(wsOffset)
            return builder.getOriginalText().subSequence(wsStart, builder.getCurrentOffset()).toString()
        }

        private fun getFollowingWhiteSpace(): String {
            var nonWsOffset = 1
            while (builder.rawLookup(nonWsOffset) == TokenType.WHITE_SPACE)
                ++nonWsOffset
            val nonWsStart: Int = builder.rawTokenTypeStart(nonWsOffset)
            return builder.getOriginalText().subSequence(builder.rawTokenTypeStart(1), nonWsStart).toString()
        }

        fun getSpacesLeft() = getPrecedingWhiteSpace().length

        fun getSpacesRight() = getFollowingWhiteSpace().length

        fun getTokenIndent(): Int {

            val tokenStart = builder.currentOffset
            if (tokIndentCache.containsKey(tokenStart)) {
                return tokIndentCache.get(tokenStart)
            }

            var indent = -1
            val ws = getPrecedingWhiteSpace()
            val nlPos = ws.lastIndexOf('\n')
            if (nlPos != -1) {
                indent = ws.length - nlPos - 1
            }
            tokIndentCache.put(tokenStart, indent)
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
                val result: Boolean = parser.parse(builder_,level + 1)
                state.currentIndent = prevIndent
                return result
            }
            return false
    }

        @JvmStatic
        fun indEq(builder_: PsiBuilder, level:Int):Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() == state.currentIndent
        }

        @JvmStatic
        fun indEqAndNoSpaces(builder_: PsiBuilder, level:Int):Boolean {
            val state = getParserState(builder_)!!
            val spaces = state.getSpacesLeft() == 0
            return state.getTokenIndent() == state.currentIndent || spaces
        }

        @JvmStatic
        fun indGt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() > state.currentIndent
        }

        @JvmStatic
        fun indLt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            val tokenIndent = state.getTokenIndent()
            return tokenIndent >= 0 && tokenIndent < state.currentIndent
        }

        @JvmStatic
        fun indNone(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            return state.getTokenIndent() == - 1
        }

        @JvmStatic
        fun indOpt(builder_: PsiBuilder, level: Int): Boolean {
            val state = getParserState(builder_)!!
            val tokenInd = state.getTokenIndent()
            return tokenInd == - 1 || tokenInd > state.currentIndent
        }

        fun adapt_builder_(root:IElementType, builder_: PsiBuilder, parser: PsiParser, extendsSet: Array<TokenSet>):PsiBuilder {
            val psiBuilder = GeneratedParserUtilBase.adapt_builder_(root,builder_,parser,extendsSet)
            val state = ParserState(builder_)
            psiBuilder.putUserData(parserStateKey,state)

            return psiBuilder
        }

    }

}
