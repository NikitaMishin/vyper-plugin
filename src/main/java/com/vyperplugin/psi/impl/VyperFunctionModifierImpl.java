// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperFunctionModifier;
import com.vyperplugin.psi.VyperUniqueKey;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VyperFunctionModifierImpl extends ASTWrapperPsiElement implements VyperFunctionModifier {

  public VyperFunctionModifierImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitFunctionModifier(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperUniqueKey getUniqueKey() {
    return findChildByClass(VyperUniqueKey.class);
  }

}
