// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperType extends VyperElement {

  @Nullable
  VyperListType getListType();

  @Nullable
  VyperMapType getMapType();

  @Nullable
  VyperStructType getStructType();

  @Nullable
  VyperValueType getValueType();

}
