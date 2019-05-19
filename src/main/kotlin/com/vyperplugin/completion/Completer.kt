package com.vyperplugin.completion

import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.vyperplugin.VyperIcons
import com.vyperplugin.psi.*
import com.vyperplugin.references.VyperResolver.resolveMemberAccess
import javax.swing.Icon

object VyperCompleter {
//    fun completeSelfAccess(element: VyperSelfAccessExpression) : Array<out LookupElement>{
//
//        val stateVars = resolveSelfAccessVarLiteralRec(element)
//        val funDefs = mutableListOf<VyperNamedElement>()
//        funDefs.addAll((element.file as VyperFile)
//                .getStatements().filter { it is VyperFunctionDefinition }.map{it as VyperFunctionDefinition })
//        return (stateVars + funDefs).createVarLookups()
//    }

    fun completeMemberAccess(element: VyperMemberAccessExpression):Array<out LookupElement> {
        return resolveMemberAccess(element).createVarLookups()
    }

    private fun Collection<VyperNamedElement>.createVarLookups(): Array<LookupElement> = createVarLookups(VyperIcons.FILE)

    private fun Collection<VyperNamedElement>.createVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                    .withIcon(icon)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }
}