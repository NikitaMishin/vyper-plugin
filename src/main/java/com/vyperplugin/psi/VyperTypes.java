// This is a generated file. Not intended for manual editing.
package com.vyperplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.vyperplugin.psi.impl.*;

public interface VyperTypes {

  IElementType AND_EXPRESSION = new VyperElementType("AND_EXPRESSION");
  IElementType ASSERT_EXPRESSION = new VyperElementType("ASSERT_EXPRESSION");
  IElementType ASSIGNMENT_EXPRESSION = new VyperElementType("ASSIGNMENT_EXPRESSION");
  IElementType BAD_STATEMENT = new VyperElementType("BAD_STATEMENT");
  IElementType BUILT_IN = new VyperElementType("BUILT_IN");
  IElementType CALL_EXPRESSION = new VyperElementType("CALL_EXPRESSION");
  IElementType CLEAR_EXPRESSION = new VyperElementType("CLEAR_EXPRESSION");
  IElementType COMP_EXPRESSION = new VyperElementType("COMP_EXPRESSION");
  IElementType CUSTOM_UNIT_TYPE = new VyperElementType("CUSTOM_UNIT_TYPE");
  IElementType EMIT_STATEMENT = new VyperElementType("EMIT_STATEMENT");
  IElementType EQ_EXPRESSION = new VyperElementType("EQ_EXPRESSION");
  IElementType EVENT_DECLARATION = new VyperElementType("EVENT_DECLARATION");
  IElementType EVENT_PROPERTY = new VyperElementType("EVENT_PROPERTY");
  IElementType EXPONENT_EXPRESSION = new VyperElementType("EXPONENT_EXPRESSION");
  IElementType EXPRESSION = new VyperElementType("EXPRESSION");
  IElementType EXTERNAL_INTERFACES = new VyperElementType("EXTERNAL_INTERFACES");
  IElementType FOR_STATEMENT = new VyperElementType("FOR_STATEMENT");
  IElementType FUNCTION_ARGS = new VyperElementType("FUNCTION_ARGS");
  IElementType FUNCTION_CALL_ARGUMENTS = new VyperElementType("FUNCTION_CALL_ARGUMENTS");
  IElementType FUNCTION_CALL_EXPRESSION = new VyperElementType("FUNCTION_CALL_EXPRESSION");
  IElementType FUNCTION_DEFINITION = new VyperElementType("FUNCTION_DEFINITION");
  IElementType FUNCTION_MODIFIER = new VyperElementType("FUNCTION_MODIFIER");
  IElementType IF_STATEMENT = new VyperElementType("IF_STATEMENT");
  IElementType IMPLEMENTS_DIRECTIVE = new VyperElementType("IMPLEMENTS_DIRECTIVE");
  IElementType IMPORT_PATH = new VyperElementType("IMPORT_PATH");
  IElementType INDEXED_DATA = new VyperElementType("INDEXED_DATA");
  IElementType INDEX_ACCESS_EXPRESSION = new VyperElementType("INDEX_ACCESS_EXPRESSION");
  IElementType INLINE_ARRAY_EXPRESSION = new VyperElementType("INLINE_ARRAY_EXPRESSION");
  IElementType LIST_TYPE = new VyperElementType("LIST_TYPE");
  IElementType LOCAL_VARIABLE_DECLARATION = new VyperElementType("LOCAL_VARIABLE_DECLARATION");
  IElementType LOCAL_VARIABLE_DEFINITION = new VyperElementType("LOCAL_VARIABLE_DEFINITION");
  IElementType MAP_TYPE = new VyperElementType("MAP_TYPE");
  IElementType MEMBER_ACCESS_EXPRESSION = new VyperElementType("MEMBER_ACCESS_EXPRESSION");
  IElementType MEMBER_INDEX_ACCESS = new VyperElementType("MEMBER_INDEX_ACCESS");
  IElementType MULTI_LINE_STRING = new VyperElementType("MULTI_LINE_STRING");
  IElementType MULT_DIV_EXPRESSION = new VyperElementType("MULT_DIV_EXPRESSION");
  IElementType NEW_EXPRESSION = new VyperElementType("NEW_EXPRESSION");
  IElementType OR_EXPRESSION = new VyperElementType("OR_EXPRESSION");
  IElementType PARAM_DEF = new VyperElementType("PARAM_DEF");
  IElementType PARENTHESIZIED_EXPRESSION = new VyperElementType("PARENTHESIZIED_EXPRESSION");
  IElementType PLUS_MIN_EXPRESSION = new VyperElementType("PLUS_MIN_EXPRESSION");
  IElementType PRIMARY_EXPRESSION = new VyperElementType("PRIMARY_EXPRESSION");
  IElementType RANGE_EXPRESSION = new VyperElementType("RANGE_EXPRESSION");
  IElementType STATEMENT = new VyperElementType("STATEMENT");
  IElementType STATE_VARIABLE_DECLARATION = new VyperElementType("STATE_VARIABLE_DECLARATION");
  IElementType STATE_VARIABLE_MODIFIER = new VyperElementType("STATE_VARIABLE_MODIFIER");
  IElementType STRUCT_DEFINITION = new VyperElementType("STRUCT_DEFINITION");
  IElementType STRUCT_TYPE = new VyperElementType("STRUCT_TYPE");
  IElementType TYPE = new VyperElementType("TYPE");
  IElementType UNARY_EXPRESSION = new VyperElementType("UNARY_EXPRESSION");
  IElementType UNIQUE_KEY = new VyperElementType("UNIQUE_KEY");
  IElementType UNITS_DEFINITION = new VyperElementType("UNITS_DEFINITION");
  IElementType UNIT_TYPE = new VyperElementType("UNIT_TYPE");
  IElementType USER_DEFINED_CONSTANTS_EXPRESSION = new VyperElementType("USER_DEFINED_CONSTANTS_EXPRESSION");
  IElementType VALUE_TYPE = new VyperElementType("VALUE_TYPE");
  IElementType VAR_LITERAL = new VyperElementType("VAR_LITERAL");

