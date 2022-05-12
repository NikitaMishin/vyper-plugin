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

class VyperBaseTypesCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(CompletionType.BASIC, baseTypes(),
            object : CompletionProvider<CompletionParameters>() {
                val types = listOf(
                    "int128",
                    "uint256",
                    "bytes32",
                    "bytes[]",
                    "address",
                    "fixed",
                    "bool",
                    "map()",
                    "timestamp",
                    "string[]",
                    "constant()"
                )

                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    types.forEach { result.addElement(LookupElementBuilder.create(it)) }
                }

            })
    }
}

class VyperInFileContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, inFile(),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    result.addElement(LookupElementBuilder.create("def"))
                    result.addElement(LookupElementBuilder.create("struct"))
                    result.addElement(LookupElementBuilder.create("contract"))
                    result.addElement(LookupElementBuilder.create("import"))
                }
            }
        )
    }
}

class VyperFunModifierContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, funModifiers(),
            object : CompletionProvider<CompletionParameters>() {
                val mods = listOf(
                    "public",
                    "private",
                    "payable",
                    "nonreentrant()",
                    "modifying",
                    "constant",
                    "external",
                    "view",
                    "internal",
                    "pure",
                )

                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    mods.forEach { result.addElement(LookupElementBuilder.create(it)) }
                }
            }
        )
    }
}

fun inFile() = PlatformPatterns.psiElement().inFile(PlatformPatterns.psiFile())
    .and(PlatformPatterns.psiElement().withSuperParent(2, PlatformPatterns.psiFile()))

fun funModifiers() = PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(VyperTypes.DECORATOR))

fun baseTypes() = PlatformPatterns.psiElement().inside(PlatformPatterns.psiElement(VyperType::class.java))