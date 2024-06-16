// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperRangeExpression extends VyperExpression {

  @Nullable
  VyperExpression getExpression();

  @Nullable
  PsiElement getDecimalNumber();

  @Nullable
  PsiElement getFixedNumber();

  @Nullable
  PsiElement getHexNumber();

}
