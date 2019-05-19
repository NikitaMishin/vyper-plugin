// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.vyperplugin.psi.VyperTypes.*;
import com.vyperplugin.psi.VyperNamedElementImpl;
import com.vyperplugin.psi.*;

public class VyperStateVariableDeclarationImpl extends VyperNamedElementImpl implements VyperStateVariableDeclaration {

  public VyperStateVariableDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStateVariableDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperStateVariableModifier getStateVariableModifier() {
    return findChildByClass(VyperStateVariableModifier.class);
  }

  @Override
  @NotNull
  public VyperType getType() {
    return findNotNullChildByClass(VyperType.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
