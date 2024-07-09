package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.isAncestor
import com.intellij.psi.util.parentOfType
import org.vyperlang.plugin.psi.*

object VyperResolver {
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
            .filter { !it.isAncestor(place) } // don't suggest the element being defined
            .distinct()
            .toList()

    /**
     * Resolves the members of a struct.
     * @param element The literal that might reference a struct.
     */
    fun resolveStructMembers(element: VyperVarLiteral) =
        element.file.findStruct(element.parentOfType<VyperStructExpression>()?.varLiteral?.name)
            .flatMap { it.childrenOfType<VyperLocalVariableDefinition>() }

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> = when (scope) {
        is VyperLocalVariableDefinition -> listOf(scope)
        is VyperImportDirective -> listOf(scope)
        is VyperStructDeclaration -> listOf(scope)
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

    fun resolveMemberAccess(element: VyperMemberAccessExpression): List<VyperNamedElement> =
        when (getFirstLiteralName(element.expression)) {
            "msg" -> VyperInternalTypeFactory(element.project).msg.children.filterIsInstance<VyperLocalVariableDefinition>()
            "self" -> element.file.selfElements
            is String -> resolveInterfaceMembers(element, getFirstLiteralName(element.expression))
            else -> emptyList() // todo #37: `block` built-in
        }

    private fun resolveInterfaceMembers(element: VyperMemberAccessExpression, name: String?) =
        lexicalDeclarations(element)
            .filter { it.name == name }
            .flatMap { findInterfaceMembers(it) }
            .toList()

    fun resolveInterfaceFunctionModifiers(element: VyperMemberAccessExpression): List<String> =
        resolveMemberAccess(element)
            .flatMap { findInterfaceMembers(it) }
            .flatMap { it.childrenOfType<VyperInterfaceFunctionModifier>() }
            .map { it.text }

    private fun findInterfaceMembers(element: VyperNamedElement?): Collection<VyperInterfaceFunction> = when (element) {
        is VyperParamDef -> findInterfaceMembers(element, element.type.text)
        is VyperStateVariableDeclaration -> findInterfaceMembers(element, element.stateVariableType.text)
        is VyperImmutableDefinitionExpression -> findInterfaceMembers(element, element.type?.text)
        is VyperConstantDefinitionExpression -> findInterfaceMembers(element, element.type?.text)
        is VyperInterfaceDeclaration -> element.childrenOfType<VyperInterfaceFunction>()
        is VyperInterfaceFunction -> listOf(element)
        else -> emptyList()
    }

    private fun findInterfaceMembers(element: VyperNamedElement, interfaceName: String?) =
        resolveInterfaces(element).filter { it.name == interfaceName }.flatMap { findInterfaceMembers(it) }

    /**
     * Returns all the interfaces in the file. They may be either an import or a declaration.
     * Import declarations are not resolved to their actual interfaces.
     */
    fun resolveInterfaces(element: VyperElement): List<VyperNamedElement> =
        sequenceOf(element.file.interfaces, element.file.imports).flatten().toList()

    private fun getFirstLiteralName(element: VyperExpression?): String? = when (element) {
        is VyperPrimaryExpression -> element.varLiteral?.text
        is VyperCallExpression -> getFirstLiteralName(element.expressionList.firstOrNull())
        else -> null
    }

}
