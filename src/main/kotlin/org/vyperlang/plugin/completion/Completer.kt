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
        return resolveMemberAccess(element).map { it as VyperLocalVariableDeclaration }
            .createLocalVarLookups(VyperIcons.FILE)
    }

    private fun createVarLookup(elem: VyperNamedElement): LookupElement {
        return when (elem) {
            is VyperUserDefinedConstantsExpression ->
                LookupElementBuilder.create(elem, elem.name ?: "")
                    .withIcon(VyperIcons.FILE)
                    .withTypeText("constant( ${elem.type?.text ?: ""} )")
            is VyperLocalVariableDeclaration ->
                LookupElementBuilder.create(elem, elem.name ?: "")
                    .withIcon(VyperIcons.FILE)
                    .withTypeText(elem.type.text ?: "")
            is VyperParamDef ->
                LookupElementBuilder.create(elem, elem.name ?: "")
                    .withIcon(VyperIcons.FILE)
                    .withTypeText(elem.type.text ?: "")
            else -> LookupElementBuilder.create(elem)
        }
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
                .withIcon(icon).withTypeText(it.stateVariableType.text)
        }.toTypedArray().map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()
    }

    private fun Collection<VyperFunctionDefinition>.createFunDecLookups(icon: Icon): Array<LookupElement> {
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
                    "bool",
                    "map()",
                    "string[]",
                    "HashMap[]"
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

/**
 * Completion on the file top level
 */
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
                    result.addElement(LookupElementBuilder.create("interface"))
                    result.addElement(LookupElementBuilder.create("import"))
                }
            }
        )
    }
}

/**
 * Completion when the user types @
 */
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