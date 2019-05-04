// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperFunctionDefinition extends PsiElement {

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
