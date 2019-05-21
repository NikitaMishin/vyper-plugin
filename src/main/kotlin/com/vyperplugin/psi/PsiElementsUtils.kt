package com.vyperplugin.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil

val PsiElement.ancestors: Sequence<PsiElement> get() = generateSequence(this) { it.parent }
val PsiElement.siblings: Sequence<PsiElement> get() = generateSequence(this) { it.nextSibling }

val PsiElement.file get() = this.containingFile

fun PsiElement.rangeRelativeTo(ancestor: PsiElement): TextRange {
    check(ancestor.textRange.contains(textRange))
    return textRange.shiftRight(-ancestor.textRange.startOffset)
}

val PsiElement.parentRelativeRange: TextRange
    get() = rangeRelativeTo(parent)

inline fun <reified T : PsiElement> PsiElement.childOfType(strict: Boolean = true): T? =
        PsiTreeUtil.findChildOfType(this, T::class.java, strict)

fun  findLastChildByType(type: IElementType, node : ASTNode): ASTNode? {
    var child = node.lastChildNode
    while (child != null) {
        var node_ = child
        if (node_ != null && node_.elementType == type) return child
        child = child.treePrev
    }
    return null
}