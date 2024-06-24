// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.vyperlang.plugin.psi.VyperTypes.*;
import org.vyperlang.plugin.psi.*;

public class VyperPrimaryExpressionImpl extends VyperExpressionImpl implements VyperPrimaryExpression {

  public VyperPrimaryExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitPrimaryExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperMultiLineString getMultiLineString() {
    return findChildByClass(VyperMultiLineString.class);
  }

  @Override
  @Nullable
  public VyperType getType() {
    return findChildByClass(VyperType.class);
  }

  @Override
  @Nullable
  public VyperVarLiteral getVarLiteral() {
    return findChildByClass(VyperVarLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getBooleanLiteral() {
    return findChildByType(BOOLEANLITERAL);
  }

  @Override
  @Nullable
  public PsiElement getDecimalNumber() {
    return findChildByType(DECIMALNUMBER);
  }

  @Override
  @Nullable
  public PsiElement getFixedNumber() {
    return findChildByType(FIXEDNUMBER);
  }

  @Override
  @Nullable
  public PsiElement getHexLiteral() {
    return findChildByType(HEXLITERAL);
  }

  @Override
  @Nullable
  public PsiElement getHexNumber() {
    return findChildByType(HEXNUMBER);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralDouble() {
    return findChildByType(STRINGLITERALDOUBLE);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralDoubleB() {
    return findChildByType(STRINGLITERALDOUBLEB);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralSingle() {
    return findChildByType(STRINGLITERALSINGLE);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralSingleB() {
    return findChildByType(STRINGLITERALSINGLEB);
  }

}
