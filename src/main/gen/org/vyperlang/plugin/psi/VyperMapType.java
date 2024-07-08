// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperMapType extends VyperElement {

  @NotNull
  VyperType getType();

  @Nullable
  VyperValueType getValueType();

  @Nullable
  VyperVarLiteral getVarLiteral();

  @Nullable
  PsiElement getDecimalNumber();

  @Nullable
  PsiElement getFixedNumber();

  @Nullable
  PsiElement getHexNumber();

}
