// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.vyperplugin.psi.VyperStateVariableModifier;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

public class VyperStateVariableModifierImpl extends ASTWrapperPsiElement implements VyperStateVariableModifier {

  public VyperStateVariableModifierImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStateVariableModifier(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

}
