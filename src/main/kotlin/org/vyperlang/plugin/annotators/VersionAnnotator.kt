package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.findParentOfType
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.references.VyperResolver

private val Vyper4 = SemVer.parseFromText("0.4.0")
private val ExtCallModifiers = listOf("payable", "nonpayable")
private val StaticCallModifiers = listOf("pure", "view")

class VersionAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element.containingFile.fileType) {
            is VyperFileType ->
                element.file.vyperVersion?.let {
                    when {
                        it >= Vyper4 -> highlightVyper4(element, holder)
                        it < Vyper4 -> highlightVyper3(element, holder)
                    }
                }

        }
    }

    private fun highlightVyper3(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is VyperExtCallExpression ->
                holder.newAnnotation(HighlightSeverity.ERROR, "Keyword `extcall` not supported in Vyper 0.3").create()
            is VyperStaticCallExpression ->
                holder.newAnnotation(HighlightSeverity.ERROR, "Keyword `staticcall` not supported in Vyper 0.3").create()
        }
    }

    private fun highlightVyper4(element: PsiElement, holder: AnnotationHolder) {
        when(element) {
            is VyperFunctionEntrancyKey ->
                holder.newAnnotation(HighlightSeverity.ERROR, "Named locks are not supported in Vyper 0.4").create()
            is VyperMemberAccessExpression -> {
                val modifiers = VyperResolver.resolveInterfaceFunctionModifiers(element)
                when {
                    modifiers.any { it in StaticCallModifiers } && element.findParentOfType<VyperStaticCallExpression>() == null ->
                        holder.newAnnotation(HighlightSeverity.ERROR, "Missing `staticcall`").create()
                    modifiers.any { it in ExtCallModifiers } && element.findParentOfType<VyperExtCallExpression>() == null ->
                        holder.newAnnotation(HighlightSeverity.ERROR, "Missing `extcall`").create()
                }
            }
        }
    }
}
