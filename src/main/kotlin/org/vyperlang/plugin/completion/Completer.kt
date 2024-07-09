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

object VyperCompleter {

    fun completeVarLiteral(element: VyperVarLiteral): Array<out LookupElement> =
        VyperResolver.lexicalDeclarations(element)
            .map { createLookup(it) }
            .map { PrioritizedLookupElement.withPriority(it, 15.0) }
            .toTypedArray()

    fun completeMemberAccess(element: VyperMemberAccessExpression): Array<out LookupElement> =
        resolveMemberAccess(element)
            .map { PrioritizedLookupElement.withPriority(this.createLookup(it), 15.0) }
            .toTypedArray()

    /**
     * Creates a lookup element for the given element.
     * @param elem The named element to target.
     * @return The lookup element.
     */
    private fun createLookup(elem: VyperNamedElement): LookupElement = when (elem) {
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

        is VyperStateVariableDeclaration -> LookupElementBuilder
            .create(elem, elem.name ?: "")
            .withIcon(VyperIcons.FILE)
            .withTypeText(elem.stateVariableType.text)

        is VyperFunctionDefinition -> LookupElementBuilder.create(elem, elem.name + "()")
                .withIcon(VyperIcons.FILE)
                .withTypeText("->${elem.funTypeAnnotation?.text ?: "()"}")

        else -> LookupElementBuilder.create(elem)
    }
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
