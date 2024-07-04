package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.util.isAncestor
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.psi.VyperTypes.VAR_LITERAL


object VyperResolver {
    fun resolveVarLiteral(element: VyperNamedElement): List<PsiElement> =
        lexicalDeclarations(element).filter { it.name == element.name }.toList()

    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }): List<VyperNamedElement> =
        lexicalDeclRec(place, stop).distinct()

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

    private fun lexicalDeclRec(place: PsiElement, stop: (PsiElement) -> Boolean): List<VyperNamedElement> =
        place.ancestors
            .drop(1) // current element might not be a VyperElement
            .takeWhile { (it is VyperElement || it is VyperFile) && !stop(it) }
            .flatMap { lexicalDeclarations(it, place) }
            .filter { !it.isAncestor(place) } // don't suggest the element being defined
            .toList()

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> = when (scope) {
        is VyperLocalVariableDefinition -> listOf(scope)
        is VyperConstantDefinitionExpression -> listOf(scope)
        is VyperImmutableDefinitionExpression -> listOf(scope)
        is VyperStatement -> scope.prevSiblings
            .filterIsInstance<VyperStatement>()
            .map { it.childOfType<VyperLocalVariableDefinition>() }
            .filterNotNull()
            .flatMap { lexicalDeclarations(it, place) }
            .toList()

        is VyperFile -> scope.statements
            .filter { it !is VyperFunctionDefinition }
            .flatMap { lexicalDeclarations(it, place) }

        is VyperFunctionDefinition -> scope.parameters?.paramDefList ?: emptyList()
        else -> emptyList()
    }

    fun resolveFunction(element: VyperCallExpression): Collection<FunctionResolveResult> = when {
        element.firstChild is VyperPrimaryExpression -> emptyList()
        element.firstChild is VyperMemberAccessExpression &&
                (element.firstChild.firstChild.firstChild as VyperVarLiteral).name == "self" ->
            (element.file as VyperFile)
                .statements
                .filterIsInstance<VyperFunctionDefinition>()
                .filter { it.name == element.referenceName }
                .map { FunctionResolveResult(it) }

        else -> emptyList()
    }

    fun resolveMemberAccess(element: VyperMemberAccessExpression): List<VyperNamedElement> = when {
        element.firstChild.firstChild is VyperVarLiteral
                && (element.firstChild.firstChild as VyperVarLiteral).name == "msg"
        ->
            VyperInternalTypeFactory(element.project).msg.children.filterIsInstance<VyperLocalVariableDefinition>()

        element.firstChild.firstChild is VyperVarLiteral
                && (element.firstChild.firstChild as VyperVarLiteral).name == "self"
        -> resolveSelfVariableLiteral(
            element,
            findLastChildByType(VAR_LITERAL, element.node)!!.psi as VyperVarLiteral
        )

        else -> emptyList()
    }

    //now only for self.var
    //typechecking to be implemented
    private fun resolveSelfVariableLiteral(
        element: VyperMemberAccessExpression,
        id: VyperVarLiteral
    ): List<VyperNamedElement> =
        resolveSelfVariables(element)
            .plus(resolveSelfAccessFunction(element))
            .filter { it.name == id.name }

    private fun resolveSelfAccessFunction(element: VyperMemberAccessExpression): List<VyperNamedElement> =
        (element.file as VyperFile).statements.filterIsInstance<VyperFunctionDefinition>()

    fun resolveSelfVariables(element: VyperMemberAccessExpression): List<VyperNamedElement> =
        (element.file as VyperFile).statements.filterIsInstance<VyperStateVariableDeclaration>()
}

data class FunctionResolveResult(val psiElement: PsiElement, val usingLibrary: Boolean = false)