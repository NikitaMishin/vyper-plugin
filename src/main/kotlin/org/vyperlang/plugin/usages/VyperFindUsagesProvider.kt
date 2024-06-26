package org.vyperlang.plugin.usages

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import org.vyperlang.plugin.VyperLexerAdapter
import org.vyperlang.plugin.psi.*

class VyperFindUsagesProvider : FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            VyperLexerAdapter(),
            TokenSet.create(org.vyperlang.plugin.psi.VyperTypes.IDENTIFIER),
            TokenSet.create(org.vyperlang.plugin.psi.VyperTypes.COMMENT),
            TokenSet.create(org.vyperlang.plugin.psi.VyperTypes.VAR_LITERAL)
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is org.vyperlang.plugin.psi.VyperStateVariableDeclaration -> "state var"
            is org.vyperlang.plugin.psi.VyperLocalVariableDeclaration -> "local var"
            is org.vyperlang.plugin.psi.VyperFunctionDefinition -> "function"
            is org.vyperlang.plugin.psi.VyperStructDefinition -> "struct"
            else -> ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return ""
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return when (element) {
            is org.vyperlang.plugin.psi.VyperStateVariableDeclaration -> element.name + "state var"
            is org.vyperlang.plugin.psi.VyperLocalVariableDeclaration -> element.name + "local var"
            is org.vyperlang.plugin.psi.VyperFunctionDefinition -> element.name + "func"
            is org.vyperlang.plugin.psi.VyperStructDefinition -> element.text + "struct"
            else -> ""
        }
    }
}