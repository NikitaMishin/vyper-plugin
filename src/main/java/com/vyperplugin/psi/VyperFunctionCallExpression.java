// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperFunctionCallExpression extends VyperExpression {

  @NotNull
  List<VyperExpression> getExpressionList();

  @Nullable
  VyperFunctionCallArguments getFunctionCallArguments();

}
