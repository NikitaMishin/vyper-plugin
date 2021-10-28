// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.vyperplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.vyperplugin.psi.VyperTypes.IDENTIFIER;

public class VyperFunctionDefinitionImpl extends VyperFunctionDefMixin implements VyperFunctionDefinition {

  public VyperFunctionDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitFunctionDefinition(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperFunctionArgs getFunctionArgs() {
    return findChildByClass(VyperFunctionArgs.class);
  }

  @Override
  @NotNull
  public List<VyperFunctionModifier> getFunctionModifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperFunctionModifier.class);
  }

  @Override
  @Nullable
  public VyperStatement getStatement() {
    return findChildByClass(VyperStatement.class);
  }

  @Override
  @Nullable
  public VyperType getType() {
    return findChildByClass(VyperType.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
