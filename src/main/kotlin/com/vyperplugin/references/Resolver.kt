package com.vyperplugin.references

import com.intellij.psi.PsiElement
import com.intellij.util.containers.toMutableSmartList
import com.vyperplugin.psi.*
import com.vyperplugin.psi.VyperTypes.VAR_LITERAL


object VyperResolver {
    fun resolveVarLiteral(element: VyperNamedElement): List<PsiElement> {
        return lexicalDeclarations(element).filter { it.name == element.name }.toList()
    }

    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }): List<VyperNamedElement> {

        return lexicalDeclRec(place, stop).distinct()
    }

//    private fun getImportDecls(element: PsiElement): List<PsiElement> {
//        val newList = element.file.children.filterIsInstance<VyperImportPath>().map { it ->
//            val localPath = it.text
////            val absPath = place.file.project.basePath + localPath.replace('.', '/') + ".vy"
//            val kek = FilenameIndex.getVirtualFilesByName(localPath.drop(localPath.indexOfLast { it == '.' } + 1) + ".vy", GlobalSearchScope.projectScope(it.project)).first()
//            val psiFile = PsiManager.getInstance(it.project).findFile(kek)
//            psiFile!!.children.filter { a -> a is VyperUserDefinedConstantsExpression || a is VyperFunctionDefinition  }
//        }.reduce { acc, psiElements -> acc.plus(psiElements) }
//        return newList
//    }

    private fun lexicalDeclRec(place: PsiElement, stop: (PsiElement) -> Boolean): List<VyperNamedElement> {
        val parents = place.ancestors
            .drop(1) // current element might not be a VyperElement
            .toList()

        return parents
            .takeWhile { (it is VyperElement || it is VyperFile) && !stop(it) }
            .flatMap { lexicalDeclarations(it, place) }


    }

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> {
        return when (scope) {

            is VyperLocalVariableDeclaration -> listOf(scope)

            is VyperStatement -> {
                val temp = mutableListOf<PsiElement>().apply {
                    var start = scope
                    while (start.prevSibling != null) {
                        this.add(start.prevSibling)
                        start = start.prevSibling
                    }
                }
                temp.filterIsInstance<VyperStatement>().toList()
                    .fold(emptyList()) { acc, elem ->
                        acc.plus(
                            lexicalDeclarations(elem.firstChild, place)
                        )
                    }
            }

            is VyperFile -> scope.getStatements().filter { it !is VyperFunctionDefinition }
                .flatMap { lexicalDeclarations(it, place) }

            is VyperUserDefinedConstantsExpression -> listOf(scope)

            is VyperLocalVariableDefinition -> lexicalDeclarations(scope.localVariableDeclaration, place)

            is VyperFunctionDefinition -> {
                val params = scope.parameters?.paramDefList?.toTypedArray() ?: emptyArray()
                val statements: MutableList<VyperNamedElement> = scope.statement
                    ?.siblings
                    ?.toMutableList()
                    ?.flatMap { lexicalDeclarations(it, place) }?.toMutableSmartList() ?: mutableListOf()
                statements.addAll(params)
                statements

            }
            else -> emptyList()
        }
    }


    fun resolveFunction(element: VyperCallExpression): Collection<FunctionResolveResult> {
        return resolveFunRec(element).filter { it.name == element.referenceName }
            .map { FunctionResolveResult(it) }
    }

    private fun resolveFunRec(element: VyperCallExpression): List<VyperFunctionDefinition> {
        val res = mutableListOf<VyperFunctionDefinition>()
        val ref: VyperElement = element
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
        if (element.firstChild.firstChild is VyperVarLiteral
            && (element.firstChild.firstChild as VyperVarLiteral).name == "msg"
        ) {
            val msg = VyperInternalTypeFactory.of(element.project).msg
            return msg.children.filter { it is VyperLocalVariableDefinition }.map { it as VyperLocalVariableDefinition }
                .map { it.localVariableDeclaration }
        }
        if (element.firstChild.firstChild is VyperVarLiteral
            && (element.firstChild.firstChild as VyperVarLiteral).name == "self"
        ) {
            return resolveSelfAccessVarLiteral(
                element,
                findLastChildByType(VAR_LITERAL, element.node)!!.psi as VyperVarLiteral
            )

        }

        return emptyList()
    }

    //now only for self.var
    //typechecking to be implemented
    fun resolveSelfAccessVarLiteral(
        element: VyperMemberAccessExpression,
        id: VyperVarLiteral
    ): List<VyperNamedElement> {
//
        return resolveSelfAccessVarLiteralRec(element).plus(resolveSelfAccessFunction(element))
            .filter { it.name == id.name }

    }

    //
    fun resolveSelfAccessFunction(element: VyperMemberAccessExpression): List<VyperNamedElement> {
        return (element.file as VyperFile).getStatements().filter { it is VyperFunctionDefinition }
            .map { it as VyperNamedElement }
    }

    fun resolveSelfAccessVarLiteralRec(element: VyperMemberAccessExpression): List<VyperNamedElement> {
        return (element.file as VyperFile).getStatements().filter { it is VyperStateVariableDeclaration }
            .map { it as VyperNamedElement }
    }
}

data class FunctionResolveResult(val psiElement: PsiElement, val usingLibrary: Boolean = false)