// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperMultiLineString;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import static com.vyperplugin.psi.VyperTypes.MULTILINESTRINGTOKEN;

public class VyperMultiLineStringImpl extends ASTWrapperPsiElement implements VyperMultiLineString {

  public VyperMultiLineStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitMultiLineString(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getMultiLineStringToken() {
    return findNotNullChildByType(MULTILINESTRINGTOKEN);
  }

}
