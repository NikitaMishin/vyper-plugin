// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperStructType;
import com.vyperplugin.psi.VyperStructTypeMixin;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import static com.vyperplugin.psi.VyperTypes.IDENTIFIER;

public class VyperStructTypeImpl extends VyperStructTypeMixin implements VyperStructType {

  public VyperStructTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStructType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
