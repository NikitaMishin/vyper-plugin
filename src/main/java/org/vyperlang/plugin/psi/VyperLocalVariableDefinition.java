// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperLocalVariableDefinition extends VyperNamedElement {

  @Nullable
  VyperExpression getExpression();

  @NotNull
  VyperType getType();

  @NotNull
  PsiElement getIdentifier();

}
