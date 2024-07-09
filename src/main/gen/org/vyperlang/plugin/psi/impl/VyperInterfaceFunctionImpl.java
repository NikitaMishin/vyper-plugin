// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.vyperlang.plugin.psi.VyperTypes.*;
import org.vyperlang.plugin.psi.VyperNamedElementImpl;
import org.vyperlang.plugin.psi.*;

public class VyperInterfaceFunctionImpl extends VyperNamedElementImpl implements VyperInterfaceFunction {

  public VyperInterfaceFunctionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitInterfaceFunction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperFunTypeAnnotation getFunTypeAnnotation() {
    return findChildByClass(VyperFunTypeAnnotation.class);
  }

  @Override
  @Nullable
  public VyperFunctionArgs getFunctionArgs() {
    return findChildByClass(VyperFunctionArgs.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
