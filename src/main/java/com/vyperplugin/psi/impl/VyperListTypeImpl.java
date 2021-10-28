// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VyperListTypeImpl extends ASTWrapperPsiElement implements VyperListType {

  public VyperListTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitListType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperStructType getStructType() {
    return findChildByClass(VyperStructType.class);
  }

  @Override
  @Nullable
  public VyperUnitType getUnitType() {
    return findChildByClass(VyperUnitType.class);
  }

  @Override
  @Nullable
  public VyperValueType getValueType() {
    return findChildByClass(VyperValueType.class);
  }

}
