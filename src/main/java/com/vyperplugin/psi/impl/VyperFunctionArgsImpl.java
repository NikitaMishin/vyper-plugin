// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.vyperplugin.psi.VyperFunctionArgs;
import com.vyperplugin.psi.VyperParamDef;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VyperFunctionArgsImpl extends ASTWrapperPsiElement implements VyperFunctionArgs {

  public VyperFunctionArgsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitFunctionArgs(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VyperParamDef> getParamDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperParamDef.class);
  }

}
