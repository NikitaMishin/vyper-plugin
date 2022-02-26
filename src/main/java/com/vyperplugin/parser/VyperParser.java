// This is a generated file. Not intended for manual editing.
package com.vyperplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.vyperplugin.psi.VyperTypes.*;
import static com.vyperplugin.parser.ParserUtil.*;
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
    create_token_set_(AND_EXPRESSION, ASSERT_EXPRESSION, ASSIGNMENT_EXPRESSION, CALL_EXPRESSION,
      CLEAR_EXPRESSION, COMP_EXPRESSION, EQ_EXPRESSION, EXPONENT_EXPRESSION,
      EXPRESSION, FUNCTION_CALL_EXPRESSION, INDEX_ACCESS_EXPRESSION, INLINE_ARRAY_EXPRESSION,
      MEMBER_ACCESS_EXPRESSION, MEMBER_INDEX_ACCESS, MULT_DIV_EXPRESSION, NEW_EXPRESSION,
      OR_EXPRESSION, PARENTHESIZIED_EXPRESSION, PLUS_MIN_EXPRESSION, PRIMARY_EXPRESSION,
      RANGE_EXPRESSION, UNARY_EXPRESSION, USER_DEFINED_CONSTANTS_EXPRESSION),
  };

  /* ********************************************************** */
  // ',' (stringLiteralDouble| stringLiteralSingle | MultiLineString)
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

  // stringLiteralDouble| stringLiteralSingle | MultiLineString
  private static boolean AssertMessage_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssertMessage_1")) return false;
    boolean r;
    r = consumeToken(b, STRINGLITERALDOUBLE);
    if (!r) r = consumeToken(b, STRINGLITERALSINGLE);
    if (!r) r = MultiLineString(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Identifier | StringLiteral |BAD_CHARACTER
  public static boolean BadStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BadStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BAD_STATEMENT, "<bad statement>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = StringLiteral(b, l + 1);
    if (!r) r = consumeToken(b, BAD_CHARACTER);
    exit_section_(b, l, m, r, false, null);
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
  // ZERO_ADDRESS | EMPTY_BYTES32 | MAX_INT128 | MIN_INT128
  //              | MAX_DECIMAL | MIN_DECIMAL | MAX_UINT256
  static boolean Constants(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constants")) return false;
    boolean r;
    r = consumeToken(b, ZERO_ADDRESS);
    if (!r) r = consumeToken(b, EMPTY_BYTES32);
    if (!r) r = consumeToken(b, MAX_INT128);
    if (!r) r = consumeToken(b, MIN_INT128);
    if (!r) r = consumeToken(b, MAX_DECIMAL);
    if (!r) r = consumeToken(b, MIN_DECIMAL);
    if (!r) r = consumeToken(b, MAX_UINT256);
    return r;
  }

  /* ********************************************************** */
  // continue
  static boolean Continue(PsiBuilder b, int l) {
    return consumeToken(b, CONTINUE);
  }

  /* ********************************************************** */
  // (int128|uint256) &INDNONE '(' Expression ')'
  public static boolean CustomUnitType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CustomUnitType")) return false;
    if (!nextTokenIs(b, "<custom unit type>", INT128, UINT256)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CUSTOM_UNIT_TYPE, "<custom unit type>");
    r = CustomUnitType_0(b, l + 1);
    r = r && CustomUnitType_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // int128|uint256
  private static boolean CustomUnitType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CustomUnitType_0")) return false;
    boolean r;
    r = consumeToken(b, INT128);
    if (!r) r = consumeToken(b, UINT256);
    return r;
  }

  // &INDNONE
  private static boolean CustomUnitType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CustomUnitType_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
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
  // Identifier &INDNONE ':' &INDNONE event &INDNONE '(' '{'   EventProperty? '}' ')'
  public static boolean EventDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EVENT_DECLARATION, null);
    r = consumeToken(b, IDENTIFIER);
    r = r && EventDeclaration_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && EventDeclaration_3(b, l + 1);
    r = r && consumeToken(b, EVENT);
    p = r; // pin = 5
    r = r && report_error_(b, EventDeclaration_5(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, LPAREN, LBRACE)) && r;
    r = p && report_error_(b, EventDeclaration_8(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, RBRACE, RPAREN)) && r;
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

  // &INDNONE
  private static boolean EventDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // EventProperty?
  private static boolean EventDeclaration_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventDeclaration_8")) return false;
    EventProperty(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Identifier ':'    (IndexedData | TYPE)
  //                 (',' Identifier ':' (IndexedData |TYPE))*
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

  // (',' Identifier ':' (IndexedData |TYPE))*
  private static boolean EventProperty_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EventProperty_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EventProperty_3", c)) break;
    }
    return true;
  }

  // ',' Identifier ':' (IndexedData |TYPE)
  private static boolean EventProperty_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER, COLON);
    r = r && EventProperty_3_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IndexedData |TYPE
  private static boolean EventProperty_3_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventProperty_3_0_3")) return false;
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
  // modifying | constant
  static boolean ExternalFunStatus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalFunStatus")) return false;
    if (!nextTokenIs(b, "", CONSTANT, MODIFYING)) return false;
    boolean r;
    r = consumeToken(b, MODIFYING);
    if (!r) r = consumeToken(b, CONSTANT);
    return r;
  }

  /* ********************************************************** */
  // def &INDNONE Identifier &INDNONE '('
  // ( FunctionArgs)? ')' (&INDNONE '->' &INDNONE TYPE)? &INDNONE ':' &INDNONE ExternalFunStatus
  static boolean ExternalInterfaceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, DEF);
    p = r; // pin = 1
    r = r && report_error_(b, ExternalInterfaceBody_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LPAREN)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_5(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_7(b, l + 1)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_8(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, ExternalInterfaceBody_10(b, l + 1)) && r;
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

  // &INDNONE
  private static boolean ExternalInterfaceBody_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( FunctionArgs)?
  private static boolean ExternalInterfaceBody_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_5")) return false;
    ExternalInterfaceBody_5_0(b, l + 1);
    return true;
  }

  // ( FunctionArgs)
  private static boolean ExternalInterfaceBody_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionArgs(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDNONE '->' &INDNONE TYPE)?
  private static boolean ExternalInterfaceBody_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_7")) return false;
    ExternalInterfaceBody_7_0(b, l + 1);
    return true;
  }

  // &INDNONE '->' &INDNONE TYPE
  private static boolean ExternalInterfaceBody_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ExternalInterfaceBody_7_0_0(b, l + 1);
    r = r && consumeToken(b, "->");
    r = r && ExternalInterfaceBody_7_0_2(b, l + 1);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_7_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_7_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_7_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_7_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_8")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ExternalInterfaceBody_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaceBody_10")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // contract &INDNONE Identifier &INDNONE ':'
  //                         <<indented (ExternalInterfaceBody+) >>
  public static boolean ExternalInterfaces(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalInterfaces")) return false;
    if (!nextTokenIs(b, CONTRACT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTERNAL_INTERFACES, null);
    r = consumeToken(b, CONTRACT);
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
  //                     (&INDNONE Statement
  //                     | <<indented (Statement (&INDEQ Statement)*)>>)
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

  // &INDNONE Statement
  //                     | <<indented (Statement (&INDEQ Statement)*)>>
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
  // (&INDNONE Statement)
  //                     | <<indented ((MultiLineString)? &INDEQ Statement (&INDEQ Statement)*) >>
  static boolean FunBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunBody_0(b, l + 1);
    if (!r) r = indented(b, l + 1, VyperParser::FunBody_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE Statement
  private static boolean FunBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunBody_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunBody_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (MultiLineString)? &INDEQ Statement (&INDEQ Statement)*
  private static boolean FunBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunBody_1_0_0(b, l + 1);
    r = r && FunBody_1_0_1(b, l + 1);
    r = r && Statement(b, l + 1);
    r = r && FunBody_1_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MultiLineString)?
  private static boolean FunBody_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_0")) return false;
    FunBody_1_0_0_0(b, l + 1);
    return true;
  }

  // (MultiLineString)
  private static boolean FunBody_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MultiLineString(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FunBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean FunBody_1_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunBody_1_0_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunBody_1_0_3", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean FunBody_1_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunBody_1_0_3_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FunBody_1_0_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunBody_1_0_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // def (&INDNONE Identifier) (&INDNONE '(') FunctionArgs?  ')' (&INDNONE '->' &INDNONE TYPE)? (&INDNONE ':')
  static boolean FunDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, DEF);
    p = r; // pin = 1
    r = r && report_error_(b, FunDef_1(b, l + 1));
    r = p && report_error_(b, FunDef_2(b, l + 1)) && r;
    r = p && report_error_(b, FunDef_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && report_error_(b, FunDef_5(b, l + 1)) && r;
    r = p && FunDef_6(b, l + 1) && r;
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

  // &INDNONE '('
  private static boolean FunDef_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunDef_2_0(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunctionArgs?
  private static boolean FunDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_3")) return false;
    FunctionArgs(b, l + 1);
    return true;
  }

  // (&INDNONE '->' &INDNONE TYPE)?
  private static boolean FunDef_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_5")) return false;
    FunDef_5_0(b, l + 1);
    return true;
  }

  // &INDNONE '->' &INDNONE TYPE
  private static boolean FunDef_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunDef_5_0_0(b, l + 1);
    r = r && consumeToken(b, "->");
    r = r && FunDef_5_0_2(b, l + 1);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_5_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE ':'
  private static boolean FunDef_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunDef_6_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunDef_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunDef_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ParamDef  (',' ParamDef )*
  public static boolean FunctionArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParamDef(b, l + 1);
    r = r && FunctionArgs_1(b, l + 1);
    exit_section_(b, m, FUNCTION_ARGS, r);
    return r;
  }

  // (',' ParamDef )*
  private static boolean FunctionArgs_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionArgs_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionArgs_1", c)) break;
    }
    return true;
  }

  // ',' ParamDef
  private static boolean FunctionArgs_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ParamDef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression? ( &INDNONE ',' Expression )*
  public static boolean FunctionCallArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_ARGUMENTS, "<function call arguments>");
    r = FunctionCallArguments_0(b, l + 1);
    r = r && FunctionCallArguments_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expression?
  private static boolean FunctionCallArguments_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_0")) return false;
    Expression(b, l + 1, -1);
    return true;
  }

  // ( &INDNONE ',' Expression )*
  private static boolean FunctionCallArguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionCallArguments_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionCallArguments_1", c)) break;
    }
    return true;
  }

  // &INDNONE ',' Expression
  private static boolean FunctionCallArguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallArguments_1_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionCallArguments_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArguments_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Identifier &INDNONE '(' FunctionCallArguments? ')') |
  //                            ( ( PrimaryExpression | NewExpression | TypeName  )
  //                            ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  //                                      &INDNONE '(' FunctionCallArguments? ')' )  {
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

  // Identifier &INDNONE '(' FunctionCallArguments? ')'
  private static boolean FunctionCallExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && FunctionCallExpression_0_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && FunctionCallExpression_0_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
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

  // FunctionCallArguments?
  private static boolean FunctionCallExpression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_0_3")) return false;
    FunctionCallArguments(b, l + 1);
    return true;
  }

  // ( ( PrimaryExpression | NewExpression | TypeName  )
  //                            ( ( &INDNONE '.' Identifier ) | ( &INDNONE '[' Expression? ']' ) )*
  //                                      &INDNONE '(' FunctionCallArguments? ')' )  {
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
  //                                      &INDNONE '(' FunctionCallArguments? ')'
  private static boolean FunctionCallExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCallExpression_1_0_0(b, l + 1);
    r = r && FunctionCallExpression_1_0_1(b, l + 1);
    r = r && FunctionCallExpression_1_0_2(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && FunctionCallExpression_1_0_4(b, l + 1);
    r = r && consumeToken(b, RPAREN);
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

  // FunctionCallArguments?
  private static boolean FunctionCallExpression_1_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallExpression_1_0_4")) return false;
    FunctionCallArguments(b, l + 1);
    return true;
  }

  // {
  //    //implements and mixin
  // }
  private static boolean FunctionCallExpression_1_1(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // FunctionModifier (&INDEQ FunctionModifier)*
  // //    def &INDNONE Identifier &INDNONE '(' FunctionArgs?  ')' (&INDNONE '->' &INDNONE TYPE)? (&INDNONE ':')
  // //    FunBody
  //     FunDef
  //     FunBody
  public static boolean FunctionDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDefinition")) return false;
    if (!nextTokenIs(b, DECORATOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DEFINITION, null);
    r = FunctionModifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, FunctionDefinition_1(b, l + 1));
    r = p && report_error_(b, FunDef(b, l + 1)) && r;
    r = p && FunBody(b, l + 1) && r;
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
  // DECORATOR (&INDNONE (public | internal | view | pure | external | private |constant | payable | (nonreentrant (&INDNONE '(') UNIQUE_KEY ')')))
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

  // &INDNONE (public | internal | view | pure | external | private |constant | payable | (nonreentrant (&INDNONE '(') UNIQUE_KEY ')'))
  private static boolean FunctionModifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionModifier_1_0(b, l + 1);
    r = r && FunctionModifier_1_1(b, l + 1);
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

  // public | internal | view | pure | external | private |constant | payable | (nonreentrant (&INDNONE '(') UNIQUE_KEY ')')
  private static boolean FunctionModifier_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, INTERNAL);
    if (!r) r = consumeToken(b, VIEW);
    if (!r) r = consumeToken(b, PURE);
    if (!r) r = consumeToken(b, EXTERNAL);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, CONSTANT);
    if (!r) r = consumeToken(b, PAYABLE);
    if (!r) r = FunctionModifier_1_1_8(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nonreentrant (&INDNONE '(') UNIQUE_KEY ')'
  private static boolean FunctionModifier_1_1_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1_1_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NONREENTRANT);
    r = r && FunctionModifier_1_1_8_1(b, l + 1);
    r = r && UNIQUE_KEY(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '('
  private static boolean FunctionModifier_1_1_8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1_1_8_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionModifier_1_1_8_1_0(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionModifier_1_1_8_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionModifier_1_1_8_1_0")) return false;
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
  //                    |( from  &INDNONE IMPORT_PATH &INDNONE import &INDNONE Identifier  )
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

  // from  &INDNONE IMPORT_PATH &INDNONE import &INDNONE Identifier
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
  // (UnitType| ValueType|StructType) &INDNONE '[' (DecimalNumber | Identifier) ']'
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

  // UnitType| ValueType|StructType
  private static boolean ListType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_0")) return false;
    boolean r;
    r = UnitType(b, l + 1);
    if (!r) r = ValueType(b, l + 1);
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
  // ( LocalVariableDeclaration (&INDNONE '=') (&INDNONE Expression) ) |
  //                            LocalVariableDeclaration
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
  // (map  &INDNONE '(' (ValueType|UnitType)  ',' (ReferenceType |  UnitType|ValueType| StructType  ) ')') |
  //  (HashMap &INDNONE '[' (ValueType|UnitType) ',' (ReferenceType |  UnitType|ValueType| StructType  ) ']')
  public static boolean MapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType")) return false;
    if (!nextTokenIs(b, "<map type>", HASHMAP, MAP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_TYPE, "<map type>");
    r = MapType_0(b, l + 1);
    if (!r) r = MapType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // map  &INDNONE '(' (ValueType|UnitType)  ',' (ReferenceType |  UnitType|ValueType| StructType  ) ')'
  private static boolean MapType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MAP);
    r = r && MapType_0_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && MapType_0_3(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && MapType_0_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MapType_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ValueType|UnitType
  private static boolean MapType_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_0_3")) return false;
    boolean r;
    r = ValueType(b, l + 1);
    if (!r) r = UnitType(b, l + 1);
    return r;
  }

  // ReferenceType |  UnitType|ValueType| StructType
  private static boolean MapType_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_0_5")) return false;
    boolean r;
    r = ReferenceType(b, l + 1);
    if (!r) r = UnitType(b, l + 1);
    if (!r) r = ValueType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    return r;
  }

  // HashMap &INDNONE '[' (ValueType|UnitType) ',' (ReferenceType |  UnitType|ValueType| StructType  ) ']'
  private static boolean MapType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASHMAP);
    r = r && MapType_1_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && MapType_1_3(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && MapType_1_5(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean MapType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ValueType|UnitType
  private static boolean MapType_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_1_3")) return false;
    boolean r;
    r = ValueType(b, l + 1);
    if (!r) r = UnitType(b, l + 1);
    return r;
  }

  // ReferenceType |  UnitType|ValueType| StructType
  private static boolean MapType_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType_1_5")) return false;
    boolean r;
    r = ReferenceType(b, l + 1);
    if (!r) r = UnitType(b, l + 1);
    if (!r) r = ValueType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
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
  // pass
  static boolean PassStatement(PsiBuilder b, int l) {
    return consumeToken(b, PASS);
  }

  /* ********************************************************** */
  // raise
  static boolean Raise(PsiBuilder b, int l) {
    return consumeToken(b, RAISE);
  }

  /* ********************************************************** */
  // PrimaryExpression ',' PrimaryExpression ',' PrimaryExpression
  //                          | PrimaryExpression ',' PrimaryExpression
  //                          | PrimaryExpression
  static boolean RangeInterval(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RangeInterval_0(b, l + 1);
    if (!r) r = RangeInterval_1(b, l + 1);
    if (!r) r = PrimaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PrimaryExpression ',' PrimaryExpression ',' PrimaryExpression
  private static boolean RangeInterval_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrimaryExpression(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && PrimaryExpression(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && PrimaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PrimaryExpression ',' PrimaryExpression
  private static boolean RangeInterval_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeInterval_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrimaryExpression(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && PrimaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ListType  | MapType
  static boolean ReferenceType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceType")) return false;
    boolean r;
    r = ListType(b, l + 1);
    if (!r) r = MapType(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // return (&INDNONE Expression)?
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

  // (&INDNONE Expression)?
  private static boolean Return_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1")) return false;
    Return_1_0(b, l + 1);
    return true;
  }

  // &INDNONE Expression
  private static boolean Return_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Return_1_0_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
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
  //                         | UnitsDefinition
  //                         | ExternalInterfaces
  //                         | EventDeclaration
  //                         | FunctionDefinition
  //                         | StateVariableDeclaration
  //                         | BadStatement
  static boolean SourceUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SourceUnit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = ImportDirective(b, l + 1);
    if (!r) r = UserDefinedConstantsExpression(b, l + 1);
    if (!r) r = ImplementsDirective(b, l + 1);
    if (!r) r = StructDefinition(b, l + 1);
    if (!r) r = UnitsDefinition(b, l + 1);
    if (!r) r = ExternalInterfaces(b, l + 1);
    if (!r) r = EventDeclaration(b, l + 1);
    if (!r) r = FunctionDefinition(b, l + 1);
    if (!r) r = StateVariableDeclaration(b, l + 1);
    if (!r) r = BadStatement(b, l + 1);
    exit_section_(b, l, m, r, false, VyperParser::structRecover);
    return r;
  }

  /* ********************************************************** */
  // Identifier &INDNONE ':' (  (&INDNONE StateVariableModifier &INDNONE '(' &INDNONE TYPE &INDNONE ')')
  // | (&INDNONE TYPE ))
  public static boolean StateVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && StateVariableDeclaration_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && StateVariableDeclaration_3(b, l + 1);
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

  // (&INDNONE StateVariableModifier &INDNONE '(' &INDNONE TYPE &INDNONE ')')
  // | (&INDNONE TYPE )
  private static boolean StateVariableDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StateVariableDeclaration_3_0(b, l + 1);
    if (!r) r = StateVariableDeclaration_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE StateVariableModifier &INDNONE '(' &INDNONE TYPE &INDNONE ')'
  private static boolean StateVariableDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StateVariableDeclaration_3_0_0(b, l + 1);
    r = r && StateVariableModifier(b, l + 1);
    r = r && StateVariableDeclaration_3_0_2(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && StateVariableDeclaration_3_0_4(b, l + 1);
    r = r && TYPE(b, l + 1);
    r = r && StateVariableDeclaration_3_0_6(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_3_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_0_4")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_3_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_0_6")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE TYPE
  private static boolean StateVariableDeclaration_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StateVariableDeclaration_3_1_0(b, l + 1);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StateVariableDeclaration_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableDeclaration_3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // public | (private)
  public static boolean StateVariableModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableModifier")) return false;
    if (!nextTokenIs(b, "<state variable modifier>", PRIVATE, PUBLIC)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATE_VARIABLE_MODIFIER, "<state variable modifier>");
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, PRIVATE);
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
  //               | BadStatement
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
    if (!r) r = BadStatement(b, l + 1);
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
  // LocalVariableDefinition | BadStatement
  static boolean StructLocalVariableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructLocalVariableDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = LocalVariableDefinition(b, l + 1);
    if (!r) r = BadStatement(b, l + 1);
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
  // ReferenceType
  //           | UnitType
  //           | ValueType
  //           | StructType
  public static boolean TYPE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TYPE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE, "<type>");
    r = ReferenceType(b, l + 1);
    if (!r) r = UnitType(b, l + 1);
    if (!r) r = ValueType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'timedelta'
  static boolean Timedelta(PsiBuilder b, int l) {
    return consumeToken(b, "timedelta");
  }

  /* ********************************************************** */
  // 'timestamp'
  static boolean Timestamp(PsiBuilder b, int l) {
    return consumeToken(b, "timestamp");
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
  // Timestamp | Timedelta | Wei_value | CustomUnitType
  public static boolean UnitType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_TYPE, "<unit type>");
    r = Timestamp(b, l + 1);
    if (!r) r = Timedelta(b, l + 1);
    if (!r) r = Wei_value(b, l + 1);
    if (!r) r = CustomUnitType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // UnitsDefinitionPrefix '}'
  public static boolean UnitsDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinition")) return false;
    if (!nextTokenIs(b, UNITS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnitsDefinitionPrefix(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, UNITS_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // units  (&INDNONE ':') (&INDNONE '{')
  //      ( (Identifier ':' StringLiteral) ( ',' Identifier ':' StringLiteral)* )?
  static boolean UnitsDefinitionPrefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, UNITS);
    p = r; // pin = 1
    r = r && report_error_(b, UnitsDefinitionPrefix_1(b, l + 1));
    r = p && report_error_(b, UnitsDefinitionPrefix_2(b, l + 1)) && r;
    r = p && UnitsDefinitionPrefix_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, VyperParser::unitsRecover);
    return r || p;
  }

  // &INDNONE ':'
  private static boolean UnitsDefinitionPrefix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnitsDefinitionPrefix_1_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean UnitsDefinitionPrefix_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE '{'
  private static boolean UnitsDefinitionPrefix_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnitsDefinitionPrefix_2_0(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean UnitsDefinitionPrefix_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( (Identifier ':' StringLiteral) ( ',' Identifier ':' StringLiteral)* )?
  private static boolean UnitsDefinitionPrefix_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_3")) return false;
    UnitsDefinitionPrefix_3_0(b, l + 1);
    return true;
  }

  // (Identifier ':' StringLiteral) ( ',' Identifier ':' StringLiteral)*
  private static boolean UnitsDefinitionPrefix_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnitsDefinitionPrefix_3_0_0(b, l + 1);
    r = r && UnitsDefinitionPrefix_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier ':' StringLiteral
  private static boolean UnitsDefinitionPrefix_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( ',' Identifier ':' StringLiteral)*
  private static boolean UnitsDefinitionPrefix_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!UnitsDefinitionPrefix_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "UnitsDefinitionPrefix_3_0_1", c)) break;
    }
    return true;
  }

  // ',' Identifier ':' StringLiteral
  private static boolean UnitsDefinitionPrefix_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnitsDefinitionPrefix_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER, COLON);
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
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
  // int128 | uint256| bytes32 | address
  //              | (bytes32 | bytes) &INDNONE '[' (DecimalNumber | Identifier) ']' | fixed | bool |
  //                 string   &INDNONE '[' (DecimalNumber | Identifier) ']'
  public static boolean ValueType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_TYPE, "<value type>");
    r = consumeToken(b, INT128);
    if (!r) r = consumeToken(b, UINT256);
    if (!r) r = consumeToken(b, BYTES32);
    if (!r) r = consumeToken(b, ADDRESS);
    if (!r) r = ValueType_4(b, l + 1);
    if (!r) r = consumeToken(b, FIXED);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = ValueType_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (bytes32 | bytes) &INDNONE '[' (DecimalNumber | Identifier) ']'
  private static boolean ValueType_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ValueType_4_0(b, l + 1);
    r = r && ValueType_4_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ValueType_4_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // bytes32 | bytes
  private static boolean ValueType_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_4_0")) return false;
    boolean r;
    r = consumeToken(b, BYTES32);
    if (!r) r = consumeToken(b, BYTES);
    return r;
  }

  // &INDNONE
  private static boolean ValueType_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_4_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DecimalNumber | Identifier
  private static boolean ValueType_4_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_4_3")) return false;
    boolean r;
    r = DecimalNumber(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  // string   &INDNONE '[' (DecimalNumber | Identifier) ']'
  private static boolean ValueType_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    r = r && ValueType_7_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ValueType_7_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ValueType_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_7_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DecimalNumber | Identifier
  private static boolean ValueType_7_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_7_3")) return false;
    boolean r;
    r = DecimalNumber(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // Identifier
  public static boolean VarLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarLiteral")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, VAR_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // ((!<<eof>> SourceUnit) (&INDEQ SourceUnit)*)?
  static boolean VyperFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile")) return false;
    VyperFile_0(b, l + 1);
    return true;
  }

  // (!<<eof>> SourceUnit) (&INDEQ SourceUnit)*
  private static boolean VyperFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VyperFile_0_0(b, l + 1);
    r = r && VyperFile_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>> SourceUnit
  private static boolean VyperFile_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VyperFile_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VyperFile_0_0_0(b, l + 1);
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
  // 'wei_value'
  static boolean Wei_value(PsiBuilder b, int l) {
    return consumeToken(b, "wei_value");
  }

  /* ********************************************************** */
  // !(&INDLT)
  static boolean recoverINDLT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverINDLT")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverINDLT_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDLT
  private static boolean recoverINDLT_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverINDLT_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indLt(b, l + 1);
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
  // !(&INDLT | &INDEQ)
  static boolean recoverStruct(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStruct")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverStruct_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDLT | &INDEQ
  private static boolean recoverStruct_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStruct_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = recoverStruct_0_0(b, l + 1);
    if (!r) r = recoverStruct_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDLT
  private static boolean recoverStruct_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStruct_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indLt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDEQ
  private static boolean recoverStruct_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverStruct_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
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
  // !('}')
  static boolean unitsRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unitsRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, RBRACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: BINARY(AssignmentExpression)
  // 1: ATOM(RangeExpression)
  // 2: ATOM(ParenthesiziedExpression)
  // 3: POSTFIX(CallExpression)
  // 4: ATOM(NewExpression)
  // 5: ATOM(AssertExpression)
  // 6: BINARY(EqExpression)
  // 7: BINARY(OrExpression)
  // 8: BINARY(AndExpression)
  // 9: BINARY(CompExpression)
  // 10: BINARY(PlusMinExpression)
  // 11: BINARY(MultDivExpression)
  // 12: BINARY(ExponentExpression)
  // 13: PREFIX(UnaryExpression)
  // 14: ATOM(ClearExpression)
  // 15: BINARY(IndexAccessExpression)
  // 16: POSTFIX(MemberAccessExpression)
  // 17: BINARY(MemberIndexAccess)
  // 18: ATOM(InlineArrayExpression)
  // 19: ATOM(PrimaryExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = RangeExpression(b, l + 1);
    if (!r) r = ParenthesiziedExpression(b, l + 1);
    if (!r) r = NewExpression(b, l + 1);
    if (!r) r = AssertExpression(b, l + 1);
    if (!r) r = UnaryExpression(b, l + 1);
    if (!r) r = ClearExpression(b, l + 1);
    if (!r) r = InlineArrayExpression(b, l + 1);
    if (!r) r = PrimaryExpression(b, l + 1);
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
      if (g < 0 && AssignmentExpression_0(b, l + 1)) {
        r = Expression(b, l, -1);
        exit_section_(b, l, m, ASSIGNMENT_EXPRESSION, r, true, null);
      }
      else if (g < 3 && CallExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CALL_EXPRESSION, r, true, null);
      }
      else if (g < 6 && EqExpression_0(b, l + 1)) {
        r = Expression(b, l, 6);
        exit_section_(b, l, m, EQ_EXPRESSION, r, true, null);
      }
      else if (g < 7 && consumeTokenSmart(b, OR)) {
        r = Expression(b, l, 7);
        exit_section_(b, l, m, OR_EXPRESSION, r, true, null);
      }
      else if (g < 8 && consumeTokenSmart(b, AND)) {
        r = Expression(b, l, 8);
        exit_section_(b, l, m, AND_EXPRESSION, r, true, null);
      }
      else if (g < 9 && CompExpression_0(b, l + 1)) {
        r = Expression(b, l, 9);
        exit_section_(b, l, m, COMP_EXPRESSION, r, true, null);
      }
      else if (g < 10 && PlusMinExpression_0(b, l + 1)) {
        r = Expression(b, l, 10);
        exit_section_(b, l, m, PLUS_MIN_EXPRESSION, r, true, null);
      }
      else if (g < 11 && MultDivExpression_0(b, l + 1)) {
        r = Expression(b, l, 11);
        exit_section_(b, l, m, MULT_DIV_EXPRESSION, r, true, null);
      }
      else if (g < 12 && consumeTokenSmart(b, EXPONENT)) {
        r = Expression(b, l, 12);
        exit_section_(b, l, m, EXPONENT_EXPRESSION, r, true, null);
      }
      else if (g < 15 && IndexAccessExpression_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 15));
        r = consumeToken(b, RBRACKET) && r;
        exit_section_(b, l, m, INDEX_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 16 && MemberAccessExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 17 && MemberIndexAccess_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 17));
        r = MemberIndexAccess_1(b, l + 1) && r;
        exit_section_(b, l, m, MEMBER_INDEX_ACCESS, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
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

  // range &INDNONE '(' (Expression | RangeInterval ) ')'
  public static boolean RangeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression")) return false;
    if (!nextTokenIsSmart(b, RANGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, RANGE);
    r = r && RangeExpression_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && RangeExpression_3(b, l + 1);
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

  // Expression | RangeInterval
  private static boolean RangeExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_3")) return false;
    boolean r;
    r = Expression(b, l + 1, -1);
    if (!r) r = RangeInterval(b, l + 1);
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

  // (   &INDNONE '[' Expression ']'  )* &INDNONE '(' FunctionCallArguments? ')' (&INDNONE '(' FunctionCallArguments? ')')*
  private static boolean CallExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_0(b, l + 1);
    r = r && CallExpression_0_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && CallExpression_0_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && CallExpression_0_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (   &INDNONE '[' Expression ']'  )*
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

  // FunctionCallArguments?
  private static boolean CallExpression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_3")) return false;
    FunctionCallArguments(b, l + 1);
    return true;
  }

  // (&INDNONE '(' FunctionCallArguments? ')')*
  private static boolean CallExpression_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CallExpression_0_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CallExpression_0_5", c)) break;
    }
    return true;
  }

  // &INDNONE '(' FunctionCallArguments? ')'
  private static boolean CallExpression_0_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_5_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, LPAREN);
    r = r && CallExpression_0_5_0_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CallExpression_0_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunctionCallArguments?
  private static boolean CallExpression_0_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_5_0_2")) return false;
    FunctionCallArguments(b, l + 1);
    return true;
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
    Marker m = enter_section_(b, l, _NONE_, ASSERT_EXPRESSION, null);
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

  public static boolean UnaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = UnaryExpression_0(b, l + 1);
    p = r;
    r = p && Expression(b, l, 13);
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

  // VarLiteral
  //                   |BooleanLiteral
  //                   | NumberLiteral
  //                   | HexLiteral
  //                   | StringLiteral
  //                   | Constants
  //                   //type??
  //                   | TYPE
  public static boolean PrimaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPRESSION, "<primary expression>");
    r = VarLiteral(b, l + 1);
    if (!r) r = BooleanLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
    if (!r) r = HexLiteral(b, l + 1);
    if (!r) r = StringLiteral(b, l + 1);
    if (!r) r = Constants(b, l + 1);
    if (!r) r = TYPE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
