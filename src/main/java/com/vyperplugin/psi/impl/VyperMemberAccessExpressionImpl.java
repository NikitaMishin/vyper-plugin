// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperExpression;
import com.vyperplugin.psi.VyperMemberAccessExpression;
import com.vyperplugin.psi.VyperVarLiteral;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

public class VyperMemberAccessExpressionImpl extends VyperExpressionImpl implements VyperMemberAccessExpression {

  public VyperMemberAccessExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitMemberAccessExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public VyperExpression getExpression() {
    return findNotNullChildByClass(VyperExpression.class);
  }

  @Override
  @NotNull
  public VyperVarLiteral getVarLiteral() {
    return findNotNullChildByClass(VyperVarLiteral.class);
  }

}
