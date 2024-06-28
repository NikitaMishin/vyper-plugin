// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.vyperlang.plugin.psi.VyperTypes.*;
import static org.vyperlang.plugin.parser.ParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class VyperParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = Companion.adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return VyperFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(AND_EXPRESSION, ASSERT_EXPRESSION, ASSIGNMENT_EXPRESSION, BIN_EXPRESSION,
      CALL_EXPRESSION, CLEAR_EXPRESSION, COMP_EXPRESSION, EQ_EXPRESSION,
      EVENT_LOG_EXPRESSION, EXPONENT_EXPRESSION, EXPRESSION, FUNCTION_CALL_EXPRESSION,
      INDEX_ACCESS_EXPRESSION, INLINE_ARRAY_EXPRESSION, IN_EXPRESSION, MEMBER_ACCESS_EXPRESSION,
      MEMBER_INDEX_ACCESS, MULT_DIV_EXPRESSION, NEW_EXPRESSION, OR_EXPRESSION,
      PARENTHESIZIED_EXPRESSION, PLUS_MIN_EXPRESSION, PRIMARY_EXPRESSION, RANGE_EXPRESSION,
      TERNARY_EXPRESSION, TUPLE_ASSIGNMENT_EXPRESSION, UNARY_EXPRESSION, USER_DEFINED_CONSTANTS_EXPRESSION),
  };

  /* ********************************************************** */
  // ',' ( stringLiteralDouble | stringLiteralSingle | MultiLineString )
  static boolean AssertMessage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertMessage")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && AssertMessage_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // stringLiteralDouble | stringLiteralSingle | MultiLineString
  private static boolean AssertMessage_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertMessage_1")) return false;
    boolean r;
    r = consumeToken(b, STRINGLITERALDOUBLE);
    if (!r) r = consumeToken(b, STRINGLITERALSINGLE);
    if (!r) r = MultiLineString(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // booleanLiteral
  static boolean BooleanLiteral(PsiBuilder b, int l) {
    return consumeToken(b, BOOLEANLITERAL);
  }

  /* ********************************************************** */
  // break
  static boolean Break(PsiBuilder b, int l) {
    return consumeToken(b, BREAK);
  }

  /* ********************************************************** */
  // Expression &INDNONE ':' ((&INDNONE Statement)
  //                                      | <<indented (Statement (&INDEQ Statement)*)>>)
  static boolean CondStmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1, -1);
    r = r && CondStmt_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && CondStmt_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CondStmt_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE Statement)
  //                                      | <<indented (Statement (&INDEQ Statement)*)>>
  private static boolean CondStmt_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CondStmt_3_0(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::CondStmt_3_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE Statement
  private static boolean CondStmt_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CondStmt_3_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CondStmt_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Statement (&INDEQ Statement)*
  private static boolean CondStmt_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    r = r && CondStmt_3_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean CondStmt_3_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CondStmt_3_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CondStmt_3_1_0_1", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean CondStmt_3_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CondStmt_3_1_0_1_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean CondStmt_3_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondStmt_3_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // continue
  static boolean Continue(PsiBuilder b, int l) {
    return consumeToken(b, CONTINUE);
  }

  /* ********************************************************** */
  // decimalNumber
  static boolean DecimalNumber(PsiBuilder b, int l) {
    return consumeToken(b, DECIMALNUMBER);
  }

  /* ********************************************************** */
  // elif &INDNONE CondStmt
  static boolean ElifStmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElifStmt")) return false;
    if (!nextTokenIs(b, ELIF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ELIF);
    p = r; // pin = 1
    r = r && report_error_(b, ElifStmt_1(b, l + 1));
    r = p && CondStmt(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ElifStmt_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElifStmt_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // else &INDNONE ':' ((&INDNONE Statement)
  //                                        | <<indented (Statement (&INDEQ Statement)*)>>)
  static boolean ElseStmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ELSE);
    p = r; // pin = 1
    r = r && report_error_(b, ElseStmt_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && ElseStmt_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ElseStmt_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE Statement)
  //                                        | <<indented (Statement (&INDEQ Statement)*)>>
  private static boolean ElseStmt_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElseStmt_3_0(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::ElseStmt_3_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE Statement
  private static boolean ElseStmt_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElseStmt_3_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ElseStmt_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Statement (&INDEQ Statement)*
  private static boolean ElseStmt_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    r = r && ElseStmt_3_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean ElseStmt_3_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ElseStmt_3_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ElseStmt_3_1_0_1", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean ElseStmt_3_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElseStmt_3_1_0_1_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean ElseStmt_3_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStmt_3_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'log.' &INDNONE FunctionCallExpression
  public static boolean EmitStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmitStatement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EMIT_STATEMENT, "<emit statement>");
    r = consumeToken(b, "log.");
    p = r; // pin = 1
    r = r && report_error_(b, EmitStatement_1(b, l + 1));
    r = p && FunctionCallExpression(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean EmitStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmitStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // event &INDNONE Identifier &INDNONE ':' ((&INDGT EventProperty?) | (&INDNONE PassStatement))
  public static boolean EventDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration")) return false;
    if (!nextTokenIs(b, EVENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EVENT_DECLARATION, null);
    r = consumeToken(b, EVENT);
    r = r && EventDeclaration_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && EventDeclaration_3(b, l + 1);
    r = r && consumeToken(b, COLON);
    p = r; // pin = 5
    r = r && EventDeclaration_5(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean EventDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean EventDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDGT EventProperty?) | (&INDNONE PassStatement)
  private static boolean EventDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EventDeclaration_5_0(b, l + 1);
    if (!r) r = EventDeclaration_5_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDGT EventProperty?
  private static boolean EventDeclaration_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EventDeclaration_5_0_0(b, l + 1);
    r = r && EventDeclaration_5_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDGT
  private static boolean EventDeclaration_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indGt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // EventProperty?
  private static boolean EventDeclaration_5_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5_0_1")) return false;
    EventProperty(b, l + 1);
    return true;
  }

  // &INDNONE PassStatement
  private static boolean EventDeclaration_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EventDeclaration_5_1_0(b, l + 1);
    r = r && PassStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean EventDeclaration_5_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Identifier ':' (IndexedData | TYPE) (Identifier ':' (IndexedData |TYPE))*
  public static boolean EventProperty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && EventProperty_2(b, l + 1);
    r = r && EventProperty_3(b, l + 1);
    exit_section_(b, m, EVENT_PROPERTY, r);
    return r;
  }

  // IndexedData | TYPE
  private static boolean EventProperty_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_2")) return false;
    boolean r;
    r = IndexedData(b, l + 1);
    if (!r) r = TYPE(b, l + 1);
    return r;
  }

  // (Identifier ':' (IndexedData |TYPE))*
  private static boolean EventProperty_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EventProperty_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EventProperty_3", c)) break;
    }
    return true;
  }

  // Identifier ':' (IndexedData |TYPE)
  private static boolean EventProperty_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && EventProperty_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IndexedData |TYPE
  private static boolean EventProperty_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3_0_2")) return false;
    boolean r;
    r = IndexedData(b, l + 1);
    if (!r) r = TYPE(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Expression
  static boolean ExpressionStatement(PsiBuilder b, int l) {
    return Expression(b, l + 1, -1);
  }

  /* ********************************************************** */
  // pure | view | nonpayable | payable
  static boolean ExternalFunStatus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalFunStatus")) return false;
    boolean r;
    r = consumeToken(b, PURE);
    if (!r) r = consumeToken(b, VIEW);
    if (!r) r = consumeToken(b, NONPAYABLE);
    if (!r) r = consumeToken(b, PAYABLE);
    return r;
  }

  /* ********************************************************** */
  // def &INDNONE Identifier FunctionArgs FunTypeAnnotation? &INDNONE ':' &INDNONE ExternalFunStatus
  static boolean ExternalInterfaceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, DEF);
    p = r; // pin = 1
    r = r && report_error_(b, ExternalInterfaceBody_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, FunctionArgs(b, l + 1)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_4(b, l + 1)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_5(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_7(b, l + 1)) && r;
    r = p && ExternalFunStatus(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunTypeAnnotation?
  private static boolean ExternalInterfaceBody_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_4")) return false;
    FunTypeAnnotation(b, l + 1);
    return true;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_7")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // interface &INDNONE Identifier &INDNONE ':'
  //                         <<indented (ExternalInterfaceBody+) >>
  public static boolean ExternalInterfaces(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaces")) return false;
    if (!nextTokenIs(b, INTERFACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTERNAL_INTERFACES, null);
    r = consumeToken(b, INTERFACE);
    p = r; // pin = 1
    r = r && report_error_(b, ExternalInterfaces_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, ExternalInterfaces_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && indented(b, l + 1, VyperParser::ExternalInterfaces_5_0) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ExternalInterfaces_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaces_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaces_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaces_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ExternalInterfaceBody+
  private static boolean ExternalInterfaces_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaces_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ExternalInterfaceBody(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ExternalInterfaceBody(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExternalInterfaces_5_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // fixedNumber
  static boolean FixedNumber(PsiBuilder b, int l) {
    return consumeToken(b, FIXEDNUMBER);
  }

  /* ********************************************************** */
  // for &INDNONE Identifier &INDNONE in &INDNONE Expression &INDNONE ':'
  //                     (&INDNONE Statement | <<indented (Statement (&INDEQ Statement)*)>>)
  public static boolean ForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FOR_STATEMENT, null);
    r = consumeToken(b, FOR);
    p = r; // pin = 1
    r = r && report_error_(b, ForStatement_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, ForStatement_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IN)) && r;
    r = p && report_error_(b, ForStatement_5(b, l + 1)) && r;
    r = p && report_error_(b, Expression(b, l + 1, -1)) && r;
    r = p && report_error_(b, ForStatement_7(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && ForStatement_9(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ForStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_7")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE Statement | <<indented (Statement (&INDEQ Statement)*)>>
  private static boolean ForStatement_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_9_0(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::ForStatement_9_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE Statement
  private static boolean ForStatement_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_9_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_9_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Statement (&INDEQ Statement)*
  private static boolean ForStatement_9_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    r = r && ForStatement_9_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean ForStatement_9_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ForStatement_9_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ForStatement_9_1_0_1", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean ForStatement_9_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_9_1_0_1_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean ForStatement_9_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_9_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // def (&INDNONE Identifier) FunctionArgs FunTypeAnnotation? (&INDNONE ':')
  static boolean FunDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, DEF);
    p = r; // pin = 1
    r = r && report_error_(b, FunDef_1(b, l + 1));
    r = p && report_error_(b, FunctionArgs(b, l + 1)) && r;
    r = p && report_error_(b, FunDef_3(b, l + 1)) && r;
    r = p && FunDef_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE Identifier
  private static boolean FunDef_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunDef_1_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunTypeAnnotation?
  private static boolean FunDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_3")) return false;
    FunTypeAnnotation(b, l + 1);
    return true;
  }

  // &INDNONE ':'
  private static boolean FunDef_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunDef_4_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // &INDNONE '->' &INDNONE ( TYPE | '(' TYPE (',' TYPE)* ')' )
  public static boolean FunTypeAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUN_TYPE_ANNOTATION, "<fun type annotation>");
    r = FunTypeAnnotation_0(b, l + 1);
    r = r && consumeToken(b, "->");
    r = r && FunTypeAnnotation_2(b, l + 1);
    r = r && FunTypeAnnotation_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean FunTypeAnnotation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean FunTypeAnnotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TYPE | '(' TYPE (',' TYPE)* ')'
  private static boolean FunTypeAnnotation_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TYPE(b, l + 1);
    if (!r) r = FunTypeAnnotation_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '(' TYPE (',' TYPE)* ')'
  private static boolean FunTypeAnnotation_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && TYPE(b, l + 1);
    r = r && FunTypeAnnotation_3_1_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' TYPE)*
  private static boolean FunTypeAnnotation_3_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_3_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunTypeAnnotation_3_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunTypeAnnotation_3_1_2", c)) break;
    }
    return true;
  }

  // ',' TYPE
  private static boolean FunTypeAnnotation_3_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunTypeAnnotation_3_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (ParamDef (',' (ParamDef | & ')'))*)? ')'
  public static boolean FunctionArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_ARGS, null);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, FunctionArgs_1(b, l + 1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ParamDef (',' (ParamDef | & ')'))*)?
  private static boolean FunctionArgs_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1")) return false;
    FunctionArgs_1_0(b, l + 1);
    return true;
  }

  // ParamDef (',' (ParamDef | & ')'))*
  private static boolean FunctionArgs_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ParamDef(b, l + 1);
    p = r; // pin = 1
    r = r && FunctionArgs_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (ParamDef | & ')'))*
  private static boolean FunctionArgs_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionArgs_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionArgs_1_0_1", c)) break;
    }
    return true;
  }

  // ',' (ParamDef | & ')')
  private static boolean FunctionArgs_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && FunctionArgs_1_0_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ParamDef | & ')'
  private static boolean FunctionArgs_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParamDef(b, l + 1);
    if (!r) r = FunctionArgs_1_0_1_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // & ')'
  private static boolean FunctionArgs_1_0_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ELLIPSIS|FunctionImplementation
  public static boolean FunctionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_BODY, "<function body>");
    r = consumeToken(b, ELLIPSIS);
    if (!r) r = FunctionImplementation(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression | ( Identifier '=' Expression )
  public static boolean FunctionCallArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArgument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_ARGUMENT, "<function call argument>");
    r = Expression(b, l + 1, -1);
    if (!r) r = FunctionCallArgument_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier '=' Expression
  private static boolean FunctionCallArgument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArgument_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, ASSIGN);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (FunctionCallArgument (',' (FunctionCallArgument | &')'))*)? ')'
  public static boolean FunctionCallArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_ARGUMENTS, null);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, FunctionCallArguments_1(b, l + 1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (FunctionCallArgument (',' (FunctionCallArgument | &')'))*)?
  private static boolean FunctionCallArguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1")) return false;
    FunctionCallArguments_1_0(b, l + 1);
    return true;
  }

  // FunctionCallArgument (',' (FunctionCallArgument | &')'))*
  private static boolean FunctionCallArguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = FunctionCallArgument(b, l + 1);
    p = r; // pin = 1
    r = r && FunctionCallArguments_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (FunctionCallArgument | &')'))*
  private static boolean FunctionCallArguments_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionCallArguments_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionCallArguments_1_0_1", c)) break;
    }
    return true;
  }

  // ',' (FunctionCallArgument | &')')
  private static boolean FunctionCallArguments_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && FunctionCallArguments_1_0_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // FunctionCallArgument | &')'
  private static boolean FunctionCallArguments_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallArgument(b, l + 1);
    if (!r) r = FunctionCallArguments_1_0_1_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &')'
  private static boolean FunctionCallArguments_1_0_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0_1_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Identifier &INDNONE FunctionCallArguments) |
  //                            ( ( PrimaryExpression | NewExpression | TypeName  )
  //                            ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  //                                      &INDNONE FunctionCallArguments )  {
  //    //implements and mixin
  // }
  public static boolean FunctionCallExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_EXPRESSION, "<function call expression>");
    r = FunctionCallExpression_0(b, l + 1);
    if (!r) r = FunctionCallExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier &INDNONE FunctionCallArguments
  private static boolean FunctionCallExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && FunctionCallExpression_0_1(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionCallExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( ( PrimaryExpression | NewExpression | TypeName  )
  //                            ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  //                                      &INDNONE FunctionCallArguments )  {
  //    //implements and mixin
  // }
  private static boolean FunctionCallExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0(b, l + 1);
    r = r && FunctionCallExpression_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( PrimaryExpression | NewExpression | TypeName  )
  //                            ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  //                                      &INDNONE FunctionCallArguments
  private static boolean FunctionCallExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0_0(b, l + 1);
    r = r && FunctionCallExpression_1_0_1(b, l + 1);
    r = r && FunctionCallExpression_1_0_2(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PrimaryExpression | NewExpression | TypeName
  private static boolean FunctionCallExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_0")) return false;
    boolean r;
    r = PrimaryExpression(b, l + 1);
    if (!r) r = NewExpression(b, l + 1);
    if (!r) r = consumeToken(b, TYPENAME);
    return r;
  }

  // ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  private static boolean FunctionCallExpression_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionCallExpression_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionCallExpression_1_0_1", c)) break;
    }
    return true;
  }

  // ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' )
  private static boolean FunctionCallExpression_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0_1_0_0(b, l + 1);
    if (!r) r = FunctionCallExpression_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '.' Identifier
  private static boolean FunctionCallExpression_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0_1_0_0_0(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionCallExpression_1_0_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '[' Expression? ']'
  private static boolean FunctionCallExpression_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0_1_0_1_0(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && FunctionCallExpression_1_0_1_0_1_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionCallExpression_1_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expression?
  private static boolean FunctionCallExpression_1_0_1_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_1_0_1_2")) return false;
    Expression(b, l + 1, -1);
    return true;
  }

  // &INDNONE
  private static boolean FunctionCallExpression_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // {
  //    //implements and mixin
  // }
  private static boolean FunctionCallExpression_1_1(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // public | internal | view | pure | external | private | nonpayable | payable | (nonreentrant (&INDNONE '(') UNIQUE_KEY ')')
  public static boolean FunctionDecorator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECORATOR, "<function decorator>");
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, INTERNAL);
    if (!r) r = consumeToken(b, VIEW);
    if (!r) r = consumeToken(b, PURE);
    if (!r) r = consumeToken(b, EXTERNAL);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, NONPAYABLE);
    if (!r) r = consumeToken(b, PAYABLE);
    if (!r) r = FunctionDecorator_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // nonreentrant (&INDNONE '(') UNIQUE_KEY ')'
  private static boolean FunctionDecorator_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NONREENTRANT);
    r = r && FunctionDecorator_8_1(b, l + 1);
    r = r && UNIQUE_KEY(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '('
  private static boolean FunctionDecorator_8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_8_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDecorator_8_1_0(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionDecorator_8_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_8_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FunctionModifier (&INDEQ FunctionModifier)* FunDef FunctionBody
  public static boolean FunctionDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDefinition")) return false;
    if (!nextTokenIs(b, DECORATOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DEFINITION, null);
    r = FunctionModifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, FunctionDefinition_1(b, l + 1));
    r = p && report_error_(b, FunDef(b, l + 1)) && r;
    r = p && FunctionBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (&INDEQ FunctionModifier)*
  private static boolean FunctionDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDefinition_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionDefinition_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionDefinition_1", c)) break;
    }
    return true;
  }

  // &INDEQ FunctionModifier
  private static boolean FunctionDefinition_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDefinition_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDefinition_1_0_0(b, l + 1);
    r = r && FunctionModifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FunctionDefinition_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDefinition_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (&INDNONE Statement) | <<indented ((MultiLineString)? &INDEQ Statement (&INDEQ Statement)*) >>
  public static boolean FunctionImplementation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_IMPLEMENTATION, "<function implementation>");
    r = FunctionImplementation_0(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::FunctionImplementation_1_0);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE Statement
  private static boolean FunctionImplementation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionImplementation_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionImplementation_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (MultiLineString)? &INDEQ Statement (&INDEQ Statement)*
  private static boolean FunctionImplementation_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionImplementation_1_0_0(b, l + 1);
    r = r && FunctionImplementation_1_0_1(b, l + 1);
    r = r && Statement(b, l + 1);
    r = r && FunctionImplementation_1_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MultiLineString)?
  private static boolean FunctionImplementation_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_0")) return false;
    FunctionImplementation_1_0_0_0(b, l + 1);
    return true;
  }

  // (MultiLineString)
  private static boolean FunctionImplementation_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MultiLineString(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FunctionImplementation_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean FunctionImplementation_1_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionImplementation_1_0_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionImplementation_1_0_3", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean FunctionImplementation_1_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionImplementation_1_0_3_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FunctionImplementation_1_0_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionImplementation_1_0_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DECORATOR (&INDNONE FunctionDecorator)
  public static boolean FunctionModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier")) return false;
    if (!nextTokenIs(b, DECORATOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_MODIFIER, null);
    r = consumeToken(b, DECORATOR);
    p = r; // pin = 1
    r = r && FunctionModifier_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE FunctionDecorator
  private static boolean FunctionModifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionModifier_1_0(b, l + 1);
    r = r && FunctionDecorator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionModifier_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // hexLiteral
  static boolean HexLiteral(PsiBuilder b, int l) {
    return consumeToken(b, HEXLITERAL);
  }

  /* ********************************************************** */
  // hexNumber
  static boolean HexNumber(PsiBuilder b, int l) {
    return consumeToken(b, HEXNUMBER);
  }

  /* ********************************************************** */
  // Identifier  (&INDNONE '.' &INDNONE Identifier)*
  public static boolean IMPORT_PATH(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IMPORT_PATH")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && IMPORT_PATH_1(b, l + 1);
    exit_section_(b, m, IMPORT_PATH, r);
    return r;
  }

  // (&INDNONE '.' &INDNONE Identifier)*
  private static boolean IMPORT_PATH_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IMPORT_PATH_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IMPORT_PATH_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IMPORT_PATH_1", c)) break;
    }
    return true;
  }

  // &INDNONE '.' &INDNONE Identifier
  private static boolean IMPORT_PATH_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IMPORT_PATH_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IMPORT_PATH_1_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && IMPORT_PATH_1_0_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean IMPORT_PATH_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IMPORT_PATH_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean IMPORT_PATH_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IMPORT_PATH_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // if &INDNONE CondStmt
  //                 (&INDEQ ElifStmt)*
  //                 (&INDEQ ElseStmt)?
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IF_STATEMENT, null);
    r = consumeToken(b, IF);
    p = r; // pin = 1
    r = r && report_error_(b, IfStatement_1(b, l + 1));
    r = p && report_error_(b, CondStmt(b, l + 1)) && r;
    r = p && report_error_(b, IfStatement_3(b, l + 1)) && r;
    r = p && IfStatement_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean IfStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDEQ ElifStmt)*
  private static boolean IfStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IfStatement_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IfStatement_3", c)) break;
    }
    return true;
  }

  // &INDEQ ElifStmt
  private static boolean IfStatement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfStatement_3_0_0(b, l + 1);
    r = r && ElifStmt(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean IfStatement_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDEQ ElseStmt)?
  private static boolean IfStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4")) return false;
    IfStatement_4_0(b, l + 1);
    return true;
  }

  // &INDEQ ElseStmt
  private static boolean IfStatement_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfStatement_4_0_0(b, l + 1);
    r = r && ElseStmt(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean IfStatement_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // implements &INDNONE ':' &INDNONE Identifier (&INDNONE ',' &INDNONE Identifier )*
  public static boolean ImplementsDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective")) return false;
    if (!nextTokenIs(b, IMPLEMENTS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IMPLEMENTS_DIRECTIVE, null);
    r = consumeToken(b, IMPLEMENTS);
    p = r; // pin = 1
    r = r && report_error_(b, ImplementsDirective_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, ImplementsDirective_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && ImplementsDirective_5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ImplementsDirective_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImplementsDirective_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE ',' &INDNONE Identifier )*
  private static boolean ImplementsDirective_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImplementsDirective_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImplementsDirective_5", c)) break;
    }
    return true;
  }

  // &INDNONE ',' &INDNONE Identifier
  private static boolean ImplementsDirective_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImplementsDirective_5_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && ImplementsDirective_5_0_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImplementsDirective_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImplementsDirective_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsDirective_5_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( import &INDNONE IMPORT_PATH &INDNONE as &INDNONE Identifier )
  //                    | ( from &INDNONE IMPORT_PATH &INDNONE import &INDNONE Identifier )
  static boolean ImportDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective")) return false;
    if (!nextTokenIs(b, "", FROM, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDirective_0(b, l + 1);
    if (!r) r = ImportDirective_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // import &INDNONE IMPORT_PATH &INDNONE as &INDNONE Identifier
  private static boolean ImportDirective_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, IMPORT);
    p = r; // pin = 1
    r = r && report_error_(b, ImportDirective_0_1(b, l + 1));
    r = p && report_error_(b, IMPORT_PATH(b, l + 1)) && r;
    r = p && report_error_(b, ImportDirective_0_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, AS)) && r;
    r = p && report_error_(b, ImportDirective_0_5(b, l + 1)) && r;
    r = p && consumeToken(b, IDENTIFIER) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ImportDirective_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // from &INDNONE IMPORT_PATH &INDNONE import &INDNONE Identifier
  private static boolean ImportDirective_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, FROM);
    p = r; // pin = 1
    r = r && report_error_(b, ImportDirective_1_1(b, l + 1));
    r = p && report_error_(b, IMPORT_PATH(b, l + 1)) && r;
    r = p && report_error_(b, ImportDirective_1_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IMPORT)) && r;
    r = p && report_error_(b, ImportDirective_1_5(b, l + 1)) && r;
    r = p && consumeToken(b, IDENTIFIER) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ImportDirective_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IndexedType '(' TYPE ')'
  public static boolean IndexedData(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexedData")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INDEXED_DATA, "<indexed data>");
    r = IndexedType(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'indexed'
  static boolean IndexedType(PsiBuilder b, int l) {
    return consumeToken(b, "indexed");
  }

  /* ********************************************************** */
  // ( ValueType | StructType ) &INDNONE '[' (DecimalNumber | Identifier) ']'
  //                     (&INDNONE '[' (DecimalNumber | Identifier) ']')*
  public static boolean ListType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_TYPE, "<list type>");
    r = ListType_0(b, l + 1);
    r = r && ListType_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ListType_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && ListType_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ValueType | StructType
  private static boolean ListType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_0")) return false;
    boolean r;
    r = ValueType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    return r;
  }

  // &INDNONE
  private static boolean ListType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DecimalNumber | Identifier
  private static boolean ListType_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_3")) return false;
    boolean r;
    r = DecimalNumber(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  // (&INDNONE '[' (DecimalNumber | Identifier) ']')*
  private static boolean ListType_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ListType_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ListType_5", c)) break;
    }
    return true;
  }

  // &INDNONE '[' (DecimalNumber | Identifier) ']'
  private static boolean ListType_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ListType_5_0_0(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ListType_5_0_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ListType_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DecimalNumber | Identifier
  private static boolean ListType_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5_0_2")) return false;
    boolean r;
    r = DecimalNumber(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // Identifier &INDNONE ':' &INDNONE TYPE
  public static boolean LocalVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && LocalVariableDeclaration_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && LocalVariableDeclaration_3(b, l + 1);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, LOCAL_VARIABLE_DECLARATION, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( LocalVariableDeclaration (&INDNONE '=') (&INDNONE Expression) ) | LocalVariableDeclaration
  public static boolean LocalVariableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_0(b, l + 1);
    if (!r) r = LocalVariableDeclaration(b, l + 1);
    exit_section_(b, m, LOCAL_VARIABLE_DEFINITION, r);
    return r;
  }

  // LocalVariableDeclaration (&INDNONE '=') (&INDNONE Expression)
  private static boolean LocalVariableDefinition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDeclaration(b, l + 1);
    r = r && LocalVariableDefinition_0_1(b, l + 1);
    r = r && LocalVariableDefinition_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '='
  private static boolean LocalVariableDefinition_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_0_1_0(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE Expression
  private static boolean LocalVariableDefinition_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_0_2_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( DynArray | HashMap ) &INDNONE '[' ValueType ',' TYPE ']'
  public static boolean MapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType")) return false;
    if (!nextTokenIs(b, "<map type>", DYNARRAY, HASHMAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_TYPE, "<map type>");
    r = MapType_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, MapType_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACKET)) && r;
    r = p && report_error_(b, ValueType(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, TYPE(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // DynArray | HashMap
  private static boolean MapType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_0")) return false;
    boolean r;
    r = consumeToken(b, DYNARRAY);
    if (!r) r = consumeToken(b, HASHMAP);
    return r;
  }

  // &INDNONE
  private static boolean MapType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MultiLineStringToken
  public static boolean MultiLineString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiLineString")) return false;
    if (!nextTokenIs(b, MULTILINESTRINGTOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MULTILINESTRINGTOKEN);
    exit_section_(b, m, MULTI_LINE_STRING, r);
    return r;
  }

  /* ********************************************************** */
  // HexNumber | DecimalNumber |FixedNumber
  static boolean NumberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral")) return false;
    boolean r;
    r = HexNumber(b, l + 1);
    if (!r) r = DecimalNumber(b, l + 1);
    if (!r) r = FixedNumber(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Identifier ':' TYPE ('=' Expression)?
  public static boolean ParamDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDef")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && TYPE(b, l + 1);
    r = r && ParamDef_3(b, l + 1);
    exit_section_(b, m, PARAM_DEF, r);
    return r;
  }

  // ('=' Expression)?
  private static boolean ParamDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDef_3")) return false;
    ParamDef_3_0(b, l + 1);
    return true;
  }

  // '=' Expression
  private static boolean ParamDef_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDef_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' &INDNONE ')'
  static boolean Parenthesis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parenthesis")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Parenthesis_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean Parenthesis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parenthesis_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PASS
  static boolean PassStatement(PsiBuilder b, int l) {
    return consumeToken(b, PASS);
  }

  /* ********************************************************** */
  // raise
  static boolean Raise(PsiBuilder b, int l) {
    return consumeToken(b, RAISE);
  }

  /* ********************************************************** */
  // (Identifier | NumberLiteral) (&INDNONE ',' &INDNONE Expression)?
  static boolean RangeInterval(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RangeInterval_0(b, l + 1);
    r = r && RangeInterval_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier | NumberLiteral
  private static boolean RangeInterval_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_0")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = NumberLiteral(b, l + 1);
    return r;
  }

  // (&INDNONE ',' &INDNONE Expression)?
  private static boolean RangeInterval_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_1")) return false;
    RangeInterval_1_0(b, l + 1);
    return true;
  }

  // &INDNONE ',' &INDNONE Expression
  private static boolean RangeInterval_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RangeInterval_1_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && RangeInterval_1_0_2(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean RangeInterval_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean RangeInterval_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // return (&INDNONE Expression (',' Expression)*)?
  static boolean Return(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, RETURN);
    p = r; // pin = 1
    r = r && Return_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (&INDNONE Expression (',' Expression)*)?
  private static boolean Return_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1")) return false;
    Return_1_0(b, l + 1);
    return true;
  }

  // &INDNONE Expression (',' Expression)*
  private static boolean Return_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Return_1_0_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    r = r && Return_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean Return_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' Expression)*
  private static boolean Return_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Return_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Return_1_0_2", c)) break;
    }
    return true;
  }

  // ',' Expression
  private static boolean Return_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LocalVariableDefinition | ExpressionStatement
  static boolean SimpleStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleStatement")) return false;
    boolean r;
    r = LocalVariableDefinition(b, l + 1);
    if (!r) r = ExpressionStatement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ImportDirective
  //                         | UserDefinedConstantsExpression
  //                         | ImplementsDirective
  //                         | StructDefinition
  //                         | ExternalInterfaces
  //                         | EventDeclaration
  //                         | FunctionDefinition
  //                         | StateVariableDeclaration
  static boolean SourceUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SourceUnit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = ImportDirective(b, l + 1);
    if (!r) r = UserDefinedConstantsExpression(b, l + 1);
    if (!r) r = ImplementsDirective(b, l + 1);
    if (!r) r = StructDefinition(b, l + 1);
    if (!r) r = ExternalInterfaces(b, l + 1);
    if (!r) r = EventDeclaration(b, l + 1);
    if (!r) r = FunctionDefinition(b, l + 1);
    if (!r) r = StateVariableDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, VyperParser::structRecover);
    return r;
  }

  /* ********************************************************** */
  // Identifier &INDNONE ':' StateVariableType (&INDNONE '=' &INDNONE Expression)?
  public static boolean StateVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && StateVariableDeclaration_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && StateVariableType(b, l + 1);
    r = r && StateVariableDeclaration_4(b, l + 1);
    exit_section_(b, m, STATE_VARIABLE_DECLARATION, r);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE '=' &INDNONE Expression)?
  private static boolean StateVariableDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_4")) return false;
    StateVariableDeclaration_4_0(b, l + 1);
    return true;
  }

  // &INDNONE '=' &INDNONE Expression
  private static boolean StateVariableDeclaration_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StateVariableDeclaration_4_0_0(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && StateVariableDeclaration_4_0_2(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_4_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_4_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // public | private | immutable | constant
  public static boolean StateVariableModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATE_VARIABLE_MODIFIER, "<state variable modifier>");
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, IMMUTABLE);
    if (!r) r = consumeToken(b, CONSTANT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TYPE | StateVariableModifier &INDNONE '(' &INDNONE StateVariableType &INDNONE ')'
  public static boolean StateVariableType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATE_VARIABLE_TYPE, "<state variable type>");
    r = TYPE(b, l + 1);
    if (!r) r = StateVariableType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // StateVariableModifier &INDNONE '(' &INDNONE StateVariableType &INDNONE ')'
  private static boolean StateVariableType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StateVariableModifier(b, l + 1);
    r = r && StateVariableType_1_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && StateVariableType_1_3(b, l + 1);
    r = r && StateVariableType(b, l + 1);
    r = r && StateVariableType_1_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableType_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableType_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType_1_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PassStatement
  //               | IfStatement
  //               | ForStatement
  //               | Continue
  //               | Break
  //               | Return
  //               | Raise
  //               | EmitStatement
  //               | SimpleStatement
  //               | MultiLineString
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = PassStatement(b, l + 1);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = ForStatement(b, l + 1);
    if (!r) r = Continue(b, l + 1);
    if (!r) r = Break(b, l + 1);
    if (!r) r = Return(b, l + 1);
    if (!r) r = Raise(b, l + 1);
    if (!r) r = EmitStatement(b, l + 1);
    if (!r) r = SimpleStatement(b, l + 1);
    if (!r) r = MultiLineString(b, l + 1);
    exit_section_(b, l, m, r, false, VyperParser::recoverStatement);
    return r;
  }

  /* ********************************************************** */
  // stringLiteralDouble | stringLiteralDoubleB | stringLiteralSingle | stringLiteralSingleB|MultiLineString
  static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    boolean r;
    r = consumeToken(b, STRINGLITERALDOUBLE);
    if (!r) r = consumeToken(b, STRINGLITERALDOUBLEB);
    if (!r) r = consumeToken(b, STRINGLITERALSINGLE);
    if (!r) r = consumeToken(b, STRINGLITERALSINGLEB);
    if (!r) r = MultiLineString(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // StructSingleDef | <<indented (StructMultipleDef) >>
  static boolean StructBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructBody")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructSingleDef(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::StructBody_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // (StructMultipleDef)
  private static boolean StructBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructMultipleDef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // struct &INDNONE Identifier &INDNONE COLON  StructBody
  public static boolean StructDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDefinition")) return false;
    if (!nextTokenIs(b, STRUCT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_DEFINITION, null);
    r = consumeToken(b, STRUCT);
    p = r; // pin = 1
    r = r && report_error_(b, StructDefinition_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, StructDefinition_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && StructBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean StructDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDefinition_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StructDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDefinition_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LocalVariableDefinition | Identifier | StringLiteral
  static boolean StructLocalVariableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLocalVariableDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = LocalVariableDefinition(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, VyperParser::structRecover);
    return r;
  }

  /* ********************************************************** */
  // StructLocalVariableDefinition (&INDEQ StructLocalVariableDefinition )*
  static boolean StructMultipleDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructMultipleDef")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructLocalVariableDefinition(b, l + 1);
    r = r && StructMultipleDef_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDEQ StructLocalVariableDefinition )*
  private static boolean StructMultipleDef_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructMultipleDef_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructMultipleDef_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructMultipleDef_1", c)) break;
    }
    return true;
  }

  // &INDEQ StructLocalVariableDefinition
  private static boolean StructMultipleDef_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructMultipleDef_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructMultipleDef_1_0_0(b, l + 1);
    r = r && StructLocalVariableDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean StructMultipleDef_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructMultipleDef_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // &INDNONE StructLocalVariableDefinition
  static boolean StructSingleDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructSingleDef")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructSingleDef_0(b, l + 1);
    r = r && StructLocalVariableDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StructSingleDef_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructSingleDef_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Identifier
  public static boolean StructType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructType")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, STRUCT_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // ListType | MapType | ValueType | StructType
  public static boolean TYPE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TYPE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE, "<type>");
    r = ListType(b, l + 1);
    if (!r) r = MapType(b, l + 1);
    if (!r) r = ValueType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // stringLiteralSingle | stringLiteralDouble
  public static boolean UNIQUE_KEY(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UNIQUE_KEY")) return false;
    if (!nextTokenIs(b, "<unique key>", STRINGLITERALDOUBLE, STRINGLITERALSINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIQUE_KEY, "<unique key>");
    r = consumeToken(b, STRINGLITERALSINGLE);
    if (!r) r = consumeToken(b, STRINGLITERALDOUBLE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Identifier &INDNONE ':' &INDNONE constant &INDNONE'(' TYPE ')' &INDNONE'=' &INDNONE Expression
  public static boolean UserDefinedConstantsExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, USER_DEFINED_CONSTANTS_EXPRESSION, null);
    r = consumeToken(b, IDENTIFIER);
    r = r && UserDefinedConstantsExpression_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && UserDefinedConstantsExpression_3(b, l + 1);
    r = r && consumeToken(b, CONSTANT);
    p = r; // pin = 5
    r = r && report_error_(b, UserDefinedConstantsExpression_5(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LPAREN)) && r;
    r = p && report_error_(b, TYPE(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && report_error_(b, UserDefinedConstantsExpression_9(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, ASSIGN)) && r;
    r = p && report_error_(b, UserDefinedConstantsExpression_11(b, l + 1)) && r;
    r = p && Expression(b, l + 1, -1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean UserDefinedConstantsExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean UserDefinedConstantsExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean UserDefinedConstantsExpression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean UserDefinedConstantsExpression_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression_9")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean UserDefinedConstantsExpression_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UserDefinedConstantsExpression_11")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // intM | uintM | bytesM | address | bool | ((string | bytes) &INDNONE '[' ( DecimalNumber | Identifier ) ']')
  public static boolean ValueType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_TYPE, "<value type>");
    r = consumeToken(b, INTM);
    if (!r) r = consumeToken(b, UINTM);
    if (!r) r = consumeToken(b, BYTESM);
    if (!r) r = consumeToken(b, ADDRESS);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = ValueType_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (string | bytes) &INDNONE '[' ( DecimalNumber | Identifier ) ']'
  private static boolean ValueType_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ValueType_5_0(b, l + 1);
    r = r && ValueType_5_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ValueType_5_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // string | bytes
  private static boolean ValueType_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_5_0")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, BYTES);
    return r;
  }

  // &INDNONE
  private static boolean ValueType_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_5_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DecimalNumber | Identifier
  private static boolean ValueType_5_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_5_3")) return false;
    boolean r;
    r = DecimalNumber(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // Identifier
  public static boolean VarLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarLiteral")) return false;
    if (!nextTokenIs(b, "<variable>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_LITERAL, "<variable>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ((!<<eof>> MultiLineString? SourceUnit) (&INDEQ SourceUnit)*)?
  static boolean VyperFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile")) return false;
    VyperFile_0(b, l + 1);
    return true;
  }

  // (!<<eof>> MultiLineString? SourceUnit) (&INDEQ SourceUnit)*
  private static boolean VyperFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VyperFile_0_0(b, l + 1);
    r = r && VyperFile_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>> MultiLineString? SourceUnit
  private static boolean VyperFile_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VyperFile_0_0_0(b, l + 1);
    r = r && VyperFile_0_0_1(b, l + 1);
    r = r && SourceUnit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>>
  private static boolean VyperFile_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // MultiLineString?
  private static boolean VyperFile_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_0_1")) return false;
    MultiLineString(b, l + 1);
    return true;
  }

  // (&INDEQ SourceUnit)*
  private static boolean VyperFile_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VyperFile_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VyperFile_0_1", c)) break;
    }
    return true;
  }

  // &INDEQ SourceUnit
  private static boolean VyperFile_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VyperFile_0_1_0_0(b, l + 1);
    r = r && SourceUnit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean VyperFile_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(&INDEQ|&INDLT)
  static boolean recoverStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverStatement_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDEQ|&INDLT
  private static boolean recoverStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = recoverStatement_0_0(b, l + 1);
    if (!r) r = recoverStatement_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean recoverStatement_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStatement_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDLT
  private static boolean recoverStatement_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStatement_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indLt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(&INDEQ | &INDLT |<<eof>>)
  static boolean structRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !structRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDEQ | &INDLT |<<eof>>
  private static boolean structRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = structRecover_0_0(b, l + 1);
    if (!r) r = structRecover_0_1(b, l + 1);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean structRecover_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structRecover_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDLT
  private static boolean structRecover_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structRecover_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indLt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: BINARY(TernaryExpression)
  // 1: BINARY(AssignmentExpression)
  // 2: PREFIX(TupleAssignmentExpression)
  // 3: ATOM(RangeExpression)
  // 4: ATOM(ParenthesiziedExpression)
  // 5: POSTFIX(CallExpression)
  // 6: ATOM(NewExpression)
  // 7: ATOM(AssertExpression)
  // 8: BINARY(EqExpression)
  // 9: BINARY(OrExpression)
  // 10: BINARY(AndExpression)
  // 11: BINARY(CompExpression)
  // 12: BINARY(PlusMinExpression)
  // 13: BINARY(MultDivExpression)
  // 14: BINARY(ExponentExpression)
  // 15: BINARY(BinExpression)
  // 16: PREFIX(UnaryExpression)
  // 17: ATOM(ClearExpression)
  // 18: BINARY(IndexAccessExpression)
  // 19: POSTFIX(MemberAccessExpression)
  // 20: BINARY(MemberIndexAccess)
  // 21: ATOM(InlineArrayExpression)
  // 22: ATOM(PrimaryExpression)
  // 23: ATOM(EventLogExpression)
  // 24: BINARY(InExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = TupleAssignmentExpression(b, l + 1);
    if (!r) r = RangeExpression(b, l + 1);
    if (!r) r = ParenthesiziedExpression(b, l + 1);
    if (!r) r = NewExpression(b, l + 1);
    if (!r) r = AssertExpression(b, l + 1);
    if (!r) r = UnaryExpression(b, l + 1);
    if (!r) r = ClearExpression(b, l + 1);
    if (!r) r = InlineArrayExpression(b, l + 1);
    if (!r) r = PrimaryExpression(b, l + 1);
    if (!r) r = EventLogExpression(b, l + 1);
    p = r;
    r = r && Expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && TernaryExpression_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 0));
        r = TernaryExpression_1(b, l + 1) && r;
        exit_section_(b, l, m, TERNARY_EXPRESSION, r, true, null);
      }
      else if (g < 1 && AssignmentExpression_0(b, l + 1)) {
        r = Expression(b, l, 0);
        exit_section_(b, l, m, ASSIGNMENT_EXPRESSION, r, true, null);
      }
      else if (g < 5 && CallExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CALL_EXPRESSION, r, true, null);
      }
      else if (g < 8 && EqExpression_0(b, l + 1)) {
        r = Expression(b, l, 8);
        exit_section_(b, l, m, EQ_EXPRESSION, r, true, null);
      }
      else if (g < 9 && consumeTokenSmart(b, OR)) {
        r = Expression(b, l, 9);
        exit_section_(b, l, m, OR_EXPRESSION, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, AND)) {
        r = Expression(b, l, 10);
        exit_section_(b, l, m, AND_EXPRESSION, r, true, null);
      }
      else if (g < 11 && CompExpression_0(b, l + 1)) {
        r = Expression(b, l, 11);
        exit_section_(b, l, m, COMP_EXPRESSION, r, true, null);
      }
      else if (g < 12 && PlusMinExpression_0(b, l + 1)) {
        r = Expression(b, l, 12);
        exit_section_(b, l, m, PLUS_MIN_EXPRESSION, r, true, null);
      }
      else if (g < 13 && MultDivExpression_0(b, l + 1)) {
        r = Expression(b, l, 13);
        exit_section_(b, l, m, MULT_DIV_EXPRESSION, r, true, null);
      }
      else if (g < 14 && consumeTokenSmart(b, EXPONENT)) {
        r = Expression(b, l, 14);
        exit_section_(b, l, m, EXPONENT_EXPRESSION, r, true, null);
      }
      else if (g < 15 && BinExpression_0(b, l + 1)) {
        r = Expression(b, l, 15);
        exit_section_(b, l, m, BIN_EXPRESSION, r, true, null);
      }
      else if (g < 18 && IndexAccessExpression_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 18));
        r = consumeToken(b, RBRACKET) && r;
        exit_section_(b, l, m, INDEX_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 19 && MemberAccessExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 20 && MemberIndexAccess_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 20));
        r = MemberIndexAccess_1(b, l + 1) && r;
        exit_section_(b, l, m, MEMBER_INDEX_ACCESS, r, true, null);
      }
      else if (g < 24 && InExpression_0(b, l + 1)) {
        r = Expression(b, l, 24);
        exit_section_(b, l, m, IN_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // &INDNONE if &INDNONE
  private static boolean TernaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TernaryExpression_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, IF);
    r = r && TernaryExpression_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean TernaryExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean TernaryExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE else &INDNONE Expression
  private static boolean TernaryExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TernaryExpression_1_0(b, l + 1);
    r = r && consumeToken(b, ELSE);
    r = r && TernaryExpression_1_2(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean TernaryExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean TernaryExpression_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TernaryExpression_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE ('=' | '+=' | '-=' | '*=' | '/=' | '%=')
  private static boolean AssignmentExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssignmentExpression_0_0(b, l + 1);
    r = r && AssignmentExpression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean AssignmentExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '=' | '+=' | '-=' | '*=' | '/=' | '%='
  private static boolean AssignmentExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentExpression_0_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, ASSIGN);
    if (!r) r = consumeTokenSmart(b, PLUS_ASSIGN);
    if (!r) r = consumeTokenSmart(b, MINUS_ASSIGN);
    if (!r) r = consumeTokenSmart(b, MULT_ASSIGN);
    if (!r) r = consumeTokenSmart(b, DIV_ASSIGN);
    if (!r) r = consumeTokenSmart(b, PERCENT_ASSIGN);
    return r;
  }

  public static boolean TupleAssignmentExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = TupleAssignmentExpression_0(b, l + 1);
    p = r;
    r = p && Expression(b, l, 2);
    exit_section_(b, l, m, TUPLE_ASSIGNMENT_EXPRESSION, r, p, null);
    return r || p;
  }

  // Identifier (',' Identifier)+ &INDNONE '='
  private static boolean TupleAssignmentExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    r = r && TupleAssignmentExpression_0_1(b, l + 1);
    r = r && TupleAssignmentExpression_0_2(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' Identifier)+
  private static boolean TupleAssignmentExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TupleAssignmentExpression_0_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!TupleAssignmentExpression_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TupleAssignmentExpression_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ',' Identifier
  private static boolean TupleAssignmentExpression_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean TupleAssignmentExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // range &INDNONE '(' RangeInterval (',' 'bound' '=' Identifier)? ')'
  public static boolean RangeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression")) return false;
    if (!nextTokenIsSmart(b, RANGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, RANGE);
    r = r && RangeExpression_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && RangeInterval(b, l + 1);
    r = r && RangeExpression_4(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, RANGE_EXPRESSION, r);
    return r;
  }

  // &INDNONE
  private static boolean RangeExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' 'bound' '=' Identifier)?
  private static boolean RangeExpression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_4")) return false;
    RangeExpression_4_0(b, l + 1);
    return true;
  }

  // ',' 'bound' '=' Identifier
  private static boolean RangeExpression_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && consumeToken(b, "bound");
    r = r && consumeTokensSmart(b, 0, ASSIGN, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // '(' Expression ')'
  public static boolean ParenthesiziedExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesiziedExpression")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIZIED_EXPRESSION, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ( &INDNONE '[' Expression ']'  )* &INDNONE FunctionCallArguments (&INDNONE FunctionCallArguments)*
  private static boolean CallExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_0(b, l + 1);
    r = r && CallExpression_0_1(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    r = r && CallExpression_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( &INDNONE '[' Expression ']'  )*
  private static boolean CallExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CallExpression_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CallExpression_0_0", c)) break;
    }
    return true;
  }

  // &INDNONE '[' Expression ']'
  private static boolean CallExpression_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_0_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CallExpression_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean CallExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE FunctionCallArguments)*
  private static boolean CallExpression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CallExpression_0_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CallExpression_0_3", c)) break;
    }
    return true;
  }

  // &INDNONE FunctionCallArguments
  private static boolean CallExpression_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_3_0_0(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CallExpression_0_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier (&INDNONE'(') '{' (  ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression)
  //                              (',' ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression))*)? '}' ')'
  public static boolean NewExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    r = r && NewExpression_1(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && NewExpression_3(b, l + 1);
    r = r && consumeTokensSmart(b, 0, RBRACE, RPAREN);
    exit_section_(b, m, NEW_EXPRESSION, r);
    return r;
  }

  // &INDNONE'('
  private static boolean NewExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NewExpression_1_0(b, l + 1);
    r = r && consumeTokenSmart(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean NewExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (  ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression)
  //                              (',' ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression))*)?
  private static boolean NewExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3")) return false;
    NewExpression_3_0(b, l + 1);
    return true;
  }

  // ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression)
  //                              (',' ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression))*
  private static boolean NewExpression_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NewExpression_3_0_0(b, l + 1);
    r = r && NewExpression_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Identifier &INDNONE ':' &INDNONE Expression) | NewExpression
  private static boolean NewExpression_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NewExpression_3_0_0_0(b, l + 1);
    if (!r) r = NewExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier &INDNONE ':' &INDNONE Expression
  private static boolean NewExpression_3_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    r = r && NewExpression_3_0_0_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && NewExpression_3_0_0_0_3(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean NewExpression_3_0_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean NewExpression_3_0_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_0_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression))*
  private static boolean NewExpression_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!NewExpression_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NewExpression_3_0_1", c)) break;
    }
    return true;
  }

  // ',' ((Identifier &INDNONE ':' &INDNONE Expression) | NewExpression)
  private static boolean NewExpression_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && NewExpression_3_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Identifier &INDNONE ':' &INDNONE Expression) | NewExpression
  private static boolean NewExpression_3_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NewExpression_3_0_1_0_1_0(b, l + 1);
    if (!r) r = NewExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier &INDNONE ':' &INDNONE Expression
  private static boolean NewExpression_3_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    r = r && NewExpression_3_0_1_0_1_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && NewExpression_3_0_1_0_1_0_3(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean NewExpression_3_0_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean NewExpression_3_0_1_0_1_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_3_0_1_0_1_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // assert &INDNONE Expression (&INDNONE AssertMessage)?
  public static boolean AssertExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertExpression")) return false;
    if (!nextTokenIsSmart(b, ASSERT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ASSERT_EXPRESSION, "<assert expression>");
    r = consumeTokenSmart(b, ASSERT);
    p = r; // pin = 1
    r = r && report_error_(b, AssertExpression_1(b, l + 1));
    r = p && report_error_(b, Expression(b, l + 1, -1)) && r;
    r = p && AssertExpression_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean AssertExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE AssertMessage)?
  private static boolean AssertExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertExpression_3")) return false;
    AssertExpression_3_0(b, l + 1);
    return true;
  }

  // &INDNONE AssertMessage
  private static boolean AssertExpression_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertExpression_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssertExpression_3_0_0(b, l + 1);
    r = r && AssertMessage(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean AssertExpression_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertExpression_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '==' | '!='
  private static boolean EqExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EqExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, EQ);
    if (!r) r = consumeTokenSmart(b, NEQ);
    return r;
  }

  // '<' | '>' | '<=' | '>='
  private static boolean CompExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, LESS);
    if (!r) r = consumeTokenSmart(b, MORE);
    if (!r) r = consumeTokenSmart(b, LESSEQ);
    if (!r) r = consumeTokenSmart(b, MOREEQ);
    return r;
  }

  // '+' | '-'
  private static boolean PlusMinExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PlusMinExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, PLUS);
    if (!r) r = consumeTokenSmart(b, MINUS);
    return r;
  }

  // '*' | '/' | '%'
  private static boolean MultDivExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultDivExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, MULT);
    if (!r) r = consumeTokenSmart(b, DIV);
    if (!r) r = consumeTokenSmart(b, PERCENT);
    return r;
  }

  // UNION | INTERSECTION | RSHIFT | LSHIFT
  private static boolean BinExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, UNION);
    if (!r) r = consumeTokenSmart(b, INTERSECTION);
    if (!r) r = consumeTokenSmart(b, RSHIFT);
    if (!r) r = consumeTokenSmart(b, LSHIFT);
    return r;
  }

  public static boolean UnaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = UnaryExpression_0(b, l + 1);
    p = r;
    r = p && Expression(b, l, 16);
    exit_section_(b, l, m, UNARY_EXPRESSION, r, p, null);
    return r || p;
  }

  // (NOT | '~'  |  '+' | '-') &INDNONE
  private static boolean UnaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnaryExpression_0_0(b, l + 1);
    r = r && UnaryExpression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NOT | '~'  |  '+' | '-'
  private static boolean UnaryExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression_0_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, NOT);
    if (!r) r = consumeTokenSmart(b, TILDE);
    if (!r) r = consumeTokenSmart(b, PLUS);
    if (!r) r = consumeTokenSmart(b, MINUS);
    return r;
  }

  // &INDNONE
  private static boolean UnaryExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // clear &INDNONE '(' Expression ')'
  public static boolean ClearExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClearExpression")) return false;
    if (!nextTokenIsSmart(b, CLEAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLEAR_EXPRESSION, null);
    r = consumeTokenSmart(b, CLEAR);
    p = r; // pin = 1
    r = r && report_error_(b, ClearExpression_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LPAREN)) && r;
    r = p && report_error_(b, Expression(b, l + 1, -1)) && r;
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ClearExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClearExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '['
  private static boolean IndexAccessExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexAccessExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IndexAccessExpression_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, LBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean IndexAccessExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexAccessExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '.' (&INDNONE VarLiteral)
  private static boolean MemberAccessExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberAccessExpression_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, DOT);
    r = r && MemberAccessExpression_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MemberAccessExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE VarLiteral
  private static boolean MemberAccessExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberAccessExpression_0_2_0(b, l + 1);
    r = r && VarLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MemberAccessExpression_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '.'
  private static boolean MemberIndexAccess_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberIndexAccess_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberIndexAccess_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MemberIndexAccess_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberIndexAccess_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '[' Expression ']'
  private static boolean MemberIndexAccess_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberIndexAccess_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberIndexAccess_1_0(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MemberIndexAccess_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberIndexAccess_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '[' Expression (',' Expression)*  ']'
  public static boolean InlineArrayExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression")) return false;
    if (!nextTokenIsSmart(b, LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INLINE_ARRAY_EXPRESSION, null);
    r = consumeTokenSmart(b, LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && report_error_(b, InlineArrayExpression_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' Expression)*
  private static boolean InlineArrayExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!InlineArrayExpression_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InlineArrayExpression_2", c)) break;
    }
    return true;
  }

  // ',' Expression
  private static boolean InlineArrayExpression_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VarLiteral | BooleanLiteral | NumberLiteral | HexLiteral | StringLiteral
  //     | TYPE
  public static boolean PrimaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPRESSION, "<primary expression>");
    r = VarLiteral(b, l + 1);
    if (!r) r = BooleanLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    if (!r) r = HexLiteral(b, l + 1);
    if (!r) r = StringLiteral(b, l + 1);
    if (!r) r = TYPE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // log &INDNONE Identifier '(' Expression (',' Expression)* ')'
  public static boolean EventLogExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression")) return false;
    if (!nextTokenIsSmart(b, LOG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LOG);
    r = r && EventLogExpression_1(b, l + 1);
    r = r && consumeTokensSmart(b, 0, IDENTIFIER, LPAREN);
    r = r && Expression(b, l + 1, -1);
    r = r && EventLogExpression_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, EVENT_LOG_EXPRESSION, r);
    return r;
  }

  // &INDNONE
  private static boolean EventLogExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' Expression)*
  private static boolean EventLogExpression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EventLogExpression_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EventLogExpression_5", c)) break;
    }
    return true;
  }

  // ',' Expression
  private static boolean EventLogExpression_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE 'in' &INDNONE
  private static boolean InExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InExpression_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, "in");
    r = r && InExpression_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean InExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean InExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
