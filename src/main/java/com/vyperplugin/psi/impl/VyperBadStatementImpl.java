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

public class VyperBadStatementImpl extends ASTWrapperPsiElement implements VyperBadStatement {

  public VyperBadStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitBadStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperMultiLineString getMultiLineString() {
    return findChildByClass(VyperMultiLineString.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralDouble() {
    return findChildByType(STRINGLITERALDOUBLE);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralDoubleB() {
    return findChildByType(STRINGLITERALDOUBLEB);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralSingle() {
    return findChildByType(STRINGLITERALSINGLE);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteralSingleB() {
    return findChildByType(STRINGLITERALSINGLEB);
  }

}
