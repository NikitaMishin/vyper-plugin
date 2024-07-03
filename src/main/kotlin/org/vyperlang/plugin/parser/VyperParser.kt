package org.vyperlang.plugin.parser

import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase.*
import com.intellij.psi.tree.IElementType

private const val LEVEL = 0
private const val FRAME_NAME = ""
private const val IS_PINNED = true

class VyperParser : BaseVyperParser() {
    /**
     * We want to override `adapt_builder_` but that's not possible since it's static.
     * Therefore, we override the whole `parseLight` method instead.
     */
    override fun parseLight(type: IElementType?, builder: PsiBuilder?) {
        val psiBuilder = ParserUtil.adapt_builder_(type, builder, this, EXTENDS_SETS_)
        val marker = enter_section_(psiBuilder, LEVEL, _COLLAPSE_, FRAME_NAME)
        val isParsed = parse_root_(type, psiBuilder)
        exit_section_(psiBuilder, LEVEL, marker, type, isParsed, IS_PINNED, TRUE_CONDITION)
    }
}
