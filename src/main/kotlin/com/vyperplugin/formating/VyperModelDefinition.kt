package com.vyperplugin.formating

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.TokenSet
import com.vyperplugin.VyperLanguage
import com.vyperplugin.psi.VyperTypes.*

class VyperFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
        val spacingBuilder = createSpacingBuilder(settings)

        val containingFile = element.containingFile
        val vyperBlock = VyperFormattingBlock(element.node, null, Indent.getNoneIndent(), null, settings, spacingBuilder)

        return FormattingModelProvider.createFormattingModelForPsiFile(containingFile, vyperBlock, settings)
    }

    override fun getRangeAffectingIndent(file: PsiFile, offset: Int, elementAtOffset: ASTNode): TextRange? {
        return null
    }

    companion object {
        fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
            return SpacingBuilder(settings, VyperLanguage)
                    .after(TokenSet.create(LPAREN, LBRACE, LBRACKET)).none()
                    // Some old versions do not support .before(TokenSet), so we use more verbose form
                    // https://github.com/JetBrains/intellij-community/commit/fd4c8224c17d041bf53d556f5c74ffaf20acffe3
                    .before(RPAREN).none()
                    .before(RBRACE).none()
                    .before(RBRACKET).none()
                    .before(COMMA).none()
                    .before(SEMICOLON).none()


        }
    }
}