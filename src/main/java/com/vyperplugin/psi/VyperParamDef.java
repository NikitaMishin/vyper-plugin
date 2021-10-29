// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface VyperParamDef extends VyperNamedElement {

  @Nullable
  VyperExpression getExpression();

  @NotNull
  VyperType getType();

  @NotNull
  PsiElement getIdentifier();

}
