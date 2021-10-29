// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.vyperplugin.psi.VyperEventProperty;
import com.vyperplugin.psi.VyperIndexedData;
import com.vyperplugin.psi.VyperType;
import com.vyperplugin.psi.VyperVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VyperEventPropertyImpl extends ASTWrapperPsiElement implements VyperEventProperty {

  public VyperEventPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitEventProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VyperIndexedData> getIndexedDataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperIndexedData.class);
  }

  @Override
  @NotNull
  public List<VyperType> getTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VyperType.class);
  }

}
