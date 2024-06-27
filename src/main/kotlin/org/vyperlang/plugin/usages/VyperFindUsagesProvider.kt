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
            TokenSet.create(VyperTypes.IDENTIFIER),
            TokenSet.create(VyperTypes.COMMENT),
            TokenSet.create(VyperTypes.VAR_LITERAL)
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
            is VyperStateVariableDeclaration -> "state var"
            is VyperLocalVariableDeclaration -> "local var"
            is VyperFunctionDefinition -> "function"
            is VyperStructDefinition -> "struct"
            else -> ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return ""
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return when (element) {
            is VyperStateVariableDeclaration -> element.name + "state var"
            is VyperLocalVariableDeclaration -> element.name + "local var"
            is VyperFunctionDefinition -> element.name + "func"
            is VyperStructDefinition -> element.text + "struct"
            else -> ""
        }
    }
}