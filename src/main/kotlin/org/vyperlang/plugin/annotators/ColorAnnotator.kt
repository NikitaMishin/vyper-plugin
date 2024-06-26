package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperElement
import org.vyperlang.plugin.psi.VyperVarLiteral


class VyperColorAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperElement) {
            val highlight = highlight(element)
            if (highlight != null) {
                val (partToHighlight, color) = highlight
                holder.newAnnotation(HighlightSeverity.INFORMATION, "").range(partToHighlight)
                    .textAttributes(color.textAttributesKey).create()
            }
        }
    }


    private fun highlight(element: VyperElement): Pair<PsiElement, VyperColor>? {
        return if (element is org.vyperlang.plugin.psi.VyperVarLiteral && element.text == "self") {
            element to VyperColor.KEYWORD
        } else null
    }
}