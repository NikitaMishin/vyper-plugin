// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperVarLiteral;
import com.vyperplugin.psi.VyperVarLiteralMixin;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import static com.vyperplugin.psi.VyperTypes.IDENTIFIER;

public class VyperVarLiteralImpl extends VyperVarLiteralMixin implements VyperVarLiteral {

  public VyperVarLiteralImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitVarLiteral(this);
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
