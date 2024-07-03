package org.vyperlang.plugin.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.StandardPatterns.or
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import org.vyperlang.plugin.VyperIcons
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.psi.VyperTypes.*
import org.vyperlang.plugin.references.VyperResolver
import org.vyperlang.plugin.references.VyperResolver.resolveMemberAccess
import org.vyperlang.plugin.references.VyperResolver.resolveSelfVariables
import javax.swing.Icon

object VyperCompleter {

    fun completeVarLiteral(element: VyperVarLiteral): Array<out LookupElement> =
        VyperResolver.lexicalDeclarations(element)
            .map { createVarLookup(it) }
            .map { PrioritizedLookupElement.withPriority(it, 15.0) }
            .toTypedArray()

    fun completeMemberAccess(element: VyperMemberAccessExpression): Array<out LookupElement> {
        if (element.firstChild.firstChild is VyperVarLiteral
            && (element.firstChild.firstChild as VyperVarLiteral).name == "self"
        ) {

            val funDefs = (element.file as VyperFile).statements
                .filterIsInstance<VyperFunctionDefinition>()
                .createFunDecLookups(VyperIcons.FILE)

            val stateVariables = resolveSelfVariables(element)
                .map { it as VyperStateVariableDeclaration }
                .createStateVarLookups(VyperIcons.FILE).toMutableList()
            stateVariables.addAll(funDefs)
            return stateVariables.toTypedArray()
        }
        return resolveMemberAccess(element).map { it as VyperLocalVariableDefinition }
            .createLocalVarLookups(VyperIcons.FILE)
    }

    private fun createVarLookup(elem: VyperNamedElement): LookupElement = when (elem) {
        is VyperConstantDefinitionExpression ->
            LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE)
                .withTypeText("constant( ${elem.type?.text ?: ""} ) = ")

        is VyperImmutableDefinitionExpression ->
            LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE)
                .withTypeText("immutable( ${elem.type?.text ?: ""} )")

        is VyperLocalVariableDefinition ->
            LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE)
                .withTypeText(elem.type.text ?: "")

        is VyperParamDef ->
            LookupElementBuilder.create(elem, elem.name ?: "")
                .withIcon(VyperIcons.FILE)
                .withTypeText(elem.type.text ?: "")

        else -> LookupElementBuilder.create(elem)
    }


    private fun Collection<VyperLocalVariableDefinition>.createLocalVarLookups(icon: Icon): Array<LookupElement> =
        map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.type.text)
        }.map {
            PrioritizedLookupElement.withPriority(it, 15.0)
        }.toTypedArray()

    private fun Collection<VyperStateVariableDeclaration>.createStateVarLookups(icon: Icon): Array<LookupElement> =
        map {
            LookupElementBuilder.create(it, it.name ?: "")
                .withIcon(icon).withTypeText(it.stateVariableType.text)
        }.map { PrioritizedLookupElement.withPriority(it, 15.0) }
            .toTypedArray()

    private fun Collection<VyperFunctionDefinition>.createFunDecLookups(icon: Icon): Array<LookupElement> = map {
        LookupElementBuilder.create(it, it.name + "()")
            .withIcon(icon).withTypeText("->${it.funTypeAnnotation?.text ?: "()"}")
    }.map {
        PrioritizedLookupElement.withPriority(it, 15.0)
    }.toTypedArray()
}

class VyperBaseTypesCompletionContributor : LookupContributor(
    baseTypes,
    "int128",
    "uint256",
    "bytes32",
    "bytes[",
    "address",
    "bool",
    "string["
)

class VyperReferenceTypesCompletionContributor : LookupContributor(
    refTypes,
    "HashMap[]",
    "DynArray[]"
)

/**
 * Completion on the file top level
 */
class VyperInFileContributor : LookupContributor(
    inFile,
    "def", "struct", "interface", "import"
)

/**
 * Completion when the user types @
 */
class VyperFunModifierContributor : LookupContributor(
    funModifiers,
    "external",
    "internal",
    "deploy",
    "pure",
    "view",
    "payable",
    "nonreentrant(\"lock\")",
)

/**
 * Completion when the user is creating state vars
 */
class VyperVariableModifierContributor : LookupContributor(
    inStateVariableType,
    "public(", "constant(", "immutable("
)

val inFile by lazy {
    psiElement().inFile(PlatformPatterns.psiFile())
        .and(psiElement().withSuperParent(2, PlatformPatterns.psiFile()))
}

val inStateVariableType by lazy {
    psiElement().inside(psiElement(VyperStateVariableType::class.java))
}

val staticVarType by lazy {
    psiElement().afterSibling(psiElement(LPAREN).afterLeaf(
        or(psiElement(CONSTANT), psiElement(IMMUTABLE))))
}

val funModifiers by lazy {
    psiElement().afterLeaf(psiElement(DECORATOR))
}

val baseTypes by lazy {
    or(
        psiElement().inside(psiElement(VyperType::class.java)),
        staticVarType,
    )
}

val refTypes by lazy {
    psiElement().inside(VyperStateVariableType::class.java)
}

abstract class LookupContributor(place: ElementPattern<out PsiElement>, vararg lookup: String) :
    CompletionContributor(), DumbAware {
    init {
        extend(CompletionType.BASIC, place, object : CompletionProvider<CompletionParameters>() {
            override fun addCompletions(
                parameters: CompletionParameters,
                context: ProcessingContext,
                result: CompletionResultSet
            ) {
                lookup.forEach { result.addElement(LookupElementBuilder.create(it)) }
            }
        })
    }
}

