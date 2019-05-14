// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class VyperVisitor extends PsiElementVisitor {

  public void visitAndExpression(@NotNull VyperAndExpression o) {
    visitExpression(o);
  }

  public void visitAssertExpression(@NotNull VyperAssertExpression o) {
    visitExpression(o);
  }

  public void visitAssignmentExpression(@NotNull VyperAssignmentExpression o) {
    visitExpression(o);
  }

  public void visitBadStatement(@NotNull VyperBadStatement o) {
    visitElement(o);
  }

  public void visitCallExpression(@NotNull VyperCallExpression o) {
    visitExpression(o);
  }

  public void visitClearExpression(@NotNull VyperClearExpression o) {
    visitExpression(o);
  }

  public void visitCompExpression(@NotNull VyperCompExpression o) {
    visitExpression(o);
  }

  public void visitCustomUnitType(@NotNull VyperCustomUnitType o) {
    visitElement(o);
  }

  public void visitEmitStatement(@NotNull VyperEmitStatement o) {
    visitElement(o);
  }

  public void visitEqExpression(@NotNull VyperEqExpression o) {
    visitExpression(o);
  }

  public void visitEventDeclaration(@NotNull VyperEventDeclaration o) {
    visitElement(o);
  }

  public void visitEventProperty(@NotNull VyperEventProperty o) {
    visitElement(o);
  }

  public void visitExponentExpression(@NotNull VyperExponentExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull VyperExpression o) {
    visitElement(o);
  }

  public void visitExternalInterfaces(@NotNull VyperExternalInterfaces o) {
    visitElement(o);
  }

  public void visitForStatement(@NotNull VyperForStatement o) {
    visitElement(o);
  }

  public void visitFunctionArgs(@NotNull VyperFunctionArgs o) {
    visitElement(o);
  }

  public void visitFunctionCallArguments(@NotNull VyperFunctionCallArguments o) {
    visitElement(o);
  }

  public void visitFunctionCallExpression(@NotNull VyperFunctionCallExpression o) {
    visitExpression(o);
  }

  public void visitFunctionDefinition(@NotNull VyperFunctionDefinition o) {
    visitElement(o);
  }

  public void visitFunctionModifier(@NotNull VyperFunctionModifier o) {
    visitElement(o);
  }

  public void visitImportPath(@NotNull VyperImportPath o) {
    visitElement(o);
  }

  public void visitIfStatement(@NotNull VyperIfStatement o) {
    visitElement(o);
  }

  public void visitImplementsDirective(@NotNull VyperImplementsDirective o) {
    visitElement(o);
  }

  public void visitIndexAccessExpression(@NotNull VyperIndexAccessExpression o) {
    visitExpression(o);
  }

  public void visitIndexedData(@NotNull VyperIndexedData o) {
    visitElement(o);
  }

  public void visitInlineArrayExpression(@NotNull VyperInlineArrayExpression o) {
    visitExpression(o);
  }

  public void visitListType(@NotNull VyperListType o) {
    visitElement(o);
  }

  public void visitLocalVariableDeclaration(@NotNull VyperLocalVariableDeclaration o) {
    visitElement(o);
  }

  public void visitLocalVariableDefinition(@NotNull VyperLocalVariableDefinition o) {
    visitElement(o);
  }

  public void visitMapType(@NotNull VyperMapType o) {
    visitElement(o);
  }

  public void visitMemberAccessExpression(@NotNull VyperMemberAccessExpression o) {
    visitExpression(o);
  }

  public void visitMemberIndexAccess(@NotNull VyperMemberIndexAccess o) {
    visitExpression(o);
  }

  public void visitMultDivExpression(@NotNull VyperMultDivExpression o) {
    visitExpression(o);
  }

  public void visitMultiLineString(@NotNull VyperMultiLineString o) {
    visitElement(o);
  }

  public void visitNewExpression(@NotNull VyperNewExpression o) {
    visitExpression(o);
  }

  public void visitOrExpression(@NotNull VyperOrExpression o) {
    visitExpression(o);
  }

  public void visitParenthesiziedExpression(@NotNull VyperParenthesiziedExpression o) {
    visitExpression(o);
  }

  public void visitPlusMinExpression(@NotNull VyperPlusMinExpression o) {
    visitExpression(o);
  }

  public void visitPrimaryExpression(@NotNull VyperPrimaryExpression o) {
    visitExpression(o);
  }

  public void visitRangeExpression(@NotNull VyperRangeExpression o) {
    visitExpression(o);
  }

  public void visitSelfAccessExpression(@NotNull VyperSelfAccessExpression o) {
    visitExpression(o);
  }

  public void visitStateVariableDeclaration(@NotNull VyperStateVariableDeclaration o) {
    visitNamedElement(o);
  }

  public void visitStateVariableModifier(@NotNull VyperStateVariableModifier o) {
    visitElement(o);
  }

  public void visitStatement(@NotNull VyperStatement o) {
    visitElement(o);
  }

  public void visitStructDefinition(@NotNull VyperStructDefinition o) {
    visitElement(o);
  }

  public void visitStructType(@NotNull VyperStructType o) {
    visitElement(o);
  }

  public void visitType(@NotNull VyperType o) {
    visitElement(o);
  }

  public void visitUniqueKey(@NotNull VyperUniqueKey o) {
    visitElement(o);
  }

  public void visitUnaryExpression(@NotNull VyperUnaryExpression o) {
    visitExpression(o);
  }

  public void visitUnitType(@NotNull VyperUnitType o) {
    visitElement(o);
  }

  public void visitUnitsDefinition(@NotNull VyperUnitsDefinition o) {
    visitElement(o);
  }

  public void visitUserDefinedConstantsExpression(@NotNull VyperUserDefinedConstantsExpression o) {
    visitExpression(o);
  }

  public void visitValueType(@NotNull VyperValueType o) {
    visitElement(o);
  }

  public void visitVarLiteral(@NotNull VyperVarLiteral o) {
    visitReferenceElement(o);
  }

  public void visitNamedElement(@NotNull VyperNamedElement o) {
    visitElement(o);
  }

  public void visitReferenceElement(@NotNull VyperReferenceElement o) {
    visitElement(o);
  }

  public void visitElement(@NotNull VyperElement o) {
    super.visitElement(o);
  }

}
