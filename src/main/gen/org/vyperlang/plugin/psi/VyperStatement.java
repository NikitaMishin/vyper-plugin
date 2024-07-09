// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperStatement extends VyperElement {

  @Nullable
  VyperEmitStatement getEmitStatement();

  @NotNull
  List<VyperExpression> getExpressionList();

  @Nullable
  VyperForStatement getForStatement();

  @Nullable
  VyperIfStatement getIfStatement();

  @Nullable
  VyperLocalVariableDefinition getLocalVariableDefinition();

  @Nullable
  VyperMultiLineString getMultiLineString();

}
