// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperCallExpression extends VyperExpression {

  @NotNull
  List<VyperExpression> getExpressionList();

  @NotNull
  List<VyperFunctionCallArguments> getFunctionCallArgumentsList();

}
