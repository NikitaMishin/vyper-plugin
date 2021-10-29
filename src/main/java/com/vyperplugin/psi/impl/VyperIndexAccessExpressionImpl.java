// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.vyperplugin.psi.VyperExpression;
import com.vyperplugin.psi.VyperIndexAccessExpression;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VyperIndexAccessExpressionImpl extends VyperExpressionImpl implements VyperIndexAccessExpression {

  public VyperIndexAccessExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitIndexAccessExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VyperExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperExpression.class);
  }

}
