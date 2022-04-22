// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperMapType extends VyperElement {

  @Nullable
  VyperListType getListType();

  @Nullable
  VyperMapType getMapType();

  @Nullable
  VyperStructType getStructType();

  @NotNull
  List<VyperUnitType> getUnitTypeList();

  @NotNull
  List<VyperValueType> getValueTypeList();

}
