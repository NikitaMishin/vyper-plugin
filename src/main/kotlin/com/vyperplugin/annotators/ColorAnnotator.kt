package com.vyperplugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.vyperplugin.psi.VyperElement
import com.vyperplugin.psi.VyperVarLiteral


class VyperColorAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is VyperElement) {
            val highlight = highlight(element)
            if (highlight != null) {
                val (partToHighlight, color) = highlight
                holder.createInfoAnnotation(partToHighlight, null).textAttributes = color.textAttributesKey
            }
        }
    }


    private fun highlight(element: VyperElement): Pair<PsiElement, VyperColor>? {
        return if (element is VyperVarLiteral && element.text == "self") {
            element to VyperColor.KEYWORD
        } else null
    }
}