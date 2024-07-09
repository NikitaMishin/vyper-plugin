// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperPrimaryExpression extends VyperExpression {

  @Nullable
  VyperMultiLineString getMultiLineString();

  @Nullable
  VyperType getType();

  @Nullable
  VyperVarLiteral getVarLiteral();

  @Nullable
  PsiElement getBooleanLiteral();

  @Nullable
  PsiElement getDecimalNumber();

  @Nullable
  PsiElement getFixedNumber();

  @Nullable
  PsiElement getHexLiteral();

  @Nullable
  PsiElement getHexNumber();

  @Nullable
  PsiElement getStringLiteralDouble();

  @Nullable
  PsiElement getStringLiteralDoubleB();

  @Nullable
  PsiElement getStringLiteralSingle();

  @Nullable
  PsiElement getStringLiteralSingleB();

}
