// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperStateVariableDeclaration extends VyperNamedElement {

  @Nullable
  VyperExpression getExpression();

  @NotNull
  VyperStateVariableType getStateVariableType();

  @NotNull
  PsiElement getIdentifier();

}
