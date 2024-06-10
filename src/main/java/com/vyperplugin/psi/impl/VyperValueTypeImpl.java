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

public class VyperValueTypeImpl extends ASTWrapperPsiElement implements VyperValueType {

  public VyperValueTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitValueType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getBytesM() {
    return findChildByType(BYTESM);
  }

  @Override
  @Nullable
  public PsiElement getDecimalNumber() {
    return findChildByType(DECIMALNUMBER);
  }

  @Override
  @Nullable
  public PsiElement getIntM() {
    return findChildByType(INTM);
  }

  @Override
  @Nullable
  public PsiElement getUintM() {
    return findChildByType(UINTM);
  }

}
