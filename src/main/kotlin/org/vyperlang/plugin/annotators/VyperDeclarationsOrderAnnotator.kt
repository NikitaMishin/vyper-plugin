package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperFunctionDefinition
import org.vyperlang.plugin.psi.VyperImportDirective
import org.vyperlang.plugin.psi.VyperStateVariableDeclaration
import org.vyperlang.plugin.psi.file

//Events must be declared before global declarations and function definitions.

//Global variables must all come before function definitions

class VyperDeclarationsOrderAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperStateVariableDeclaration && findBefore<VyperFunctionDefinition>(element) != null) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Global variables must all come before function definitions"
            )
        }
        if (element is VyperImportDirective && findBefore<VyperStateVariableDeclaration>(element) != null) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Imports must come before global variables"
            )
        }
    }

    private inline fun <reified T> findBefore(element: PsiElement) = element.file
        .elements
        .takeWhile { it != element }
        .filterIsInstance<T>()
        .firstOrNull()
}
