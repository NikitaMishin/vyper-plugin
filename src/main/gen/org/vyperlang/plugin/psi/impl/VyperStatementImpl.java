// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.vyperlang.plugin.psi.VyperTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.vyperlang.plugin.psi.*;

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
  public VyperEmitStatement getEmitStatement() {
    return findChildByClass(VyperEmitStatement.class);
  }

  @Override
  @NotNull
  public List<VyperExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperExpression.class);
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

  @Override
  @Nullable
  public VyperMultiLineString getMultiLineString() {
    return findChildByClass(VyperMultiLineString.class);
  }

}
