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
class ColorAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element.containingFile.fileType) {
            is VyperFileType -> highlightVyperFile(element, holder)
            is VyperInterfaceFileType -> highlightInterfaceFile(element, holder)
        }
    }

    private fun highlightVyperFile(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is VyperVarLiteral ->
                if (element.name == "self") {
                    holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .textAttributes(VyperColor.KEYWORD.textAttributesKey).create()
                }
            is VyperFunctionBody ->
                if (element.text == "...") {
                    addError(holder, "Ellipsis is only allowed in `.vyi` files")
                }
        }
    }

    private fun highlightInterfaceFile(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is VyperStatement -> if (element.text != "return ...") {
                addError(holder, "Statements forbidden in `.vyi` files")
            }
            is VyperStateVariableDeclaration -> addError(holder, "State variables forbidden in `.vyi` files")
            is VyperFunctionDecorator -> if (element.text == "internal") {
                addError(holder, "Internal methods forbidden in `.vyi` files")
            }
        }
    }

    private fun addError(holder: AnnotationHolder, error: String) {
        holder.newAnnotation(HighlightSeverity.ERROR, error).create()
    }
}
