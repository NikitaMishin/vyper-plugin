// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperAssertExpression;
import com.vyperplugin.psi.VyperExpression;
import com.vyperplugin.psi.VyperMultiLineString;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.vyperplugin.psi.VyperTypes.STRINGLITERALDOUBLE;
import static com.vyperplugin.psi.VyperTypes.STRINGLITERALSINGLE;

public class VyperAssertExpressionImpl extends VyperExpressionImpl implements VyperAssertExpression {

  public VyperAssertExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitAssertExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperExpression getExpression() {
    return findChildByClass(VyperExpression.class);
  }

  @Override
  @Nullable
  public VyperMultiLineString getMultiLineString() {
    return findChildByClass(VyperMultiLineString.class);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralDouble() {
    return findChildByType(STRINGLITERALDOUBLE);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralSingle() {
    return findChildByType(STRINGLITERALSINGLE);
  }

}
