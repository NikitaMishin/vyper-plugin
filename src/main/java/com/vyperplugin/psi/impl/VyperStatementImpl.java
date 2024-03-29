// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.vyperplugin.psi.VyperTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.vyperplugin.psi.*;

public class VyperStatementImpl extends ASTWrapperPsiElement implements VyperStatement {

  public VyperStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperBadStatement getBadStatement() {
    return findChildByClass(VyperBadStatement.class);
  }

  @Override
  @Nullable
  public VyperEmitStatement getEmitStatement() {
    return findChildByClass(VyperEmitStatement.class);
  }

  @Override
  @Nullable
  public VyperExpression getExpression() {
    return findChildByClass(VyperExpression.class);
  }

  @Override
  @Nullable
  public VyperForStatement getForStatement() {
    return findChildByClass(VyperForStatement.class);
  }

  @Override
  @Nullable
  public VyperIfStatement getIfStatement() {
    return findChildByClass(VyperIfStatement.class);
  }

  @Override
  @Nullable
  public VyperLocalVariableDefinition getLocalVariableDefinition() {
    return findChildByClass(VyperLocalVariableDefinition.class);
  }

}
