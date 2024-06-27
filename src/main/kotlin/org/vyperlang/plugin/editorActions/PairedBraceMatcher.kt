package org.vyperlang.plugin.editorActions

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.vyperlang.plugin.psi.VyperTypes

class PairedBraceMatcherVyper : PairedBraceMatcher {
    override fun isPairedBracesAllowedBeforeType(
        lbraceType: IElementType,
        contextType: IElementType?
    ): Boolean = true

    override fun getPairs(): Array<BracePair> {
        return PAIRS
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int {
        return openingBraceOffset
    }
}

val PAIRS: Array<BracePair> = arrayOf(
    BracePair(VyperTypes.LBRACE, VyperTypes.RBRACE, true),
    BracePair(VyperTypes.LBRACKET, VyperTypes.RBRACKET, true),
    BracePair(VyperTypes.LPAREN, VyperTypes.RPAREN, true)
)
