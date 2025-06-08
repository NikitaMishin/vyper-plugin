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
public class BaseVyperParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
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
      CALL_EXPRESSION, COMP_EXPRESSION, CONSTANT_DEFINITION_EXPRESSION, EQ_EXPRESSION,
      EVENT_LOG_EXPRESSION, EXPONENT_EXPRESSION, EXPRESSION, EXT_CALL_EXPRESSION,
      IMMUTABLE_DEFINITION_EXPRESSION, INDEX_ACCESS_EXPRESSION, INLINE_ARRAY_EXPRESSION, IN_EXPRESSION,
      MEMBER_ACCESS_EXPRESSION, MULT_DIV_EXPRESSION, OR_EXPRESSION, PARENTHESIZED_EXPRESSION,
      PLUS_MIN_EXPRESSION, PRIMARY_EXPRESSION, RANGE_EXPRESSION, STATIC_CALL_EXPRESSION,
      STRUCT_EXPRESSION, TERNARY_EXPRESSION, TUPLE_ASSIGNMENT_EXPRESSION, UNARY_EXPRESSION),
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
    if (!r) r = indented(b, l + 1, BaseVyperParser::CondStmt_3_1_0);
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
  // Identifier &INDNONE ':' ConstantType &INDNONE '=' &INDNONE Expression
  public static boolean ConstantDefinitionExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantDefinitionExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT_DEFINITION_EXPRESSION, null);
    r = consumeToken(b, IDENTIFIER);
    r = r && ConstantDefinitionExpression_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && ConstantType(b, l + 1);
    r = r && ConstantDefinitionExpression_4(b, l + 1);
    p = r; // pin = 5
    r = r && report_error_(b, consumeToken(b, ASSIGN));
    r = p && report_error_(b, ConstantDefinitionExpression_6(b, l + 1)) && r;
    r = p && Expression(b, l + 1, -1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean ConstantDefinitionExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantDefinitionExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ConstantDefinitionExpression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantDefinitionExpression_4")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ConstantDefinitionExpression_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantDefinitionExpression_6")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (&INDNONE constant &INDNONE '(' TYPE ')') | (public &INDNONE '(' &INDNONE ConstantType &INDNONE ')')
  static boolean ConstantType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ConstantType_0(b, l + 1);
    if (!r) r = ConstantType_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE constant &INDNONE '(' TYPE ')'
  private static boolean ConstantType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ConstantType_0_0(b, l + 1);
    r = r && consumeToken(b, CONSTANT);
    r = r && ConstantType_0_2(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ConstantType_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ConstantType_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // public &INDNONE '(' &INDNONE ConstantType &INDNONE ')'
  private static boolean ConstantType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUBLIC);
    r = r && ConstantType_1_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && ConstantType_1_3(b, l + 1);
    r = r && ConstantType(b, l + 1);
    r = r && ConstantType_1_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ConstantType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ConstantType_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ConstantType_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantType_1_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
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
  // DynArray &INDNONE '[' TYPE ',' Expression ']'
  static boolean DynArrayType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DynArrayType")) return false;
    if (!nextTokenIs(b, DYNARRAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DYNARRAY);
    r = r && DynArrayType_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean DynArrayType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DynArrayType_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
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
    if (!r) r = indented(b, l + 1, BaseVyperParser::ElseStmt_3_1_0);
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
  // fixedNumber
  static boolean FixedNumber(PsiBuilder b, int l) {
    return consumeToken(b, FIXEDNUMBER);
  }

  /* ********************************************************** */
  // (enum|flag) &INDNONE Identifier &INDNONE COLON  (<<indented FlagOptions >>)
  public static boolean FlagDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagDeclaration")) return false;
    if (!nextTokenIs(b, "<flag declaration>", ENUM, FLAG)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FLAG_DECLARATION, "<flag declaration>");
    r = FlagDeclaration_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, FlagDeclaration_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, FlagDeclaration_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && FlagDeclaration_5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // enum|flag
  private static boolean FlagDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagDeclaration_0")) return false;
    boolean r;
    r = consumeToken(b, ENUM);
    if (!r) r = consumeToken(b, FLAG);
    return r;
  }

  // &INDNONE
  private static boolean FlagDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean FlagDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // <<indented FlagOptions >>
  private static boolean FlagDeclaration_5(PsiBuilder b, int l) {
    return indented(b, l + 1, BaseVyperParser::FlagOptions);
  }

  /* ********************************************************** */
  // Identifier
  public static boolean FlagOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagOption")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLAG_OPTION, "<flag option>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, BaseVyperParser::recoverIndent);
    return r;
  }

  /* ********************************************************** */
  // FlagOption ( &INDEQ FlagOption )*
  static boolean FlagOptions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagOptions")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FlagOption(b, l + 1);
    r = r && FlagOptions_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( &INDEQ FlagOption )*
  private static boolean FlagOptions_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagOptions_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FlagOptions_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FlagOptions_1", c)) break;
    }
    return true;
  }

  // &INDEQ FlagOption
  private static boolean FlagOptions_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagOptions_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FlagOptions_1_0_0(b, l + 1);
    r = r && FlagOption(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean FlagOptions_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlagOptions_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // for &INDNONE Identifier (&INDNONE  ':' TYPE)? &INDNONE in &INDNONE Expression &INDNONE ':'
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
    r = p && report_error_(b, ForStatement_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IN)) && r;
    r = p && report_error_(b, ForStatement_6(b, l + 1)) && r;
    r = p && report_error_(b, Expression(b, l + 1, -1)) && r;
    r = p && report_error_(b, ForStatement_8(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && ForStatement_10(b, l + 1) && r;
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

  // (&INDNONE  ':' TYPE)?
  private static boolean ForStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_3")) return false;
    ForStatement_3_0(b, l + 1);
    return true;
  }

  // &INDNONE  ':' TYPE
  private static boolean ForStatement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_3_0_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && TYPE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_4")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_6")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_8")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE Statement | <<indented (Statement (&INDEQ Statement)*)>>
  private static boolean ForStatement_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_10_0(b, l + 1);
    if (!r) r = indented(b, l + 1, BaseVyperParser::ForStatement_10_1_0);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE Statement
  private static boolean ForStatement_10_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_10_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ForStatement_10_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Statement (&INDEQ Statement)*
  private static boolean ForStatement_10_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    r = r && ForStatement_10_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDEQ Statement)*
  private static boolean ForStatement_10_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ForStatement_10_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ForStatement_10_1_0_1", c)) break;
    }
    return true;
  }

  // &INDEQ Statement
  private static boolean ForStatement_10_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_10_1_0_1_0_0(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean ForStatement_10_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_10_1_0_1_0_0")) return false;
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
  // '(' (ParamDef (',' (ParamDef | &')'))*)? ')'
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

  // (ParamDef (',' (ParamDef | &')'))*)?
  private static boolean FunctionArgs_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1")) return false;
    FunctionArgs_1_0(b, l + 1);
    return true;
  }

  // ParamDef (',' (ParamDef | &')'))*
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

  // (',' (ParamDef | &')'))*
  private static boolean FunctionArgs_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionArgs_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionArgs_1_0_1", c)) break;
    }
    return true;
  }

  // ',' (ParamDef | &')')
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

  // ParamDef | &')'
  private static boolean FunctionArgs_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArgs_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParamDef(b, l + 1);
    if (!r) r = FunctionArgs_1_0_1_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &')'
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
  // Expression | ( VarLiteral '=' Expression )
  public static boolean FunctionCallArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArgument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_ARGUMENT, "<function call argument>");
    r = Expression(b, l + 1, -1);
    if (!r) r = FunctionCallArgument_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VarLiteral '=' Expression
  private static boolean FunctionCallArgument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCallArgument_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarLiteral(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
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
  // FunctionVisibility | FunctionMutability | (nonreentrant ((&INDNONE '(') FunctionEntrancyKey ')')?)
  public static boolean FunctionDecorator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECORATOR, "<function decorator>");
    r = FunctionVisibility(b, l + 1);
    if (!r) r = FunctionMutability(b, l + 1);
    if (!r) r = FunctionDecorator_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // nonreentrant ((&INDNONE '(') FunctionEntrancyKey ')')?
  private static boolean FunctionDecorator_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NONREENTRANT);
    r = r && FunctionDecorator_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((&INDNONE '(') FunctionEntrancyKey ')')?
  private static boolean FunctionDecorator_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_2_1")) return false;
    FunctionDecorator_2_1_0(b, l + 1);
    return true;
  }

  // (&INDNONE '(') FunctionEntrancyKey ')'
  private static boolean FunctionDecorator_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDecorator_2_1_0_0(b, l + 1);
    r = r && FunctionEntrancyKey(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '('
  private static boolean FunctionDecorator_2_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_2_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDecorator_2_1_0_0_0(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean FunctionDecorator_2_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecorator_2_1_0_0_0")) return false;
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
  // stringLiteralSingle | stringLiteralDouble
  public static boolean FunctionEntrancyKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionEntrancyKey")) return false;
    if (!nextTokenIs(b, "<function entrancy key>", STRINGLITERALDOUBLE, STRINGLITERALSINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_ENTRANCY_KEY, "<function entrancy key>");
    r = consumeToken(b, STRINGLITERALSINGLE);
    if (!r) r = consumeToken(b, STRINGLITERALDOUBLE);
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
    if (!r) r = indented(b, l + 1, BaseVyperParser::FunctionImplementation_1_0);
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
  // pure | view | nonpayable | payable
  static boolean FunctionMutability(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionMutability")) return false;
    boolean r;
    r = consumeToken(b, PURE);
    if (!r) r = consumeToken(b, VIEW);
    if (!r) r = consumeToken(b, NONPAYABLE);
    if (!r) r = consumeToken(b, PAYABLE);
    return r;
  }

  /* ********************************************************** */
  // external | internal | deploy
  static boolean FunctionVisibility(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionVisibility")) return false;
    boolean r;
    r = consumeToken(b, EXTERNAL);
    if (!r) r = consumeToken(b, INTERNAL);
    if (!r) r = consumeToken(b, DEPLOY);
    return r;
  }

  /* ********************************************************** */
  // HashMap &INDNONE '[' Expression ',' TYPE ']'
  static boolean HashMapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HashMapType")) return false;
    if (!nextTokenIs(b, HASHMAP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASHMAP);
    r = r && HashMapType_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
    r = r && consumeToken(b, COMMA);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean HashMapType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HashMapType_1")) return false;
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
  // if &INDNONE CondStmt (&INDEQ ElifStmt)* (&INDEQ ElseStmt)?
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
  // Identifier &INDNONE ':' ImmutableType
  public static boolean ImmutableDefinitionExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableDefinitionExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && ImmutableDefinitionExpression_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && ImmutableType(b, l + 1);
    exit_section_(b, m, IMMUTABLE_DEFINITION_EXPRESSION, r);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableDefinitionExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableDefinitionExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (&INDNONE immutable &INDNONE '(' TYPE ')') | (public &INDNONE '(' &INDNONE ImmutableType &INDNONE ')')
  static boolean ImmutableType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImmutableType_0(b, l + 1);
    if (!r) r = ImmutableType_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE immutable &INDNONE '(' TYPE ')'
  private static boolean ImmutableType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImmutableType_0_0(b, l + 1);
    r = r && consumeToken(b, IMMUTABLE);
    r = r && ImmutableType_0_2(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableType_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableType_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // public &INDNONE '(' &INDNONE ImmutableType &INDNONE ')'
  private static boolean ImmutableType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUBLIC);
    r = r && ImmutableType_1_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && ImmutableType_1_3(b, l + 1);
    r = r && ImmutableType(b, l + 1);
    r = r && ImmutableType_1_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableType_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImmutableType_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImmutableType_1_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (from &INDNONE ImportPath import (&INDNONE ImportPath &INDNONE as)? &INDNONE Identifier) |
  //         (import (&INDNONE ImportPath &INDNONE as)? &INDNONE Identifier)
  public static boolean ImportDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective")) return false;
    if (!nextTokenIs(b, "<import directive>", FROM, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_DIRECTIVE, "<import directive>");
    r = ImportDirective_0(b, l + 1);
    if (!r) r = ImportDirective_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // from &INDNONE ImportPath import (&INDNONE ImportPath &INDNONE as)? &INDNONE Identifier
  private static boolean ImportDirective_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FROM);
    r = r && ImportDirective_0_1(b, l + 1);
    r = r && ImportPath(b, l + 1);
    r = r && consumeToken(b, IMPORT);
    r = r && ImportDirective_0_4(b, l + 1);
    r = r && ImportDirective_0_5(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
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

  // (&INDNONE ImportPath &INDNONE as)?
  private static boolean ImportDirective_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_4")) return false;
    ImportDirective_0_4_0(b, l + 1);
    return true;
  }

  // &INDNONE ImportPath &INDNONE as
  private static boolean ImportDirective_0_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDirective_0_4_0_0(b, l + 1);
    r = r && ImportPath(b, l + 1);
    r = r && ImportDirective_0_4_0_2(b, l + 1);
    r = r && consumeToken(b, AS);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_0_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_4_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_0_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_0_4_0_2")) return false;
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

  // import (&INDNONE ImportPath &INDNONE as)? &INDNONE Identifier
  private static boolean ImportDirective_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && ImportDirective_1_1(b, l + 1);
    r = r && ImportDirective_1_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDNONE ImportPath &INDNONE as)?
  private static boolean ImportDirective_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_1")) return false;
    ImportDirective_1_1_0(b, l + 1);
    return true;
  }

  // &INDNONE ImportPath &INDNONE as
  private static boolean ImportDirective_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDirective_1_1_0_0(b, l + 1);
    r = r && ImportPath(b, l + 1);
    r = r && ImportDirective_1_1_0_2(b, l + 1);
    r = r && consumeToken(b, AS);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_1_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_1_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ImportDirective_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDirective_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Identifier? (&INDNONE ('.'|ELLIPSIS) &INDNONE Identifier?)*
  public static boolean ImportPath(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_PATH, "<import path>");
    r = ImportPath_0(b, l + 1);
    r = r && ImportPath_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier?
  private static boolean ImportPath_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_0")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // (&INDNONE ('.'|ELLIPSIS) &INDNONE Identifier?)*
  private static boolean ImportPath_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportPath_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportPath_1", c)) break;
    }
    return true;
  }

  // &INDNONE ('.'|ELLIPSIS) &INDNONE Identifier?
  private static boolean ImportPath_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportPath_1_0_0(b, l + 1);
    r = r && ImportPath_1_0_1(b, l + 1);
    r = r && ImportPath_1_0_2(b, l + 1);
    r = r && ImportPath_1_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ImportPath_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '.'|ELLIPSIS
  private static boolean ImportPath_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1_0_1")) return false;
    boolean r;
    r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, ELLIPSIS);
    return r;
  }

  // &INDNONE
  private static boolean ImportPath_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier?
  private static boolean ImportPath_1_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportPath_1_0_3")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // indexed '(' TYPE ')'
  public static boolean IndexedData(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexedData")) return false;
    if (!nextTokenIs(b, INDEXED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INDEXED, LPAREN);
    r = r && TYPE(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, INDEXED_DATA, r);
    return r;
  }

  /* ********************************************************** */
  // interface &INDNONE Identifier &INDNONE ':'
  //                         <<indented (InterfaceFunction+) >>
  public static boolean InterfaceDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceDeclaration")) return false;
    if (!nextTokenIs(b, INTERFACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_DECLARATION, null);
    r = consumeToken(b, INTERFACE);
    p = r; // pin = 1
    r = r && report_error_(b, InterfaceDeclaration_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, InterfaceDeclaration_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && indented(b, l + 1, BaseVyperParser::InterfaceDeclaration_5_0) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean InterfaceDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean InterfaceDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // InterfaceFunction+
  private static boolean InterfaceDeclaration_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceDeclaration_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InterfaceFunction(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!InterfaceFunction(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InterfaceDeclaration_5_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // def &INDNONE Identifier FunctionArgs FunTypeAnnotation? &INDNONE ':' &INDNONE InterfaceFunctionModifier
  public static boolean InterfaceFunction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunction")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_FUNCTION, null);
    r = consumeToken(b, DEF);
    p = r; // pin = 1
    r = r && report_error_(b, InterfaceFunction_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, FunctionArgs(b, l + 1)) && r;
    r = p && report_error_(b, InterfaceFunction_4(b, l + 1)) && r;
    r = p && report_error_(b, InterfaceFunction_5(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, InterfaceFunction_7(b, l + 1)) && r;
    r = p && InterfaceFunctionModifier(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean InterfaceFunction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunction_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunTypeAnnotation?
  private static boolean InterfaceFunction_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunction_4")) return false;
    FunTypeAnnotation(b, l + 1);
    return true;
  }

  // &INDNONE
  private static boolean InterfaceFunction_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunction_5")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean InterfaceFunction_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunction_7")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // pure | view | nonpayable | payable
  public static boolean InterfaceFunctionModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceFunctionModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_FUNCTION_MODIFIER, "<interface function modifier>");
    r = consumeToken(b, PURE);
    if (!r) r = consumeToken(b, VIEW);
    if (!r) r = consumeToken(b, NONPAYABLE);
    if (!r) r = consumeToken(b, PAYABLE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( ValueType | StructType ) &INDNONE '[' Expression ']' (&INDNONE '[' Expression ']')*
  public static boolean ListType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_TYPE, "<list type>");
    r = ListType_0(b, l + 1);
    r = r && ListType_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
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

  // (&INDNONE '[' Expression ']')*
  private static boolean ListType_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ListType_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ListType_5", c)) break;
    }
    return true;
  }

  // &INDNONE '[' Expression ']'
  private static boolean ListType_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListType_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ListType_5_0_0(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
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

  /* ********************************************************** */
  // Identifier &INDNONE ':' &INDNONE TYPE ((&INDNONE '=') (&INDNONE Expression))?
  public static boolean LocalVariableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && LocalVariableDefinition_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && LocalVariableDefinition_3(b, l + 1);
    r = r && TYPE(b, l + 1);
    r = r && LocalVariableDefinition_5(b, l + 1);
    exit_section_(b, m, LOCAL_VARIABLE_DEFINITION, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((&INDNONE '=') (&INDNONE Expression))?
  private static boolean LocalVariableDefinition_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5")) return false;
    LocalVariableDefinition_5_0(b, l + 1);
    return true;
  }

  // (&INDNONE '=') (&INDNONE Expression)
  private static boolean LocalVariableDefinition_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_5_0_0(b, l + 1);
    r = r && LocalVariableDefinition_5_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE '='
  private static boolean LocalVariableDefinition_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_5_0_0_0(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_5_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE Expression
  private static boolean LocalVariableDefinition_5_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocalVariableDefinition_5_0_1_0(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean LocalVariableDefinition_5_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDefinition_5_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DynArrayType | HashMapType
  public static boolean MapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType")) return false;
    if (!nextTokenIs(b, "<map type>", DYNARRAY, HASHMAP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_TYPE, "<map type>");
    r = DynArrayType(b, l + 1);
    if (!r) r = HashMapType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarLiteral (&INDNONE '.' &INDNONE Identifier)* (&INDNONE '[' &INDNONE VarLiteral &INDNONE ':=' &INDNONE VarLiteral &INDNONE ']')?
  public static boolean ModuleReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarLiteral(b, l + 1);
    r = r && ModuleReference_1(b, l + 1);
    r = r && ModuleReference_2(b, l + 1);
    exit_section_(b, m, MODULE_REFERENCE, r);
    return r;
  }

  // (&INDNONE '.' &INDNONE Identifier)*
  private static boolean ModuleReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ModuleReference_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ModuleReference_1", c)) break;
    }
    return true;
  }

  // &INDNONE '.' &INDNONE Identifier
  private static boolean ModuleReference_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ModuleReference_1_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && ModuleReference_1_0_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE '[' &INDNONE VarLiteral &INDNONE ':=' &INDNONE VarLiteral &INDNONE ']')?
  private static boolean ModuleReference_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2")) return false;
    ModuleReference_2_0(b, l + 1);
    return true;
  }

  // &INDNONE '[' &INDNONE VarLiteral &INDNONE ':=' &INDNONE VarLiteral &INDNONE ']'
  private static boolean ModuleReference_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ModuleReference_2_0_0(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && ModuleReference_2_0_2(b, l + 1);
    r = r && VarLiteral(b, l + 1);
    r = r && ModuleReference_2_0_4(b, l + 1);
    r = r && consumeToken(b, WALRUS);
    r = r && ModuleReference_2_0_6(b, l + 1);
    r = r && VarLiteral(b, l + 1);
    r = r && ModuleReference_2_0_8(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_2_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0_4")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_2_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0_6")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReference_2_0_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReference_2_0_8")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (implements|uses|exports|initializes) &INDNONE ':' (QuotedModuleReferences | UnquotedModuleReferences)
  public static boolean ModuleReferenceDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReferenceDirective")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_REFERENCE_DIRECTIVE, "<module reference directive>");
    r = ModuleReferenceDirective_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, ModuleReferenceDirective_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && ModuleReferenceDirective_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // implements|uses|exports|initializes
  private static boolean ModuleReferenceDirective_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReferenceDirective_0")) return false;
    boolean r;
    r = consumeToken(b, IMPLEMENTS);
    if (!r) r = consumeToken(b, USES);
    if (!r) r = consumeToken(b, EXPORTS);
    if (!r) r = consumeToken(b, INITIALIZES);
    return r;
  }

  // &INDNONE
  private static boolean ModuleReferenceDirective_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReferenceDirective_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // QuotedModuleReferences | UnquotedModuleReferences
  private static boolean ModuleReferenceDirective_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleReferenceDirective_3")) return false;
    boolean r;
    r = QuotedModuleReferences(b, l + 1);
    if (!r) r = UnquotedModuleReferences(b, l + 1);
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
  // HexNumber | DecimalNumber | FixedNumber
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
  // '(' (ModuleReference (',' (ModuleReference | &')'))*)? ')'
  static boolean QuotedModuleReferences(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, QuotedModuleReferences_1(b, l + 1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ModuleReference (',' (ModuleReference | &')'))*)?
  private static boolean QuotedModuleReferences_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1")) return false;
    QuotedModuleReferences_1_0(b, l + 1);
    return true;
  }

  // ModuleReference (',' (ModuleReference | &')'))*
  private static boolean QuotedModuleReferences_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ModuleReference(b, l + 1);
    p = r; // pin = 1
    r = r && QuotedModuleReferences_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (ModuleReference | &')'))*
  private static boolean QuotedModuleReferences_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!QuotedModuleReferences_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "QuotedModuleReferences_1_0_1", c)) break;
    }
    return true;
  }

  // ',' (ModuleReference | &')')
  private static boolean QuotedModuleReferences_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && QuotedModuleReferences_1_0_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ModuleReference | &')'
  private static boolean QuotedModuleReferences_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ModuleReference(b, l + 1);
    if (!r) r = QuotedModuleReferences_1_0_1_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &')'
  private static boolean QuotedModuleReferences_1_0_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotedModuleReferences_1_0_1_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // raise StringLiteral?
  static boolean Raise(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Raise")) return false;
    if (!nextTokenIs(b, RAISE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RAISE);
    r = r && Raise_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // StringLiteral?
  private static boolean Raise_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Raise_1")) return false;
    StringLiteral(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ',' BOUND '=' (VarLiteral | NumberLiteral)
  public static boolean RangeBound(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeBound")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, BOUND, ASSIGN);
    r = r && RangeBound_3(b, l + 1);
    exit_section_(b, m, RANGE_BOUND, r);
    return r;
  }

  // VarLiteral | NumberLiteral
  private static boolean RangeBound_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeBound_3")) return false;
    boolean r;
    r = VarLiteral(b, l + 1);
    if (!r) r = NumberLiteral(b, l + 1);
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
  //                         | ConstantDefinitionExpression
  //                         | ImmutableDefinitionExpression
  //                         | ModuleReferenceDirective
  //                         | StructDeclaration
  //                         | InterfaceDeclaration
  //                         | EventDeclaration
  //                         | FlagDeclaration
  //                         | FunctionDefinition
  //                         | StateVariableDeclaration
  static boolean SourceUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SourceUnit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = ImportDirective(b, l + 1);
    if (!r) r = ConstantDefinitionExpression(b, l + 1);
    if (!r) r = ImmutableDefinitionExpression(b, l + 1);
    if (!r) r = ModuleReferenceDirective(b, l + 1);
    if (!r) r = StructDeclaration(b, l + 1);
    if (!r) r = InterfaceDeclaration(b, l + 1);
    if (!r) r = EventDeclaration(b, l + 1);
    if (!r) r = FlagDeclaration(b, l + 1);
    if (!r) r = FunctionDefinition(b, l + 1);
    if (!r) r = StateVariableDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, BaseVyperParser::recoverIndent);
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
  // TYPE | public &INDNONE '(' &INDNONE StateVariableType &INDNONE ')'
  public static boolean StateVariableType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATE_VARIABLE_TYPE, "<state variable type>");
    r = TYPE(b, l + 1);
    if (!r) r = StateVariableType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // public &INDNONE '(' &INDNONE StateVariableType &INDNONE ')'
  private static boolean StateVariableType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateVariableType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUBLIC);
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
    if (!r) r = SimpleStatement(b, l + 1);
    if (!r) r = MultiLineString(b, l + 1);
    exit_section_(b, l, m, r, false, BaseVyperParser::recoverStatement);
    return r;
  }

  /* ********************************************************** */
  // stringLiteralDouble | stringLiteralDoubleB | stringLiteralSingle | stringLiteralSingleB | MultiLineString
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
    if (!r) r = indented(b, l + 1, BaseVyperParser::StructBody_1_0);
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
  public static boolean StructDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDeclaration")) return false;
    if (!nextTokenIs(b, STRUCT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_DECLARATION, null);
    r = consumeToken(b, STRUCT);
    p = r; // pin = 1
    r = r && report_error_(b, StructDeclaration_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER)) && r;
    r = p && report_error_(b, StructDeclaration_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && StructBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // &INDNONE
  private static boolean StructDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StructDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructDeclaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarLiteral &INDNONE ':' &INDNONE Expression
  public static boolean StructExpressionMember(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMember")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarLiteral(b, l + 1);
    r = r && StructExpressionMember_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && StructExpressionMember_3(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, STRUCT_EXPRESSION_MEMBER, r);
    return r;
  }

  // &INDNONE
  private static boolean StructExpressionMember_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMember_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean StructExpressionMember_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMember_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '{' StructExpressionMember (',' (StructExpressionMember | &'}'))* '}'
  static boolean StructExpressionMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMembers")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, StructExpressionMember(b, l + 1));
    r = p && report_error_(b, StructExpressionMembers_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (StructExpressionMember | &'}'))*
  private static boolean StructExpressionMembers_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMembers_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructExpressionMembers_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructExpressionMembers_2", c)) break;
    }
    return true;
  }

  // ',' (StructExpressionMember | &'}')
  private static boolean StructExpressionMembers_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMembers_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && StructExpressionMembers_2_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // StructExpressionMember | &'}'
  private static boolean StructExpressionMembers_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMembers_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructExpressionMember(b, l + 1);
    if (!r) r = StructExpressionMembers_2_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &'}'
  private static boolean StructExpressionMembers_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpressionMembers_2_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RBRACE);
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
    exit_section_(b, l, m, r, false, BaseVyperParser::recoverIndent);
    return r;
  }

  /* ********************************************************** */
  // StructLocalVariableDefinition ( &INDEQ StructLocalVariableDefinition )*
  static boolean StructMultipleDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructMultipleDef")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructLocalVariableDefinition(b, l + 1);
    r = r && StructMultipleDef_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( &INDEQ StructLocalVariableDefinition )*
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
  // &INDNONE ModuleReference (&INDNONE ',' &INDNONE ModuleReference)*
  static boolean UnquotedModuleReferences(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnquotedModuleReferences_0(b, l + 1);
    r = r && ModuleReference(b, l + 1);
    r = r && UnquotedModuleReferences_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean UnquotedModuleReferences_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (&INDNONE ',' &INDNONE ModuleReference)*
  private static boolean UnquotedModuleReferences_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!UnquotedModuleReferences_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "UnquotedModuleReferences_2", c)) break;
    }
    return true;
  }

  // &INDNONE ',' &INDNONE ModuleReference
  private static boolean UnquotedModuleReferences_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnquotedModuleReferences_2_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && UnquotedModuleReferences_2_0_2(b, l + 1);
    r = r && ModuleReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean UnquotedModuleReferences_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean UnquotedModuleReferences_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnquotedModuleReferences_2_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // intM | uintM | bytesM | address | bool | ((string | bytes) &INDNONE '[' Expression ']')
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

  // (string | bytes) &INDNONE '[' Expression ']'
  private static boolean ValueType_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueType_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ValueType_5_0(b, l + 1);
    r = r && ValueType_5_1(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && Expression(b, l + 1, -1);
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
  // !(&INDEQ | &INDLT |<<eof>>)
  static boolean recoverIndent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverIndent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverIndent_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDEQ | &INDLT |<<eof>>
  private static boolean recoverIndent_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverIndent_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = recoverIndent_0_0(b, l + 1);
    if (!r) r = recoverIndent_0_1(b, l + 1);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDEQ
  private static boolean recoverIndent_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverIndent_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indEq(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDLT
  private static boolean recoverIndent_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverIndent_0_1")) return false;
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
  // Expression root: Expression
  // Operator priority table:
  // 0: BINARY(TernaryExpression)
  // 1: BINARY(AssignmentExpression)
  // 2: PREFIX(TupleAssignmentExpression)
  // 3: ATOM(RangeExpression)
  // 4: BINARY(IndexAccessExpression)
  // 5: ATOM(UnaryExpression)
  // 6: POSTFIX(CallExpression)
  // 7: ATOM(StructExpression)
  // 8: ATOM(AssertExpression)
  // 9: BINARY(EqExpression)
  // 10: BINARY(OrExpression)
  // 11: BINARY(AndExpression)
  // 12: BINARY(CompExpression)
  // 13: BINARY(PlusMinExpression)
  // 14: BINARY(MultDivExpression)
  // 15: BINARY(ExponentExpression)
  // 16: BINARY(BinExpression)
  // 17: POSTFIX(MemberAccessExpression)
  // 18: ATOM(InlineArrayExpression)
  // 19: ATOM(EventLogExpression)
  // 20: PREFIX(ExtCallExpression)
  // 21: PREFIX(StaticCallExpression)
  // 22: BINARY(InExpression)
  // 23: ATOM(PrimaryExpression)
  // 24: ATOM(ParenthesizedExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = TupleAssignmentExpression(b, l + 1);
    if (!r) r = RangeExpression(b, l + 1);
    if (!r) r = UnaryExpression(b, l + 1);
    if (!r) r = StructExpression(b, l + 1);
    if (!r) r = AssertExpression(b, l + 1);
    if (!r) r = InlineArrayExpression(b, l + 1);
    if (!r) r = EventLogExpression(b, l + 1);
    if (!r) r = ExtCallExpression(b, l + 1);
    if (!r) r = StaticCallExpression(b, l + 1);
    if (!r) r = PrimaryExpression(b, l + 1);
    if (!r) r = ParenthesizedExpression(b, l + 1);
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
      else if (g < 4 && IndexAccessExpression_0(b, l + 1)) {
        r = report_error_(b, Expression(b, l, 4));
        r = consumeToken(b, RBRACKET) && r;
        exit_section_(b, l, m, INDEX_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 6 && CallExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CALL_EXPRESSION, r, true, null);
      }
      else if (g < 9 && EqExpression_0(b, l + 1)) {
        r = Expression(b, l, 9);
        exit_section_(b, l, m, EQ_EXPRESSION, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, OR)) {
        r = Expression(b, l, 10);
        exit_section_(b, l, m, OR_EXPRESSION, r, true, null);
      }
      else if (g < 11 && consumeTokenSmart(b, AND)) {
        r = Expression(b, l, 11);
        exit_section_(b, l, m, AND_EXPRESSION, r, true, null);
      }
      else if (g < 12 && CompExpression_0(b, l + 1)) {
        r = Expression(b, l, 12);
        exit_section_(b, l, m, COMP_EXPRESSION, r, true, null);
      }
      else if (g < 13 && PlusMinExpression_0(b, l + 1)) {
        r = Expression(b, l, 13);
        exit_section_(b, l, m, PLUS_MIN_EXPRESSION, r, true, null);
      }
      else if (g < 14 && MultDivExpression_0(b, l + 1)) {
        r = Expression(b, l, 14);
        exit_section_(b, l, m, MULT_DIV_EXPRESSION, r, true, null);
      }
      else if (g < 15 && consumeTokenSmart(b, EXPONENT)) {
        r = Expression(b, l, 15);
        exit_section_(b, l, m, EXPONENT_EXPRESSION, r, true, null);
      }
      else if (g < 16 && BinExpression_0(b, l + 1)) {
        r = Expression(b, l, 16);
        exit_section_(b, l, m, BIN_EXPRESSION, r, true, null);
      }
      else if (g < 17 && MemberAccessExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_ACCESS_EXPRESSION, r, true, null);
      }
      else if (g < 22 && InExpression_0(b, l + 1)) {
        r = Expression(b, l, 22);
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

  // VarLiteral (',' VarLiteral)+ &INDNONE '='
  private static boolean TupleAssignmentExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarLiteral(b, l + 1);
    r = r && TupleAssignmentExpression_0_1(b, l + 1);
    r = r && TupleAssignmentExpression_0_2(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' VarLiteral)+
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

  // ',' VarLiteral
  private static boolean TupleAssignmentExpression_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleAssignmentExpression_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && VarLiteral(b, l + 1);
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

  // range &INDNONE '(' Expression (',' Expression)? RangeBound? ')'
  public static boolean RangeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression")) return false;
    if (!nextTokenIsSmart(b, RANGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, RANGE);
    r = r && RangeExpression_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && Expression(b, l + 1, -1);
    r = r && RangeExpression_4(b, l + 1);
    r = r && RangeExpression_5(b, l + 1);
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

  // (',' Expression)?
  private static boolean RangeExpression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_4")) return false;
    RangeExpression_4_0(b, l + 1);
    return true;
  }

  // ',' Expression
  private static boolean RangeExpression_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RangeBound?
  private static boolean RangeExpression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpression_5")) return false;
    RangeBound(b, l + 1);
    return true;
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

  // (not | '~' | '+' | '-') &INDNONE Expression
  public static boolean UnaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_EXPRESSION, "<unary expression>");
    r = UnaryExpression_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, UnaryExpression_1(b, l + 1));
    r = p && Expression(b, l + 1, -1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // not | '~' | '+' | '-'
  private static boolean UnaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, NOT);
    if (!r) r = consumeTokenSmart(b, TILDE);
    if (!r) r = consumeTokenSmart(b, PLUS);
    if (!r) r = consumeTokenSmart(b, MINUS);
    return r;
  }

  // &INDNONE
  private static boolean UnaryExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE FunctionCallArguments
  private static boolean CallExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CallExpression_0_0(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean CallExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VarLiteral (&INDNONE'(') StructExpressionMembers ')'
  public static boolean StructExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarLiteral(b, l + 1);
    r = r && StructExpression_1(b, l + 1);
    r = r && StructExpressionMembers(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, STRUCT_EXPRESSION, r);
    return r;
  }

  // &INDNONE'('
  private static boolean StructExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructExpression_1_0(b, l + 1);
    r = r && consumeTokenSmart(b, LPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean StructExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructExpression_1_0")) return false;
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

  // PIPE | AMPERSAND | RSHIFT | LSHIFT | CARET
  private static boolean BinExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinExpression_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, PIPE);
    if (!r) r = consumeTokenSmart(b, AMPERSAND);
    if (!r) r = consumeTokenSmart(b, RSHIFT);
    if (!r) r = consumeTokenSmart(b, LSHIFT);
    if (!r) r = consumeTokenSmart(b, CARET);
    return r;
  }

  // &INDNONE '.' &INDNONE VarLiteral
  private static boolean MemberAccessExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberAccessExpression_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, DOT);
    r = r && MemberAccessExpression_0_2(b, l + 1);
    r = r && VarLiteral(b, l + 1);
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

  // &INDNONE
  private static boolean MemberAccessExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessExpression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '[' Expression (',' (Expression | &']'))* ']'
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

  // (',' (Expression | &']'))*
  private static boolean InlineArrayExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!InlineArrayExpression_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InlineArrayExpression_2", c)) break;
    }
    return true;
  }

  // ',' (Expression | &']')
  private static boolean InlineArrayExpression_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokenSmart(b, COMMA);
    p = r; // pin = 1
    r = r && InlineArrayExpression_2_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expression | &']'
  private static boolean InlineArrayExpression_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1, -1);
    if (!r) r = InlineArrayExpression_2_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &']'
  private static boolean InlineArrayExpression_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineArrayExpression_2_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeTokenSmart(b, RBRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // log (&INDNONE Identifier '.')? &INDNONE VarLiteral FunctionCallArguments
  public static boolean EventLogExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression")) return false;
    if (!nextTokenIsSmart(b, LOG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LOG);
    r = r && EventLogExpression_1(b, l + 1);
    r = r && EventLogExpression_2(b, l + 1);
    r = r && VarLiteral(b, l + 1);
    r = r && FunctionCallArguments(b, l + 1);
    exit_section_(b, m, EVENT_LOG_EXPRESSION, r);
    return r;
  }

  // (&INDNONE Identifier '.')?
  private static boolean EventLogExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_1")) return false;
    EventLogExpression_1_0(b, l + 1);
    return true;
  }

  // &INDNONE Identifier '.'
  private static boolean EventLogExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EventLogExpression_1_0_0(b, l + 1);
    r = r && consumeTokensSmart(b, 0, IDENTIFIER, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean EventLogExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean EventLogExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EventLogExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean ExtCallExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtCallExpression")) return false;
    if (!nextTokenIsSmart(b, EXTCALL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, EXTCALL);
    p = r;
    r = p && Expression(b, l, 20);
    exit_section_(b, l, m, EXT_CALL_EXPRESSION, r, p, null);
    return r || p;
  }

  public static boolean StaticCallExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StaticCallExpression")) return false;
    if (!nextTokenIsSmart(b, STATICCALL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, STATICCALL);
    p = r;
    r = p && Expression(b, l, 21);
    exit_section_(b, l, m, STATIC_CALL_EXPRESSION, r, p, null);
    return r || p;
  }

  // (&INDNONE not)? &INDNONE in &INDNONE
  private static boolean InExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InExpression_0_0(b, l + 1);
    r = r && InExpression_0_1(b, l + 1);
    r = r && consumeToken(b, IN);
    r = r && InExpression_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (&INDNONE not)?
  private static boolean InExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_0")) return false;
    InExpression_0_0_0(b, l + 1);
    return true;
  }

  // &INDNONE not
  private static boolean InExpression_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InExpression_0_0_0_0(b, l + 1);
    r = r && consumeTokenSmart(b, NOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // &INDNONE
  private static boolean InExpression_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean InExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &INDNONE
  private static boolean InExpression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InExpression_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = indNone(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VarLiteral | BooleanLiteral | NumberLiteral | HexLiteral | StringLiteral | self
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
    if (!r) r = consumeTokenSmart(b, SELF);
    if (!r) r = TYPE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '(' Expression ')'
  public static boolean ParenthesizedExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesizedExpression")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIZED_EXPRESSION, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
