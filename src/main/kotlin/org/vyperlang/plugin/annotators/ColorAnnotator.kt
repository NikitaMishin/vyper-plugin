package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.VyperInterfaceFileType
import org.vyperlang.plugin.psi.*


/**
 * Annotator that highlights Vyper elements with different colors
 */
class VyperColorAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val fileType = element.containingFile.fileType

        if (fileType is VyperFileType) {
            highlightVyperFile(element, holder)
        }
        else if (fileType is VyperInterfaceFileType) {
            highlightInterfaceFile(element, holder)
        }
    }

    private fun highlightVyperFile(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperVarLiteral && element.text == "self") {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .textAttributes(VyperColor.KEYWORD.textAttributesKey).create()
        }

        if (element is VyperFunctionBody && element.text == "...") {
            addError(holder, "Ellipsis is only allowed in `.vyi` files")
        }
    }

    private fun highlightInterfaceFile(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperStatement) {
            addError(holder, "Statements forbidden in `.vyi` files")
        }
        if (element is VyperStateVariableDeclaration) {
            addError(holder, "State variables forbidden in `.vyi` files")
        }
        if (element is VyperFunctionDecorator && element.text == "internal") {
            addError(holder, "Internal methods forbidden in `.vyi` files")
        }
    }

    private fun addError(holder: AnnotationHolder, error: String) {
        holder.newAnnotation(HighlightSeverity.ERROR, error).create();
    }
}