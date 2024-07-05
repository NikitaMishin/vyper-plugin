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

    override fun getWordsScanner(): WordsScanner = DefaultWordsScanner(
        VyperLexerAdapter(),
        TokenSet.create(VyperTypes.IDENTIFIER),
        TokenSet.create(VyperTypes.COMMENT),
        TokenSet.create(VyperTypes.VAR_LITERAL)
    )

    override fun canFindUsagesFor(psiElement: PsiElement) = psiElement is PsiNamedElement

    override fun getHelpId(psiElement: PsiElement): String? = null

    override fun getType(element: PsiElement): String = when (element) {
        is VyperStateVariableDeclaration -> "state variable"
        is VyperLocalVariableDefinition -> "local variable"
        is VyperFunctionDefinition -> "function"
        is VyperStructDefinition -> "struct"
        is VyperConstantDefinitionExpression -> "constant"
        is VyperImmutableDefinitionExpression -> "immutable"
        else -> ""
    }

    override fun getDescriptiveName(element: PsiElement): String = when (element) {
        is VyperFunctionCallArgument -> element.varLiteral?.name
        is PsiNamedElement -> element.name
        else -> element.text
    } ?: ""

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = when (element) {
//        is VyperStateVariableDeclaration -> element.name + "state variable"
//        is VyperLocalVariableDefinition -> element.name + "local variable"
//        is VyperFunctionDefinition -> element.name + "function"
//        is VyperStructDefinition -> element.text + "struct"
//        is VyperConstantDefinitionExpression -> element.name + "constant"
//        is VyperImmutableDefinitionExpression -> element.name + "immutable"
        else -> element.text
    }
}
