// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperFunctionDefinition extends VyperFunctionDefElement {

  @Nullable
  VyperFunTypeAnnotation getFunTypeAnnotation();

  @Nullable
  VyperFunctionArgs getFunctionArgs();

  @Nullable
  VyperFunctionBody getFunctionBody();

  @NotNull
  List<VyperFunctionModifier> getFunctionModifierList();

  @Nullable
  PsiElement getIdentifier();

}
