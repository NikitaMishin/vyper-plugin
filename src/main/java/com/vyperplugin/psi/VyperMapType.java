// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
