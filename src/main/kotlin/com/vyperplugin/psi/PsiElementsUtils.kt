package com.vyperplugin.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil

val PsiElement.ancestors: Sequence<PsiElement> get() = generateSequence(this) { it.parent }

val PsiElement.file get() = this.containingFile

fun PsiElement.rangeRelativeTo(ancestor: PsiElement): TextRange {
    check(ancestor.textRange.contains(textRange))
    return textRange.shiftRight(-ancestor.textRange.startOffset)
}

val PsiElement.parentRelativeRange: TextRange
    get() = rangeRelativeTo(parent)

inline fun <reified T : PsiElement> PsiElement.childOfType(strict: Boolean = true): T? =
        PsiTreeUtil.findChildOfType(this, T::class.java, strict)