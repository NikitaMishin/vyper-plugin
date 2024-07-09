// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

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

  public void visitBinExpression(@NotNull VyperBinExpression o) {
    visitExpression(o);
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

  public void visitConstantDefinitionExpression(@NotNull VyperConstantDefinitionExpression o) {
    visitExpression(o);
    // visitNamedElement(o);
  }

  public void visitEmitStatement(@NotNull VyperEmitStatement o) {
    visitElement(o);
  }

  public void visitEqExpression(@NotNull VyperEqExpression o) {
    visitExpression(o);
  }

  public void visitEventDeclaration(@NotNull VyperEventDeclaration o) {
    visitNamedElement(o);
  }

  public void visitEventLogExpression(@NotNull VyperEventLogExpression o) {
    visitExpression(o);
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

  public void visitExtCallExpression(@NotNull VyperExtCallExpression o) {
    visitExpression(o);
  }

  public void visitForStatement(@NotNull VyperForStatement o) {
    visitElement(o);
  }

  public void visitFunTypeAnnotation(@NotNull VyperFunTypeAnnotation o) {
    visitElement(o);
  }

  public void visitFunctionArgs(@NotNull VyperFunctionArgs o) {
    visitElement(o);
  }

  public void visitFunctionBody(@NotNull VyperFunctionBody o) {
    visitElement(o);
  }

  public void visitFunctionCallArgument(@NotNull VyperFunctionCallArgument o) {
    visitElement(o);
  }

  public void visitFunctionCallArguments(@NotNull VyperFunctionCallArguments o) {
    visitElement(o);
  }

  public void visitFunctionCallExpression(@NotNull VyperFunctionCallExpression o) {
    visitExpression(o);
  }

  public void visitFunctionDecorator(@NotNull VyperFunctionDecorator o) {
    visitElement(o);
  }

  public void visitFunctionDefinition(@NotNull VyperFunctionDefinition o) {
    visitFunctionDefElement(o);
  }

  public void visitFunctionEntrancyKey(@NotNull VyperFunctionEntrancyKey o) {
    visitElement(o);
  }

  public void visitFunctionImplementation(@NotNull VyperFunctionImplementation o) {
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

  public void visitImmutableDefinitionExpression(@NotNull VyperImmutableDefinitionExpression o) {
    visitExpression(o);
    // visitNamedElement(o);
  }

  public void visitImplementsDirective(@NotNull VyperImplementsDirective o) {
    visitElement(o);
  }

  public void visitImportDirective(@NotNull VyperImportDirective o) {
    visitNamedElement(o);
  }

  public void visitInExpression(@NotNull VyperInExpression o) {
    visitExpression(o);
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

  public void visitInterfaceDeclaration(@NotNull VyperInterfaceDeclaration o) {
    visitNamedElement(o);
  }

  public void visitInterfaceFunction(@NotNull VyperInterfaceFunction o) {
    visitNamedElement(o);
  }

  public void visitInterfaceFunctionModifier(@NotNull VyperInterfaceFunctionModifier o) {
    visitElement(o);
  }

  public void visitListType(@NotNull VyperListType o) {
    visitElement(o);
  }

  public void visitLocalVariableDefinition(@NotNull VyperLocalVariableDefinition o) {
    visitNamedElement(o);
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

  public void visitOrExpression(@NotNull VyperOrExpression o) {
    visitExpression(o);
  }

  public void visitParamDef(@NotNull VyperParamDef o) {
    visitNamedElement(o);
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

  public void visitStateVariableDeclaration(@NotNull VyperStateVariableDeclaration o) {
    visitNamedElement(o);
  }

  public void visitStateVariableType(@NotNull VyperStateVariableType o) {
    visitElement(o);
  }

  public void visitStatement(@NotNull VyperStatement o) {
    visitElement(o);
  }

  public void visitStaticCallExpression(@NotNull VyperStaticCallExpression o) {
    visitExpression(o);
  }

  public void visitStructDeclaration(@NotNull VyperStructDeclaration o) {
    visitNamedElement(o);
  }

  public void visitStructExpression(@NotNull VyperStructExpression o) {
    visitExpression(o);
  }

  public void visitStructExpressionMember(@NotNull VyperStructExpressionMember o) {
    visitElement(o);
  }

  public void visitStructType(@NotNull VyperStructType o) {
    visitReferenceElement(o);
  }

  public void visitType(@NotNull VyperType o) {
    visitElement(o);
  }

  public void visitTernaryExpression(@NotNull VyperTernaryExpression o) {
    visitExpression(o);
  }

  public void visitTupleAssignmentExpression(@NotNull VyperTupleAssignmentExpression o) {
    visitExpression(o);
  }

  public void visitUnaryExpression(@NotNull VyperUnaryExpression o) {
    visitExpression(o);
  }

  public void visitValueType(@NotNull VyperValueType o) {
    visitElement(o);
  }

  public void visitVarLiteral(@NotNull VyperVarLiteral o) {
    visitReferenceElement(o);
  }

  public void visitFunctionDefElement(@NotNull VyperFunctionDefElement o) {
    visitElement(o);
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
