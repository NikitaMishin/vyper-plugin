// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperListType extends VyperElement {

  @NotNull
  List<VyperExpression> getExpressionList();

  @Nullable
  VyperStructType getStructType();

  @Nullable
  VyperValueType getValueType();

}
