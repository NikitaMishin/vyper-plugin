package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperFunctionDefinition
import org.vyperlang.plugin.psi.VyperImportDirective
import org.vyperlang.plugin.psi.VyperStateVariableDeclaration
import org.vyperlang.plugin.psi.file

// todo: Events must be declared before global declarations and function definitions.

class VyperDeclarationsOrderAnnotator : Annotator {

    // todo: val variableTypes = listOf(VyperStateVariableDeclaration::class, VyperConstantDefinitionExpression::class, VyperImmutableDefinitionExpression::class)

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperStateVariableDeclaration && findBefore<VyperFunctionDefinition>(element) != null) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Global variables must all come before function definitions"
            ).create()
        }
        if (element is VyperImportDirective && findBefore<VyperStateVariableDeclaration>(element) != null) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Imports must come before global variables"
            ).create()
        }
    }

    private inline fun <reified T> findBefore(element: PsiElement) = element.file
        .elements
        .takeWhile { it != element }
        .filterIsInstance<T>()
        .firstOrNull()
}
