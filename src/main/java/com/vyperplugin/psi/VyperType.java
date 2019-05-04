// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperType extends PsiElement {

  @Nullable
  VyperListType getListType();

  @Nullable
  VyperMapType getMapType();

  @Nullable
  VyperStructType getStructType();

  @Nullable
  VyperUnitType getUnitType();

  @Nullable
  VyperValueType getValueType();

}
