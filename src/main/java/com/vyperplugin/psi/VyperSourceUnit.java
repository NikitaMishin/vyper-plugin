// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VyperSourceUnit extends VyperElement {

  @Nullable
  VyperBadStatement getBadStatement();

  @Nullable
  VyperEventDeclaration getEventDeclaration();

  @Nullable
  VyperExternalInterfaces getExternalInterfaces();

  @Nullable
  VyperFunctionDefinition getFunctionDefinition();

  @Nullable
  VyperImportPath getImportPath();

  @Nullable
  VyperImplementsDirective getImplementsDirective();

  @Nullable
  VyperStateVariableDeclaration getStateVariableDeclaration();

  @Nullable
  VyperStructDefinition getStructDefinition();

  @Nullable
  VyperUnitsDefinition getUnitsDefinition();

  @Nullable
  VyperUserDefinedConstantsExpression getUserDefinedConstantsExpression();

  @Nullable
  PsiElement getIdentifier();

}
