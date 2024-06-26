package org.vyperlang.plugin.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import org.vyperlang.plugin.VyperIcons
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.references.VyperResolver
import org.vyperlang.plugin.references.VyperResolver.resolveMemberAccess
import org.vyperlang.plugin.references.VyperResolver.resolveSelfAccessVarLiteralRec
import javax.swing.Icon

object VyperCompleter {

    fun completeVarLiteral(element: org.vyperlang.plugin.psi.VyperVarLiteral): Array<out LookupElement> {
        return VyperResolver.lexicalDeclarations(element)
            .map { createVarLookup(it) }
            .toTypedArray()
            .map {
                PrioritizedLookupElement.withPriority(it, 15.0)
            }
            .toTypedArray()
    }

    fun completeMemberAccess(element: org.vyperlang.plugin.psi.VyperMemberAccessExpression): Array<out LookupElement> {
        if (element.firstChild.firstChild is org.vyperlang.plugin.psi.VyperVarLiteral
            && (element.firstChild.firstChild as org.vyperlang.plugin.psi.VyperVarLiteral).name == "self"
        ) {

            val funDefs = (element.file as VyperFile).getStatements().filter { it is org.vyperlang.plugin.psi.VyperFunctionDefinition }
                .map { it as org.vyperlang.plugin.psi.VyperFunctionDefinition }.createFunDecLookups(VyperIcons.FILE)

            val stateVariables = resolveSelfAccessVarLiteralRec(element)
                .map { it as org.vyperlang.plugin.psi.VyperStateVariableDeclaration }
                .createStateVarLookups(VyperIcons.FILE).toMutableList()
            stateVariables.addAll(funDefs)
            return stateVariables.toTypedArray()
        }
        //msg VyperLocalVariableDeclaration
        return resolveMemberAccess(element).map { it as org.vyperlang.plugin.psi.VyperLocalVariableDeclaration }
            .createLocalVarLookups(VyperIcons.FILE)
    }

    private fun createVarLookup(elem: VyperNamedElement): LookupElement {
        when (elem) {
            is org.vyperlang.plugin.psi.VyperUserDefinedConstantsExpression -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText("constant( ${elem.type?.text ?: ""} )")

            is org.vyperlang.plugin.psi.VyperLocalVariableDeclaration -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText(elem.type.text ?: "")
            is org.vyperlang.plugin.psi.VyperParamDef -> return LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE).withTypeText(elem.type.text ?: "")
        }
        return LookupElementBuilder.create(elem)
    }


    private fun Collection<org.vyperlang.plugin.psi.VyperLocalVariableDeclaration>.createLocalVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.type.text)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<org.vyperlang.plugin.psi.VyperStateVariableDeclaration>.createStateVarLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.stateVariableType.text)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<org.vyperlang.plugin.psi.VyperFunctionDefinition>.createFunDecLookups(icon: Icon): Array<LookupElement> {
        return map {
            LookupElementBuilder.create(it, it.name + "()")
                .withIcon(icon).withTypeText("->${it.funTypeAnnotation?.text ?: "()"}")
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

fun funModifiers() = PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(org.vyperlang.plugin.psi.VyperTypes.DECORATOR))

fun baseTypes() = PlatformPatterns.psiElement().inside(PlatformPatterns.psiElement(org.vyperlang.plugin.psi.VyperType::class.java))