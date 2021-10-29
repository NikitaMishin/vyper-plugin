// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface VyperFunctionCallExpression extends VyperExpression {

  @NotNull
  List<VyperExpression> getExpressionList();

  @Nullable
  VyperFunctionCallArguments getFunctionCallArguments();

}
