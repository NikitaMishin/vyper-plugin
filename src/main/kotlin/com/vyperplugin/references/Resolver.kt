package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.intellij.util.containers.toMutableSmartList
import com.vyperplugin.psi.*
import com.vyperplugin.psi.VyperTypes.*


object VyperResolver {
    fun resolveVarLiteral(element: VyperNamedElement): List<PsiElement> {
        val resolveResultList = lexicalDeclarations(element).filter { it.name == element.name }.toList()
        return resolveResultList
    }

    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }): List<VyperNamedElement> {

        return lexicalDeclRec(place, stop).distinct()
    }

    private fun lexicalDeclRec(place: PsiElement, stop: (PsiElement) -> Boolean): List<VyperNamedElement> {
        val parents =  place.ancestors
                .drop(1) // current element might not be a VyperElement
                .toList()

        val elements =  parents
                .takeWhile { (it is VyperElement || it is VyperFile) && !stop(it) }
                .flatMap { lexicalDeclarations(it, place) }

        return elements


    }

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> {
        return when (scope) {

            is VyperLocalVariableDeclaration -> listOf(scope)

            is VyperStatement -> lexicalDeclarations(scope.firstChild,place)

            is VyperFile -> scope.getStatements().filter{it !is VyperFunctionDefinition}.flatMap { VyperResolver.lexicalDeclarations(it,place)}

            is VyperUserDefinedConstantsExpression -> listOf(scope)

            is VyperLocalVariableDefinition -> lexicalDeclarations(scope.localVariableDeclaration,place)

            is VyperFunctionDefinition -> {
                val params = scope.parameters?.paramDefList?.toTypedArray() ?: emptyArray()
                val statements : MutableList<VyperNamedElement> = scope.statement
                        ?.siblings
                        ?.toMutableList()
                        ?.flatMap { VyperResolver.lexicalDeclarations(it,place) }?.toMutableSmartList() ?: mutableListOf()
                statements.addAll(params)
                statements

            }
                else -> emptyList()
            }
        }


    fun resolveFunction(element: VyperCallExpression, skipThis: Boolean = false): Collection<FunctionResolveResult> {
        return resolveFunRec(element,skipThis).filter { it.name == element.referenceName }.map{FunctionResolveResult(it)}
    }

    private fun resolveFunRec(element: VyperCallExpression, skipThis: Boolean = false): List<VyperFunctionDefinition> {
        val res = mutableListOf<VyperFunctionDefinition>()
        val ref : VyperElement = element
        return when {
            ref.firstChild is VyperPrimaryExpression -> emptyList()
            ref.firstChild is VyperMemberAccessExpression
                    && (ref.firstChild.firstChild.firstChild as VyperVarLiteral).name == "self" -> {
                res.addAll((element.file as VyperFile)
                        .getStatements()
                        .filter { it is VyperFunctionDefinition }
                        .map { it as VyperFunctionDefinition })
                res
            }
            else -> emptyList()
        }

//        if(ref is VyperMemberAccessExpression) res.addAll((element.file as VyperFile)
//                                                .getStatements().filter { it is VyperFunctionDefinition}.map{it as VyperFunctionDefinition})

    }

    fun resolveMemberAccess(element: VyperMemberAccessExpression): List<VyperNamedElement> {
        if( element.firstChild.firstChild is VyperVarLiteral
                && (element.firstChild.firstChild as VyperVarLiteral).name == "msg") {
            val msg = VyperInternalTypeFactory.of(element.project).msg
           return  msg.children.filter { it is VyperLocalVariableDefinition }.map{it as VyperLocalVariableDefinition}.map{it.localVariableDeclaration}
        }
        if( element.firstChild.firstChild is VyperVarLiteral
                && (element.firstChild.firstChild as VyperVarLiteral).name == "self") {
            return resolveSelfAccessVarLiteral(element, findLastChildByType(VAR_LITERAL,element.node)!!.psi as VyperVarLiteral)

        }

            return emptyList()
    }

    private fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue
            shouldContinue = pred(it)
            result
        }
    }

    //now only for self.var
    //typechecking to be implemented
    fun resolveSelfAccessVarLiteral(element: VyperMemberAccessExpression, id: VyperVarLiteral): List<VyperNamedElement> {
//
        return resolveSelfAccessVarLiteralRec(element)
                .filter {it.name == id.name}

    }
//
    fun resolveSelfAccessVarLiteralRec(element: VyperMemberAccessExpression): List<VyperNamedElement>{
        return (element.file as VyperFile).getStatements().filter { it is VyperStateVariableDeclaration }.map{it as VyperStateVariableDeclaration}
    }
}

data class FunctionResolveResult(val psiElement: PsiElement, val usingLibrary: Boolean = false)