package org.vyperlang.plugin.references

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.isAncestor
import com.intellij.psi.util.parentOfType
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.psi.VyperTypes.VAR_LITERAL

object VyperResolver {
    /**
     * Resolves a variable literal to its declaration.
     */
    fun resolveVarLiteral(element: VyperNamedElement): List<PsiElement> =
        lexicalDeclarations(element).filter { it.name == element.name }.toList()

    /**
     * Finds all the lexical declarations applicable to the given place.
     * @param place The element that is being resolved.
     * @param stop A function that stops the search when it returns true. Default is to search the whole file.
     */
    fun lexicalDeclarations(place: PsiElement, stop: (PsiElement) -> Boolean = { false }): List<VyperNamedElement> =
        place.ancestors
            .drop(1) // current element might not be a VyperElement
            .takeWhile { (it is VyperElement || it is VyperFile) && !stop(it) }
            .flatMap { lexicalDeclarations(it, place) }
            .filter { !it.isAncestor(place) } // don't suggest the element being defined
            .distinct()
            .toList()

    /**
     * Resolves a struct to its declaration.
     * @param element The variable literal that is referencing a struct.
     */
    fun resolveStruct(element: VyperVarLiteral): PsiElement? = findStruct(element.file, element.name)

    /**
     * Resolves a struct member to its declaration.
     * @param element The variable literal that is a member of a struct.
     */
    fun resolveStructMember(element: VyperVarLiteral): PsiElement? =
        // find the definition of the struct
        findStruct(element.file, element.parentOfType<VyperStructExpression>()?.varLiteral?.identifier?.text)
            // then find the member in it
            ?.childrenOfType<VyperLocalVariableDefinition>()
            ?.firstOrNull { it.identifier.text == element.text }

    private fun findStruct(file: PsiFile, structName: String?) =
        file.childrenOfType<VyperStructDefinition>()
            .firstOrNull { it.name == structName }

    fun resolveEventLog(element: VyperVarLiteral): Collection<PsiElement> =
        element.file.childrenOfType<VyperEventDeclaration>()
            .filter { it.identifier.text == element.text }

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

    private fun lexicalDeclarations(scope: PsiElement, place: PsiElement): List<VyperNamedElement> = when (scope) {
        is VyperLocalVariableDefinition -> listOf(scope)
        is VyperImportDirective -> listOf(scope)
        is VyperStructDefinition -> listOf(scope)
        is VyperConstantDefinitionExpression -> listOf(scope)
        is VyperImmutableDefinitionExpression -> listOf(scope)

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

    // todo: this needs to be rewritten. Checking first child is wrong. We need to iterate in all the children
    // all expressions and arguments: CallExpression = Expression ('[' Expression ']')* FunctionCallArguments FunctionCallArguments*
    fun resolveCall(element: VyperCallExpression): Collection<VyperNamedElement> = when (element.firstChild) {
        is VyperMemberAccessExpression -> resolveMemberAccess(element.firstChild as VyperMemberAccessExpression)

        else -> emptyList()
    }

    fun resolveMemberAccess(element: VyperMemberAccessExpression): List<VyperNamedElement> = when {
        element.getFirstLiteral().name == "msg" ->
            VyperInternalTypeFactory(element.project).msg.children.filterIsInstance<VyperLocalVariableDefinition>()

        element.firstChild.firstChild is VyperVarLiteral
                && (element.firstChild.firstChild as VyperVarLiteral).name == "self"
        -> getSelfLiteral(
            element,
            findLastChildByType(VAR_LITERAL, element.node)!!.psi as VyperVarLiteral
        )

        else -> emptyList()
    }

    fun getSelfLiteral(element: VyperMemberAccessExpression, id: VyperVarLiteral) =
        getSelfElements(element)
            .filter { it.name == id.name }

    fun getSelfElements(element: VyperMemberAccessExpression) = (element.file as VyperFile).selfElements
}

fun VyperMemberAccessExpression.getFirstLiteral() = this.firstChild.firstChild as VyperVarLiteral
