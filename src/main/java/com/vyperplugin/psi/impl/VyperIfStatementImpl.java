// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.vyperplugin.psi.VyperExpression;
import com.vyperplugin.psi.VyperIfStatement;
import com.vyperplugin.psi.VyperStatement;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VyperIfStatementImpl extends ASTWrapperPsiElement implements VyperIfStatement {

  public VyperIfStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitIfStatement(this);
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

  @Override
  @NotNull
  public List<VyperStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperStatement.class);
  }

}
