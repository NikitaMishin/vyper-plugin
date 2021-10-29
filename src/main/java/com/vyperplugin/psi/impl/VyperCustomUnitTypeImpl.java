// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperCustomUnitType;
import com.vyperplugin.psi.VyperExpression;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

public class VyperCustomUnitTypeImpl extends ASTWrapperPsiElement implements VyperCustomUnitType {

  public VyperCustomUnitTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitCustomUnitType(this);
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

}
