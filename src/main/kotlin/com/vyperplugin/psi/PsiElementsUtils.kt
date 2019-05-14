package com.vyperplugin.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

val PsiElement.ancestors: Sequence<PsiElement> get() = generateSequence(this) { it.parent }

val PsiElement.file get() = this.containingFile