// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.vyperlang.plugin.psi.VyperTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.vyperlang.plugin.psi.*;

public class VyperStateVariableTypeImpl extends ASTWrapperPsiElement implements VyperStateVariableType {

  public VyperStateVariableTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStateVariableType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperStateVariableType getStateVariableType() {
    return findChildByClass(VyperStateVariableType.class);
  }

  @Override
  @Nullable
  public VyperType getType() {
    return findChildByClass(VyperType.class);
  }

}
