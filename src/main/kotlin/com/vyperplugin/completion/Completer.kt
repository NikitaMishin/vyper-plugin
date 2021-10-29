package com.vyperplugin.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.vyperplugin.VyperIcons
import com.vyperplugin.psi.*
import com.vyperplugin.references.VyperResolver
import com.vyperplugin.references.VyperResolver.resolveMemberAccess
import com.vyperplugin.references.VyperResolver.resolveSelfAccessVarLiteralRec
import javax.swing.Icon

object VyperCompleter {

    fun completeVarLiteral(element: VyperVarLiteral): Array<out LookupElement> {
        return VyperResolver.lexicalDeclarations(element)
            .map { createVarLookup(it) }
            .toTypedArray()
            .map {
                PrioritizedLookupElement.withPriority(it, 15.0)
            }
            .toTypedArray()
    }

    fun completeTypes(): Array<out LookupElement> {
        return listOf(
            "int128",
            "uint256",
            "bytes32",
            "bytes[]",
            "address",
            "fixed",
            "bool",
            "map()",
            "timestamp",
            "string[]"
        ).map {
            LookupElementBuilder.create(it)
                .withIcon(VyperIcons.FILE)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    fun completeMemberAccess(element: VyperMemberAccessExpression): Array<out LookupElement> {
        if (element.firstChild.firstChild is VyperVarLiteral
            && (element.firstChild.firstChild as VyperVarLiteral).name == "self"
        ) {

            val funDefs = (element.file as VyperFile).getStatements().filter { it is VyperFunctionDefinition }
                .map { it as VyperFunctionDefinition }.createFunDecLookups(VyperIcons.FILE)

            val stateVariables = resolveSelfAccessVarLiteralRec(element)
                .map { it as VyperStateVariableDeclaration }
                .createStateVarLookups(VyperIcons.FILE).toMutableList()
            stateVariables.addAll(funDefs)
            return stateVariables.toTypedArray()
        }
        //msg VyperLocalVariableDeclaration
        return resolveMemberAccess(element).map { it as VyperLocalVariableDeclaration }
            .createLocalVarLookups(VyperIcons.FILE)
    }

    private fun createVarLookup(elem: VyperNamedElement): LookupElement {
        when (elem) {
            is VyperUserDefinedConstantsExpression -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText("constant( ${elem.type?.text ?: ""} )")

            is VyperLocalVariableDeclaration -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText(elem.type.text ?: "")
            is VyperParamDef -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText(elem.type.text ?: "")
        }
        return LookupElementBuilder.create(elem)
    }

    private fun Collection<VyperNamedElement>.createVarLookups(): Array<LookupElement> =
        createVarLookups(VyperIcons.FILE)

    private fun Collection<VyperNamedElement>.createVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<VyperLocalVariableDeclaration>.createLocalVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.type.text)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<VyperStateVariableDeclaration>.createStateVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.type.text)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<VyperFunctionDefinition>.createFunDecLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name + "()")
                .withIcon(icon).withTypeText("->${it.type?.text ?: "()"}")
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }
}

fun baseTypes() = hashSetOf("__init__()", "__default__")

class VyperBaseTypesCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(CompletionType.BASIC, stateVarInsideContract(),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    baseTypes().asSequence()
                        .map { "$it " }
                        .map(LookupElementBuilder::create)
                        .map(result::addElement)
                        .toList()
                }

            })
    }
}

fun stateVarInsideContract() =
    PlatformPatterns.psiElement(VyperTypes.IDENTIFIER)
        .inside(PlatformPatterns.psiElement(VyperFunctionDefinition::class.java))
        .inside(VyperFile::class.java)