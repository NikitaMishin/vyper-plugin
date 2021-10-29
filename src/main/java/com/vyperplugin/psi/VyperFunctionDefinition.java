// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface VyperFunctionDefinition extends VyperFunctionDefElement {

  @Nullable
  VyperFunctionArgs getFunctionArgs();

  @NotNull
  List<VyperFunctionModifier> getFunctionModifierList();

  @Nullable
  VyperStatement getStatement();

  @Nullable
  VyperType getType();

  @Nullable
  PsiElement getIdentifier();

}
