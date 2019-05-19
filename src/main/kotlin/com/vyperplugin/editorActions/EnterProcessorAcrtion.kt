package com.vyperplugin.editorActions

import com.intellij.lang.LanguageExtension

import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement;
import com.intellij.lang.PairedBraceMatcher
import com.intellij.openapi.compiler.CompilationStatusListener

class Listener() : CompilationStatusListener