  IElementType ADDRESS = new VyperTokenType("address");
  IElementType AND = new VyperTokenType("and");
  IElementType AS = new VyperTokenType("as");
  IElementType ASSERT = new VyperTokenType("assert");
  IElementType ASSIGN = new VyperTokenType("=");
  IElementType BAD_CHARACTER = new VyperTokenType("BAD_CHARACTER");
  IElementType BOOL = new VyperTokenType("bool");
  IElementType BOOLEANLITERAL = new VyperTokenType("booleanLiteral");
  IElementType BREAK = new VyperTokenType("break");
  IElementType BYTES = new VyperTokenType("bytes");
  IElementType BYTES32 = new VyperTokenType("bytes32");
  IElementType CARET = new VyperTokenType("^");
  IElementType CLEAR = new VyperTokenType("clear");
  IElementType COLON = new VyperTokenType(":");
  IElementType COMMA = new VyperTokenType(",");
  IElementType COMMENT = new VyperTokenType("comment");
  IElementType CONSTANT = new VyperTokenType("constant");
  IElementType CONTINUE = new VyperTokenType("continue");
  IElementType CONTRACT = new VyperTokenType("contract");
  IElementType DECIMALNUMBER = new VyperTokenType("decimalNumber");
  IElementType DECORATOR = new VyperTokenType("@");
  IElementType DEF = new VyperTokenType("def");
  IElementType DIV = new VyperTokenType("/");
  IElementType DIV_ASSIGN = new VyperTokenType("/=");
  IElementType DOT = new VyperTokenType(".");
  IElementType ELIF = new VyperTokenType("elif");
  IElementType ELSE = new VyperTokenType("else");
  IElementType EMPTY_BYTES32 = new VyperTokenType("EMPTY_BYTES32");
  IElementType EQ = new VyperTokenType("==");
  IElementType EVENT = new VyperTokenType("event");
  IElementType EXPONENT = new VyperTokenType("**");
  IElementType EXTERNAL = new VyperTokenType("external");
  IElementType FIXED = new VyperTokenType("fixed");
  IElementType FIXEDNUMBER = new VyperTokenType("fixedNumber");
  IElementType FOR = new VyperTokenType("for");
  IElementType FROM = new VyperTokenType("from");
  IElementType HASHMAP = new VyperTokenType("HashMap");
  IElementType HEXLITERAL = new VyperTokenType("hexLiteral");
  IElementType HEXNUMBER = new VyperTokenType("hexNumber");
  IElementType IDENTIFIER = new VyperTokenType("Identifier");
  IElementType IF = new VyperTokenType("if");
  IElementType IMPLEMENTS = new VyperTokenType("implements");
  IElementType IMPORT = new VyperTokenType("import");
  IElementType IN = new VyperTokenType("in");
  IElementType INT128 = new VyperTokenType("int128");
  IElementType INTERNAL = new VyperTokenType("internal");
  IElementType LBRACE = new VyperTokenType("{");
  IElementType LBRACKET = new VyperTokenType("[");
  IElementType LESS = new VyperTokenType("<");
  IElementType LESSEQ = new VyperTokenType("<=");
  IElementType LPAREN = new VyperTokenType("(");
  IElementType LSHIFT = new VyperTokenType("<<");
  IElementType MAP = new VyperTokenType("map");
  IElementType MAX_DECIMAL = new VyperTokenType("MAX_DECIMAL");
  IElementType MAX_INT128 = new VyperTokenType("MAX_INT128");
  IElementType MAX_UINT256 = new VyperTokenType("MAX_UINT256");
  IElementType MINUS = new VyperTokenType("-");
  IElementType MINUS_ASSIGN = new VyperTokenType("-=");
  IElementType MIN_DECIMAL = new VyperTokenType("MIN_DECIMAL");
  IElementType MIN_INT128 = new VyperTokenType("MIN_INT128");
  IElementType MODIFYING = new VyperTokenType("modifying");
  IElementType MORE = new VyperTokenType(">");
  IElementType MOREEQ = new VyperTokenType(">=");
  IElementType MULT = new VyperTokenType("*");
  IElementType MULTILINESTRINGTOKEN = new VyperTokenType("MultiLineStringToken");
  IElementType MULT_ASSIGN = new VyperTokenType("*=");
  IElementType NEQ = new VyperTokenType("!=");
  IElementType NONREENTRANT = new VyperTokenType("nonreentrant");
  IElementType NOT = new VyperTokenType("not");
  IElementType OR = new VyperTokenType("or");
  IElementType PASS = new VyperTokenType("pass");
  IElementType PAYABLE = new VyperTokenType("payable");
  IElementType PERCENT = new VyperTokenType("%");
  IElementType PERCENT_ASSIGN = new VyperTokenType("%=");
  IElementType PLUS = new VyperTokenType("+");
  IElementType PLUS_ASSIGN = new VyperTokenType("+=");
  IElementType PRIVATE = new VyperTokenType("private");
  IElementType PUBLIC = new VyperTokenType("public");
  IElementType PURE = new VyperTokenType("pure");
  IElementType QUESTION = new VyperTokenType("?");
  IElementType RAISE = new VyperTokenType("raise");
  IElementType RANGE = new VyperTokenType("range");
  IElementType RBRACE = new VyperTokenType("}");
  IElementType RBRACKET = new VyperTokenType("]");
  IElementType RETURN = new VyperTokenType("return");
  IElementType RPAREN = new VyperTokenType(")");
  IElementType RSHIFT = new VyperTokenType(">>");
  IElementType SCIENTIFICNUMBER = new VyperTokenType("scientificNumber");
  IElementType SEMICOLON = new VyperTokenType(";");
  IElementType STRING = new VyperTokenType("string");
  IElementType STRINGLITERALDOUBLE = new VyperTokenType("stringLiteralDouble");
  IElementType STRINGLITERALDOUBLEB = new VyperTokenType("stringLiteralDoubleB");
  IElementType STRINGLITERALSINGLE = new VyperTokenType("stringLiteralSingle");
  IElementType STRINGLITERALSINGLEB = new VyperTokenType("stringLiteralSingleB");
  IElementType STRUCT = new VyperTokenType("struct");
  IElementType TILDE = new VyperTokenType("~");
  IElementType TO = new VyperTokenType("=>");
  IElementType TYPENAME = new VyperTokenType("TypeName");
  IElementType UINT256 = new VyperTokenType("uint256");
  IElementType UNITS = new VyperTokenType("units");
  IElementType VIEW = new VyperTokenType("view");
  IElementType ZERO_ADDRESS = new VyperTokenType("ZERO_ADDRESS");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == AND_EXPRESSION) {
        return new VyperAndExpressionImpl(node);
      }
      else if (type == ASSERT_EXPRESSION) {
        return new VyperAssertExpressionImpl(node);
      }
      else if (type == ASSIGNMENT_EXPRESSION) {
        return new VyperAssignmentExpressionImpl(node);
      }
      else if (type == BAD_STATEMENT) {
        return new VyperBadStatementImpl(node);
      }
      else if (type == BUILT_IN) {
        return new VyperBuiltInImpl(node);
      }
      else if (type == CALL_EXPRESSION) {
        return new VyperCallExpressionImpl(node);
      }
      else if (type == CLEAR_EXPRESSION) {
        return new VyperClearExpressionImpl(node);
      }
      else if (type == COMP_EXPRESSION) {
        return new VyperCompExpressionImpl(node);
      }
      else if (type == CUSTOM_UNIT_TYPE) {
        return new VyperCustomUnitTypeImpl(node);
      }
      else if (type == EMIT_STATEMENT) {
        return new VyperEmitStatementImpl(node);
      }
      else if (type == EQ_EXPRESSION) {
        return new VyperEqExpressionImpl(node);
      }
      else if (type == EVENT_DECLARATION) {
        return new VyperEventDeclarationImpl(node);
      }
      else if (type == EVENT_PROPERTY) {
        return new VyperEventPropertyImpl(node);
      }
      else if (type == EXPONENT_EXPRESSION) {
        return new VyperExponentExpressionImpl(node);
      }
      else if (type == EXTERNAL_INTERFACES) {
        return new VyperExternalInterfacesImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new VyperForStatementImpl(node);
      }
      else if (type == FUNCTION_ARGS) {
        return new VyperFunctionArgsImpl(node);
      }
      else if (type == FUNCTION_CALL_ARGUMENTS) {
        return new VyperFunctionCallArgumentsImpl(node);
      }
      else if (type == FUNCTION_CALL_EXPRESSION) {
        return new VyperFunctionCallExpressionImpl(node);
      }
      else if (type == FUNCTION_DEFINITION) {
        return new VyperFunctionDefinitionImpl(node);
      }
      else if (type == FUNCTION_MODIFIER) {
        return new VyperFunctionModifierImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new VyperIfStatementImpl(node);
      }
      else if (type == IMPLEMENTS_DIRECTIVE) {
        return new VyperImplementsDirectiveImpl(node);
      }
      else if (type == IMPORT_PATH) {
        return new VyperImportPathImpl(node);
      }
      else if (type == INDEXED_DATA) {
        return new VyperIndexedDataImpl(node);
      }
      else if (type == INDEX_ACCESS_EXPRESSION) {
        return new VyperIndexAccessExpressionImpl(node);
      }
      else if (type == INLINE_ARRAY_EXPRESSION) {
        return new VyperInlineArrayExpressionImpl(node);
      }
      else if (type == LIST_TYPE) {
        return new VyperListTypeImpl(node);
      }
      else if (type == LOCAL_VARIABLE_DECLARATION) {
        return new VyperLocalVariableDeclarationImpl(node);
      }
      else if (type == LOCAL_VARIABLE_DEFINITION) {
        return new VyperLocalVariableDefinitionImpl(node);
      }
      else if (type == MAP_TYPE) {
        return new VyperMapTypeImpl(node);
      }
      else if (type == MEMBER_ACCESS_EXPRESSION) {
        return new VyperMemberAccessExpressionImpl(node);
      }
      else if (type == MEMBER_INDEX_ACCESS) {
        return new VyperMemberIndexAccessImpl(node);
      }
      else if (type == MULTI_LINE_STRING) {
        return new VyperMultiLineStringImpl(node);
      }
      else if (type == MULT_DIV_EXPRESSION) {
        return new VyperMultDivExpressionImpl(node);
      }
      else if (type == NEW_EXPRESSION) {
        return new VyperNewExpressionImpl(node);
      }
      else if (type == OR_EXPRESSION) {
        return new VyperOrExpressionImpl(node);
      }
      else if (type == PARAM_DEF) {
        return new VyperParamDefImpl(node);
      }
      else if (type == PARENTHESIZIED_EXPRESSION) {
        return new VyperParenthesiziedExpressionImpl(node);
      }
      else if (type == PLUS_MIN_EXPRESSION) {
        return new VyperPlusMinExpressionImpl(node);
      }
      else if (type == PRIMARY_EXPRESSION) {
        return new VyperPrimaryExpressionImpl(node);
      }
      else if (type == RANGE_EXPRESSION) {
        return new VyperRangeExpressionImpl(node);
      }
      else if (type == STATEMENT) {
        return new VyperStatementImpl(node);
      }
      else if (type == STATE_VARIABLE_DECLARATION) {
        return new VyperStateVariableDeclarationImpl(node);
      }
      else if (type == STATE_VARIABLE_MODIFIER) {
        return new VyperStateVariableModifierImpl(node);
      }
      else if (type == STRUCT_DEFINITION) {
        return new VyperStructDefinitionImpl(node);
      }
      else if (type == STRUCT_TYPE) {
        return new VyperStructTypeImpl(node);
      }
      else if (type == TYPE) {
        return new VyperTypeImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new VyperUnaryExpressionImpl(node);
      }
      else if (type == UNIQUE_KEY) {
        return new VyperUniqueKeyImpl(node);
      }
      else if (type == UNITS_DEFINITION) {
        return new VyperUnitsDefinitionImpl(node);
      }
      else if (type == UNIT_TYPE) {
        return new VyperUnitTypeImpl(node);
      }
      else if (type == USER_DEFINED_CONSTANTS_EXPRESSION) {
        return new VyperUserDefinedConstantsExpressionImpl(node);
      }
      else if (type == VALUE_TYPE) {
        return new VyperValueTypeImpl(node);
      }
      else if (type == VAR_LITERAL) {
        return new VyperVarLiteralImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
