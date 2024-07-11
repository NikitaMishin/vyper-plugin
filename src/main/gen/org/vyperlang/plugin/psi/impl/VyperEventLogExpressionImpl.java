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

public class VyperEventLogExpressionImpl extends VyperExpressionImpl implements VyperEventLogExpression {

  public VyperEventLogExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitEventLogExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public VyperFunctionCallArguments getFunctionCallArguments() {
    return findNotNullChildByClass(VyperFunctionCallArguments.class);
  }

  @Override
  @NotNull
  public VyperVarLiteral getVarLiteral() {
    return findNotNullChildByClass(VyperVarLiteral.class);
  }

}
