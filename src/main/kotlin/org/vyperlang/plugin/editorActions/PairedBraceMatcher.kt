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

    private companion object {
        val PAIRS: Array<BracePair> = arrayOf(
            BracePair(org.vyperlang.plugin.psi.VyperTypes.LBRACE, org.vyperlang.plugin.psi.VyperTypes.RBRACE, true),
            BracePair(org.vyperlang.plugin.psi.VyperTypes.LBRACKET, org.vyperlang.plugin.psi.VyperTypes.RBRACKET, true),
            BracePair(org.vyperlang.plugin.psi.VyperTypes.LPAREN, org.vyperlang.plugin.psi.VyperTypes.RPAREN, true)
        )
    }
}