package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.isAncestor
import com.intellij.psi.util.parentOfType
import org.vyperlang.plugin.psi.*

object VyperResolver {
    /**
     * Resolves a variable literal to its declaration.
     */
    fun resolveVarLiteral(element: VyperNamedElement) =
        lexicalDeclarations(element).filter { it.name == element.name }.toList()

    /**
     * Finds all the lexical declarations applicable to the given place.
     * @param place The element that is being resolved.
     * @param stop A function that stops the search when it returns true. Default is to search the whole file.
     */
    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }) =
        place.ancestors
            .drop(1) // current element might not be a VyperElement
            .takeWhile { (it is VyperElement || it is VyperFile) && !stop(it) }
            .flatMap { lexicalDeclarations(it, place) }
            .toList()
            .filter { !it.isAncestor(place) } // don't suggest the element being defined
            .distinct()
            .toList()

    /**
     * Resolves a struct to its declaration.
     * @param element The variable literal that is referencing a struct.
     */
    fun resolveStruct(element: VyperVarLiteral) = findStructByName(element.file, element.name)

    /**
     * Resolves a struct member to its declaration.
     * @param element The variable literal that is a member of a struct.
     */
    fun resolveStructMember(element: VyperVarLiteral) =
        // find the definition of the struct
        findStructByName(element.file, element.parentOfType<VyperStructExpression>()?.varLiteral?.name)
            // then find the member in it
            .flatMap { it.childrenOfType<VyperLocalVariableDefinition>() }
            .filter { it.name == element.name }

    private fun findStructByName(file: PsiFile, structName: String?) =
        file.childrenOfType<VyperStructDefinition>()
            .filter { it.name == structName }

    fun resolveEventLog(element: VyperVarLiteral) =
        element.file.childrenOfType<VyperEventDeclaration>()
            .filter { it.name == element.name }

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> = when (scope) {
        is VyperLocalVariableDefinition -> listOf(scope)
        is VyperImportDirective -> listOf(scope)
        is VyperStructDefinition -> listOf(scope)
        is VyperConstantDefinitionExpression -> listOf(scope)
        is VyperImmutableDefinitionExpression -> listOf(scope)
        is VyperInterfaceDeclaration -> listOf(scope)

        is VyperStatement -> scope.prevSiblings
            .filterIsInstance<VyperStatement>()
            .map { it.childOfType<VyperLocalVariableDefinition>() }
            .filterNotNull()
            .flatMap { lexicalDeclarations(it, place) }
            .toList()

        is VyperFile -> scope.elements
            .filter { it !is VyperFunctionDefinition } // don't suggest functions as that will suggest parameters
            .flatMap { lexicalDeclarations(it, place) }

        is VyperFunctionDefinition -> scope.parameters?.paramDefList ?: emptyList()
        else -> emptyList()
    }

    // Recurse call expressions and arguments
    fun resolveCall(element: VyperCallExpression): Collection<VyperNamedElement> =
        sequenceOf(
            element.expressionList.flatMap { resolveExpression(it) },
            element.functionCallArgumentsList.flatMap {
                it.functionCallArgumentList.flatMap { arg -> resolveFunctionArgument(arg) }
            }
        ).flatten().toList()

    private fun resolveFunctionArgument(arg: VyperFunctionCallArgument) = sequenceOf(
        resolveExpression(arg.expression),
        when (arg.varLiteral) {
            is VyperVarLiteral -> resolveVarLiteral(arg.varLiteral!!)
            else -> emptyList()
        }
    ).flatten()

    private fun resolveExpression(it: VyperExpression) = when (it) {
        is VyperMemberAccessExpression -> resolveMemberAccess(it)
        else -> emptyList() // other references should be handled by `VarLiteral`?
    }

    fun resolveMemberAccess(element: VyperMemberAccessExpression): List<VyperNamedElement> = when (getFirstLiteralName(element.expression)) {
        "msg" -> VyperInternalTypeFactory(element.project).msg.children.filterIsInstance<VyperLocalVariableDefinition>()
        "self" -> element.file.selfElements
        is String -> resolveCustomMembers(element, getFirstLiteralName(element.expression))
        else -> emptyList() // todo: support interfaces, structs and `block` built-in
    }

    private fun resolveCustomMembers(element: VyperMemberAccessExpression, name: String?) =
        sequenceOf(element.file.imports, element.file.structs, element.file.interfaces)
            .flatten()
            .filter { it.name == name }
            .flatMap { when(it) {
                is VyperStructDefinition -> it.childrenOfType<VyperLocalVariableDefinition>()
                is VyperInterfaceDeclaration -> it.childrenOfType<VyperInterfaceFunction>()
                is VyperImportDirective -> emptyList() // imports not supported for finding members
                else -> throw IllegalStateException("Unknown type: $it")
            }}
            .filter { it.name == element.varLiteral.name }
            .toList()

    fun resolveInterface(element: VyperVarLiteral): List<VyperNamedElement> = findInterface(element.file, element.name)

    private fun findInterface(file: VyperFile, name: String?): List<VyperNamedElement> =
        sequenceOf(file.interfaces, file.imports)
            .flatten()
            .filter { it.name == name }
            .toList()

    private fun getFirstLiteralName(element: VyperExpression?): String? = when (element) {
        is VyperPrimaryExpression -> element.varLiteral?.text
        is VyperCallExpression -> getFirstLiteralName(element.expressionList.firstOrNull())
        else -> null
    }

}
