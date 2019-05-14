package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.vyperplugin.psi.*

object VyperResolver {
    fun resolveVarLiteral(element: VyperNamedElement): List<VyperNamedElement> {
        val resolveResultList = lexicalDeclarations(element).filter { it.name == element.name }.toList()
        return resolveResultList
    }

    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }): List<VyperNamedElement> {
//        val globalType = SolInternalTypeFactory.of(place.project).globalType
        return lexicalDeclRec(place, stop).distinct()
    }

    private fun lexicalDeclRec(place: PsiElement, stop: (PsiElement) -> Boolean): List<VyperNamedElement> {
        val parents =  place.ancestors
                .drop(1) // current element might not be a VyperElement
                .toList()

        val elements =  parents.takeWhile { it is VyperElement && !stop(it) }
                .flatMap { lexicalDeclarations(it, place) }

        if (place.parent is VyperSelfAccessExpression) {
            val file = if (place.file is VyperFile) place.file else null

            if (file != null) {
                val statements = ((file as VyperFile)
                        .getStatements()).flatMap { VyperResolver.lexicalDeclarations(it, place) }
                return listOf(elements, statements).flatten()
            }
        }
        return elements


    }

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> {
        return when (scope) {
            /*is SolVariableDeclaration -> {
                scope.declarationList?.declarationItemList?.filterIsInstance<SolNamedElement>()?.asSequence()
                        ?: scope.typedDeclarationList?.typedDeclarationItemList?.filterIsInstance<SolNamedElement>()?.asSequence()
                        ?: sequenceOf(scope)
            }
            is SolVariableDefinition -> lexicalDeclarations(scope.firstChild, place)

            is SolStateVariableDeclaration -> sequenceOf(scope)
            is SolContractDefinition -> {
                val childrenScope = sequenceOf(
                        scope.stateVariableDeclarationList,
                        scope.enumDefinitionList,
                        scope.structDefinitionList).flatten()
                        .map { lexicalDeclarations(it, place) }
                        .flatten()
                val extendsScope = scope.supers.asSequence()
                        .map { resolveTypeName(it).firstOrNull() }
                        .filterNotNull()
                        .map { lexicalDeclarations(it, place) }
                        .flatten()
                childrenScope + extendsScope
            }
            is SolFunctionDefinition -> {
                scope.parameters.asSequence() +
                        (scope.returns?.parameterDefList?.asSequence() ?: emptySequence())
            }
            is SolConstructorDefinition -> {
                scope.parameterList?.parameterDefList?.asSequence() ?: emptySequence()
            }
            is SolEnumDefinition -> sequenceOf(scope)

            is SolStatement -> {
                scope.children.asSequence()
                        .map { lexicalDeclarations(it, place) }
                        .flatten()
            }

            is SolBlock -> {
                scope.statementList.asSequence()
                        .map { lexicalDeclarations(it, place) }
                        .flatten()
            }

            is SolTupleStatement -> {
                scope.variableDeclaration?.let {
                    val declarationList = it.declarationList
                    val typedDeclarationList = it.typedDeclarationList
                    when {
                        declarationList != null -> declarationList.declarationItemList.asSequence()
                        typedDeclarationList != null -> typedDeclarationList.typedDeclarationItemList.asSequence()
                        else -> emptySequence()
                    }
                } ?: emptySequence()
            }

            else -> emptySequence()*/
            is VyperStateVariableDeclaration -> listOf(scope)
            else -> emptyList()
        }
    }

    private fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue
            shouldContinue = pred(it)
            result
        }
    }
}