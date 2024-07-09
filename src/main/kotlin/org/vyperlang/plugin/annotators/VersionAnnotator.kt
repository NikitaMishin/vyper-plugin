package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.findParentOfType
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.psi.*
import org.vyperlang.plugin.references.VyperResolver

private val ExtCallModifiers = listOf("payable", "nonpayable")
private val StaticCallModifiers = listOf("pure", "view")

internal const val EXTCALL_NOT_VY3 = "Keyword `extcall` not supported in Vyper 0.3"
internal const val STATICCALL_NOT_VY3 = "Keyword `staticcall` not supported in Vyper 0.3"
internal const val STRUCT_DICT_NOT_VY3 =
    "Instantiating a struct using a dictionary is not supported until Vyper 0.4. Use kwargs instead e.g. Foo(a=1, b=2)"
internal const val RANGE_TYPE_NOT_V3 = "Range type not supported in Vyper 0.3"
internal const val FLAGS_NOT_V3 = "`flag` is not supported in Vyper 0.3. Please use `enum` keyword"
internal const val RANGE_BOUND_NOT_V3 = "Range bound not supported in Vyper 0.3"

internal const val STRUCT_DICT_WARN_V4 = "Instantiating a struct using a dictionary is deprecated. Use kwargs instead e.g. Foo(a=1, b=2)"
internal const val NAMED_LOCKS_NOT_V4 = "Named locks are not supported in Vyper 0.4"
internal const val MISSING_STATICCALL = "Missing `staticcall`"
internal const val MISSING_EXTCALL = "Missing `extcall`"
internal const val RANGE_TYPE_REQUIRED_V4 = "Range type required in Vyper 0.4"
internal const val ENUM_NOT_V4 = "`enum` is not supported in Vyper 0.4. Please use flags instead"

internal const val VYPER_VERSION_NOT_SPECIFIED = "Vyper version not specified. Please add `# pragma version ^0.4.0` to the top of the file"

class VersionAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val fileType = element.containingFile.fileType
        if (fileType is VyperFileType)
            element.file.vyperVersion?.let {
                when ("${it.major}.${it.minor}") {
                    "0.4" -> highlightVyper4(element, holder)
                    "0.3" -> highlightVyper3(element, holder)
                }
            }

        val firstChild = element.containingFile.firstChild // show warning on the first line when missing pragma
        if (fileType is VyperFileType && element == firstChild && element.file.vyperVersion == null) {
            holder.newAnnotation(HighlightSeverity.WARNING, VYPER_VERSION_NOT_SPECIFIED)
                .withFix(AddVersionPragmaFix("^0.3.0"))
                .withFix(AddVersionPragmaFix("^0.4.0"))
                .create()
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
                    holder.newAnnotation(HighlightSeverity.ERROR, RANGE_TYPE_NOT_V3)
                        .range(element.firstChild).create()
            is VyperFlagDeclaration ->
                if (element.firstChild.text == "flag")
                    holder.newAnnotation(HighlightSeverity.ERROR, FLAGS_NOT_V3).create()
            is VyperRangeBound ->
                holder.newAnnotation(HighlightSeverity.ERROR, RANGE_BOUND_NOT_V3).create()
        }
    }

    private fun highlightVyper4(element: PsiElement, holder: AnnotationHolder) {
        when(element) {
            is VyperFunctionEntrancyKey ->
                holder.newAnnotation(HighlightSeverity.ERROR, NAMED_LOCKS_NOT_V4).create()
            is VyperMemberAccessExpression ->
                highlightVyper4Modifiers(VyperResolver.resolveInterfaceFunctionModifiers(element), element, holder)
            is VyperStructExpression ->
                holder.newAnnotation(HighlightSeverity.WARNING, STRUCT_DICT_WARN_V4).create()
            is VyperForStatement ->
                if(element.type == null)
                    holder.newAnnotation(HighlightSeverity.ERROR, RANGE_TYPE_REQUIRED_V4)
                        .range(element.firstChild).create()
            is VyperFlagDeclaration ->
                if (element.firstChild.text == "enum")
                    holder.newAnnotation(HighlightSeverity.ERROR, ENUM_NOT_V4).create()
        }
    }

    private fun highlightVyper4Modifiers(modifiers: List<String>, element: PsiElement, holder: AnnotationHolder) {
        when {
            modifiers.any { it in StaticCallModifiers } && element.findParentOfType<VyperStaticCallExpression>() == null ->
                holder.newAnnotation(HighlightSeverity.ERROR, MISSING_STATICCALL).create()

            modifiers.any { it in ExtCallModifiers } && element.findParentOfType<VyperExtCallExpression>() == null ->
                holder.newAnnotation(HighlightSeverity.ERROR, MISSING_EXTCALL).create()
        }
    }
}
