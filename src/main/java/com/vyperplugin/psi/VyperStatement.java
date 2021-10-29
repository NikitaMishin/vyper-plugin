// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import org.jetbrains.annotations.Nullable;

public interface VyperStatement extends VyperElement {

  @Nullable
  VyperBadStatement getBadStatement();

  @Nullable
  VyperEmitStatement getEmitStatement();

  @Nullable
  VyperExpression getExpression();

  @Nullable
  VyperForStatement getForStatement();

  @Nullable
  VyperIfStatement getIfStatement();

  @Nullable
  VyperLocalVariableDefinition getLocalVariableDefinition();

}
