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

public class VyperStructDefinitionImpl extends VyperNamedElementImpl implements VyperStructDefinition {

  public VyperStructDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VyperVisitor visitor) {
    visitor.visitStructDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VyperVisitor) accept((VyperVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VyperLocalVariableDefinition getLocalVariableDefinition() {
    return findChildByClass(VyperLocalVariableDefinition.class);
  }

  @Override
  @Nullable
  public VyperMultiLineString getMultiLineString() {
    return findChildByClass(VyperMultiLineString.class);
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
