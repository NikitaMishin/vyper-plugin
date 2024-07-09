package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.findParentOfType
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.VyperInterfaceFileType
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.references.VyperResolver

private val Vyper4 = SemVer.parseFromText("0.4.0")
private val ExtCallModifiers = listOf("payable", "nonpayable")
private val StaticCallModifiers = listOf("pure", "view")

internal const val EXTCALL_NOT_VY3 = "Keyword `extcall` not supported in Vyper 0.3"
internal const val STATICCALL_NOT_VY3 = "Keyword `staticcall` not supported in Vyper 0.3"
internal const val STRUCT_DICT_NOT_VY3 =
    "Instantiating a struct using a dictionary is not supported until Vyper 0.4. Use kwargs instead e.g. Foo(a=1, b=2)"
internal const val RANGE_TYPE_NOT_V3 = "Range type not supported in Vyper 0.3"

internal const val STRUCT_DICT_WARN_V4 = "Instantiating a struct using a dictionary is deprecated. Use kwargs instead e.g. Foo(a=1, b=2)"
internal const val NAMED_LOCKS_NOT_V4 = "Named locks are not supported in Vyper 0.4"
internal const val MISSING_STATICCALL = "Missing `staticcall`"
internal const val MISSING_EXTCALL = "Missing `extcall`"
internal const val RANGE_TYPE_REQUIRED_V4 = "Range type required in Vyper 0.4"

internal const val VYPER_VERSION_NOT_SPECIFIED = "Vyper version not specified. Please add `# pragma version ^0.4.0` to the top of the file"

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
        if (element.containingFile.fileType is VyperFileType && element == element.containingFile.firstChild && element.file.vyperVersion == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, VYPER_VERSION_NOT_SPECIFIED).create()
        }
    }

    private fun highlightVyper3(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is VyperExtCallExpression ->
                holder.newAnnotation(HighlightSeverity.ERROR, EXTCALL_NOT_VY3).create()
            is VyperStaticCallExpression ->
                holder.newAnnotation(HighlightSeverity.ERROR, STATICCALL_NOT_VY3).create()
            is VyperCallExpression ->
                if (VyperResolver.resolveStructCall(element).isNotEmpty())
                    holder.newAnnotation(HighlightSeverity.ERROR, STRUCT_DICT_NOT_VY3).create()
            is VyperForStatement ->
                if(element.type != null)
                    holder.newAnnotation(HighlightSeverity.ERROR, RANGE_TYPE_NOT_V3).create()
        }
    }

    private fun highlightVyper4(element: PsiElement, holder: AnnotationHolder) {
        when(element) {
            is VyperFunctionEntrancyKey ->
                holder.newAnnotation(HighlightSeverity.ERROR, NAMED_LOCKS_NOT_V4).create()
            is VyperMemberAccessExpression ->
                highlightVy4Modifiers(VyperResolver.resolveInterfaceFunctionModifiers(element), element, holder)
            is VyperStructExpression -> holder.newAnnotation(HighlightSeverity.WARNING, STRUCT_DICT_WARN_V4).create()
            is VyperForStatement ->
                if(element.type == null)
                    holder.newAnnotation(HighlightSeverity.ERROR, RANGE_TYPE_REQUIRED_V4).create()
        }
    }

    private fun highlightVy4Modifiers(modifiers: List<String>, element: PsiElement, holder: AnnotationHolder) {
        when {
            modifiers.any { it in StaticCallModifiers } && element.findParentOfType<VyperStaticCallExpression>() == null ->
                holder.newAnnotation(HighlightSeverity.ERROR, MISSING_STATICCALL).create()

            modifiers.any { it in ExtCallModifiers } && element.findParentOfType<VyperExtCallExpression>() == null ->
                holder.newAnnotation(HighlightSeverity.ERROR, MISSING_EXTCALL).create()
        }
    }
}
