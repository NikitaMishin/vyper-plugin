// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface VyperStateVariableDeclaration extends VyperNamedElement {

  @Nullable
  VyperStateVariableModifier getStateVariableModifier();

  @NotNull
  VyperType getType();

  @NotNull
  PsiElement getIdentifier();

}
