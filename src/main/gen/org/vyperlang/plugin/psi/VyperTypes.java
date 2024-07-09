// This is a generated file. Not intended for manual editing.
package org.vyperlang.plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.vyperlang.plugin.psi.impl.*;

public interface VyperTypes {

  IElementType AND_EXPRESSION = new VyperElementType("AND_EXPRESSION");
  IElementType ASSERT_EXPRESSION = new VyperElementType("ASSERT_EXPRESSION");
  IElementType ASSIGNMENT_EXPRESSION = new VyperElementType("ASSIGNMENT_EXPRESSION");
  IElementType BIN_EXPRESSION = new VyperElementType("BIN_EXPRESSION");
  IElementType CALL_EXPRESSION = new VyperElementType("CALL_EXPRESSION");
  IElementType CLEAR_EXPRESSION = new VyperElementType("CLEAR_EXPRESSION");
  IElementType COMP_EXPRESSION = new VyperElementType("COMP_EXPRESSION");
  IElementType CONSTANT_DEFINITION_EXPRESSION = new VyperElementType("CONSTANT_DEFINITION_EXPRESSION");
  IElementType EMIT_STATEMENT = new VyperElementType("EMIT_STATEMENT");
  IElementType EQ_EXPRESSION = new VyperElementType("EQ_EXPRESSION");
  IElementType EVENT_DECLARATION = new VyperElementType("EVENT_DECLARATION");
  IElementType EVENT_LOG_EXPRESSION = new VyperElementType("EVENT_LOG_EXPRESSION");
  IElementType EVENT_PROPERTY = new VyperElementType("EVENT_PROPERTY");
  IElementType EXPONENT_EXPRESSION = new VyperElementType("EXPONENT_EXPRESSION");
  IElementType EXPRESSION = new VyperElementType("EXPRESSION");
  IElementType EXT_CALL_EXPRESSION = new VyperElementType("EXT_CALL_EXPRESSION");
  IElementType FLAG_DECLARATION = new VyperElementType("FLAG_DECLARATION");
  IElementType FLAG_OPTION = new VyperElementType("FLAG_OPTION");
  IElementType FOR_STATEMENT = new VyperElementType("FOR_STATEMENT");
  IElementType FUNCTION_ARGS = new VyperElementType("FUNCTION_ARGS");
  IElementType FUNCTION_BODY = new VyperElementType("FUNCTION_BODY");
  IElementType FUNCTION_CALL_ARGUMENT = new VyperElementType("FUNCTION_CALL_ARGUMENT");
  IElementType FUNCTION_CALL_ARGUMENTS = new VyperElementType("FUNCTION_CALL_ARGUMENTS");
  IElementType FUNCTION_CALL_EXPRESSION = new VyperElementType("FUNCTION_CALL_EXPRESSION");
  IElementType FUNCTION_DECORATOR = new VyperElementType("FUNCTION_DECORATOR");
  IElementType FUNCTION_DEFINITION = new VyperElementType("FUNCTION_DEFINITION");
  IElementType FUNCTION_ENTRANCY_KEY = new VyperElementType("FUNCTION_ENTRANCY_KEY");
  IElementType FUNCTION_IMPLEMENTATION = new VyperElementType("FUNCTION_IMPLEMENTATION");
  IElementType FUNCTION_MODIFIER = new VyperElementType("FUNCTION_MODIFIER");
  IElementType FUN_TYPE_ANNOTATION = new VyperElementType("FUN_TYPE_ANNOTATION");
  IElementType IF_STATEMENT = new VyperElementType("IF_STATEMENT");
  IElementType IMMUTABLE_DEFINITION_EXPRESSION = new VyperElementType("IMMUTABLE_DEFINITION_EXPRESSION");
  IElementType IMPLEMENTS_DIRECTIVE = new VyperElementType("IMPLEMENTS_DIRECTIVE");
  IElementType IMPORT_DIRECTIVE = new VyperElementType("IMPORT_DIRECTIVE");
  IElementType IMPORT_PATH = new VyperElementType("IMPORT_PATH");
  IElementType INDEXED_DATA = new VyperElementType("INDEXED_DATA");
  IElementType INDEX_ACCESS_EXPRESSION = new VyperElementType("INDEX_ACCESS_EXPRESSION");
  IElementType INLINE_ARRAY_EXPRESSION = new VyperElementType("INLINE_ARRAY_EXPRESSION");
  IElementType INTERFACE_DECLARATION = new VyperElementType("INTERFACE_DECLARATION");
  IElementType INTERFACE_FUNCTION = new VyperElementType("INTERFACE_FUNCTION");
  IElementType INTERFACE_FUNCTION_MODIFIER = new VyperElementType("INTERFACE_FUNCTION_MODIFIER");
  IElementType IN_EXPRESSION = new VyperElementType("IN_EXPRESSION");
  IElementType LIST_TYPE = new VyperElementType("LIST_TYPE");
  IElementType LOCAL_VARIABLE_DEFINITION = new VyperElementType("LOCAL_VARIABLE_DEFINITION");
  IElementType MAP_TYPE = new VyperElementType("MAP_TYPE");
  IElementType MEMBER_ACCESS_EXPRESSION = new VyperElementType("MEMBER_ACCESS_EXPRESSION");
  IElementType MEMBER_INDEX_ACCESS = new VyperElementType("MEMBER_INDEX_ACCESS");
  IElementType MULTI_LINE_STRING = new VyperElementType("MULTI_LINE_STRING");
  IElementType MULT_DIV_EXPRESSION = new VyperElementType("MULT_DIV_EXPRESSION");
  IElementType OR_EXPRESSION = new VyperElementType("OR_EXPRESSION");
  IElementType PARAM_DEF = new VyperElementType("PARAM_DEF");
  IElementType PARENTHESIZIED_EXPRESSION = new VyperElementType("PARENTHESIZIED_EXPRESSION");
  IElementType PLUS_MIN_EXPRESSION = new VyperElementType("PLUS_MIN_EXPRESSION");
  IElementType PRIMARY_EXPRESSION = new VyperElementType("PRIMARY_EXPRESSION");
  IElementType RANGE_EXPRESSION = new VyperElementType("RANGE_EXPRESSION");
  IElementType STATEMENT = new VyperElementType("STATEMENT");
  IElementType STATE_VARIABLE_DECLARATION = new VyperElementType("STATE_VARIABLE_DECLARATION");
  IElementType STATE_VARIABLE_TYPE = new VyperElementType("STATE_VARIABLE_TYPE");
  IElementType STATIC_CALL_EXPRESSION = new VyperElementType("STATIC_CALL_EXPRESSION");
  IElementType STRUCT_DECLARATION = new VyperElementType("STRUCT_DECLARATION");
  IElementType STRUCT_EXPRESSION = new VyperElementType("STRUCT_EXPRESSION");
  IElementType STRUCT_EXPRESSION_MEMBER = new VyperElementType("STRUCT_EXPRESSION_MEMBER");
  IElementType STRUCT_TYPE = new VyperElementType("STRUCT_TYPE");
  IElementType TERNARY_EXPRESSION = new VyperElementType("TERNARY_EXPRESSION");
  IElementType TUPLE_ASSIGNMENT_EXPRESSION = new VyperElementType("TUPLE_ASSIGNMENT_EXPRESSION");
  IElementType TYPE = new VyperElementType("TYPE");
  IElementType UNARY_EXPRESSION = new VyperElementType("UNARY_EXPRESSION");
  IElementType VALUE_TYPE = new VyperElementType("VALUE_TYPE");
  IElementType VAR_LITERAL = new VyperElementType("VAR_LITERAL");

  IElementType ADDRESS = new VyperTokenType("address");
  IElementType AND = new VyperTokenType("and");
  IElementType AS = new VyperTokenType("as");
  IElementType ASSERT = new VyperTokenType("assert");
  IElementType ASSIGN = new VyperTokenType("=");
  IElementType BOOL = new VyperTokenType("bool");
  IElementType BOOLEANLITERAL = new VyperTokenType("booleanLiteral");
  IElementType BREAK = new VyperTokenType("break");
  IElementType BREAK_LINE = new VyperTokenType("BREAK_LINE");
  IElementType BYTES = new VyperTokenType("Bytes");
  IElementType BYTESM = new VyperTokenType("bytesM");
  IElementType CARET = new VyperTokenType("^");
  IElementType CLEAR = new VyperTokenType("clear");
  IElementType COLON = new VyperTokenType(":");
  IElementType COMMA = new VyperTokenType(",");
  IElementType COMMENT = new VyperTokenType("comment");
  IElementType CONSTANT = new VyperTokenType("constant");
  IElementType CONTINUE = new VyperTokenType("continue");
  IElementType DECIMALNUMBER = new VyperTokenType("decimalNumber");
  IElementType DECORATOR = new VyperTokenType("@");
  IElementType DEF = new VyperTokenType("def");
  IElementType DEPLOY = new VyperTokenType("deploy");
  IElementType DIV = new VyperTokenType("/");
  IElementType DIV_ASSIGN = new VyperTokenType("/=");
  IElementType DOT = new VyperTokenType(".");
  IElementType DYNARRAY = new VyperTokenType("DynArray");
  IElementType ELIF = new VyperTokenType("elif");
  IElementType ELLIPSIS = new VyperTokenType("...");
  IElementType ELSE = new VyperTokenType("else");
  IElementType ENUM = new VyperTokenType("enum");
  IElementType EQ = new VyperTokenType("==");
  IElementType EVENT = new VyperTokenType("event");
  IElementType EXPONENT = new VyperTokenType("**");
  IElementType EXTCALL = new VyperTokenType("extcall");
  IElementType EXTERNAL = new VyperTokenType("external");
  IElementType FIXEDNUMBER = new VyperTokenType("fixedNumber");
  IElementType FLAG = new VyperTokenType("flag");
  IElementType FOR = new VyperTokenType("for");
  IElementType FROM = new VyperTokenType("from");
  IElementType HASHMAP = new VyperTokenType("HashMap");
  IElementType HEXLITERAL = new VyperTokenType("hexLiteral");
  IElementType HEXNUMBER = new VyperTokenType("hexNumber");
  IElementType IDENTIFIER = new VyperTokenType("Identifier");
  IElementType IF = new VyperTokenType("if");
  IElementType IMMUTABLE = new VyperTokenType("immutable");
  IElementType IMPLEMENTS = new VyperTokenType("implements");
  IElementType IMPORT = new VyperTokenType("import");
  IElementType IN = new VyperTokenType("in");
  IElementType INTERFACE = new VyperTokenType("interface");
  IElementType INTERNAL = new VyperTokenType("internal");
  IElementType INTERSECTION = new VyperTokenType("&");
  IElementType INTM = new VyperTokenType("intM");
  IElementType LBRACE = new VyperTokenType("{");
  IElementType LBRACKET = new VyperTokenType("[");
  IElementType LESS = new VyperTokenType("<");
  IElementType LESSEQ = new VyperTokenType("<=");
  IElementType LOG = new VyperTokenType("log");
  IElementType LPAREN = new VyperTokenType("(");
  IElementType LSHIFT = new VyperTokenType("<<");
  IElementType MINUS = new VyperTokenType("-");
  IElementType MINUS_ASSIGN = new VyperTokenType("-=");
  IElementType MORE = new VyperTokenType(">");
  IElementType MOREEQ = new VyperTokenType(">=");
  IElementType MULT = new VyperTokenType("*");
  IElementType MULTILINESTRINGTOKEN = new VyperTokenType("MultiLineStringToken");
  IElementType MULT_ASSIGN = new VyperTokenType("*=");
  IElementType NEQ = new VyperTokenType("!=");
  IElementType NONPAYABLE = new VyperTokenType("nonpayable");
  IElementType NONREENTRANT = new VyperTokenType("nonreentrant");
  IElementType NOT = new VyperTokenType("not");
  IElementType OR = new VyperTokenType("or");
  IElementType PASS = new VyperTokenType("pass");
  IElementType PAYABLE = new VyperTokenType("payable");
  IElementType PERCENT = new VyperTokenType("%");
  IElementType PERCENT_ASSIGN = new VyperTokenType("%=");
  IElementType PLUS = new VyperTokenType("+");
  IElementType PLUS_ASSIGN = new VyperTokenType("+=");
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
  IElementType STATICCALL = new VyperTokenType("staticcall");
  IElementType STRING = new VyperTokenType("String");
  IElementType STRINGLITERALDOUBLE = new VyperTokenType("stringLiteralDouble");
  IElementType STRINGLITERALDOUBLEB = new VyperTokenType("stringLiteralDoubleB");
  IElementType STRINGLITERALSINGLE = new VyperTokenType("stringLiteralSingle");
  IElementType STRINGLITERALSINGLEB = new VyperTokenType("stringLiteralSingleB");
  IElementType STRUCT = new VyperTokenType("struct");
  IElementType TILDE = new VyperTokenType("~");
  IElementType TO = new VyperTokenType("=>");
  IElementType UINTM = new VyperTokenType("uintM");
  IElementType UNION = new VyperTokenType("|");
  IElementType VIEW = new VyperTokenType("view");

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
      else if (type == BIN_EXPRESSION) {
        return new VyperBinExpressionImpl(node);
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
      else if (type == CONSTANT_DEFINITION_EXPRESSION) {
        return new VyperConstantDefinitionExpressionImpl(node);
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
      else if (type == EVENT_LOG_EXPRESSION) {
        return new VyperEventLogExpressionImpl(node);
      }
      else if (type == EVENT_PROPERTY) {
        return new VyperEventPropertyImpl(node);
      }
      else if (type == EXPONENT_EXPRESSION) {
        return new VyperExponentExpressionImpl(node);
      }
      else if (type == EXT_CALL_EXPRESSION) {
        return new VyperExtCallExpressionImpl(node);
      }
      else if (type == FLAG_DECLARATION) {
        return new VyperFlagDeclarationImpl(node);
      }
      else if (type == FLAG_OPTION) {
        return new VyperFlagOptionImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new VyperForStatementImpl(node);
      }
      else if (type == FUNCTION_ARGS) {
        return new VyperFunctionArgsImpl(node);
      }
      else if (type == FUNCTION_BODY) {
        return new VyperFunctionBodyImpl(node);
      }
      else if (type == FUNCTION_CALL_ARGUMENT) {
        return new VyperFunctionCallArgumentImpl(node);
      }
      else if (type == FUNCTION_CALL_ARGUMENTS) {
        return new VyperFunctionCallArgumentsImpl(node);
      }
      else if (type == FUNCTION_CALL_EXPRESSION) {
        return new VyperFunctionCallExpressionImpl(node);
      }
      else if (type == FUNCTION_DECORATOR) {
        return new VyperFunctionDecoratorImpl(node);
      }
      else if (type == FUNCTION_DEFINITION) {
        return new VyperFunctionDefinitionImpl(node);
      }
      else if (type == FUNCTION_ENTRANCY_KEY) {
        return new VyperFunctionEntrancyKeyImpl(node);
      }
      else if (type == FUNCTION_IMPLEMENTATION) {
        return new VyperFunctionImplementationImpl(node);
      }
      else if (type == FUNCTION_MODIFIER) {
        return new VyperFunctionModifierImpl(node);
      }
      else if (type == FUN_TYPE_ANNOTATION) {
        return new VyperFunTypeAnnotationImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new VyperIfStatementImpl(node);
      }
      else if (type == IMMUTABLE_DEFINITION_EXPRESSION) {
        return new VyperImmutableDefinitionExpressionImpl(node);
      }
      else if (type == IMPLEMENTS_DIRECTIVE) {
        return new VyperImplementsDirectiveImpl(node);
      }
      else if (type == IMPORT_DIRECTIVE) {
        return new VyperImportDirectiveImpl(node);
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
      else if (type == INTERFACE_DECLARATION) {
        return new VyperInterfaceDeclarationImpl(node);
      }
      else if (type == INTERFACE_FUNCTION) {
        return new VyperInterfaceFunctionImpl(node);
      }
      else if (type == INTERFACE_FUNCTION_MODIFIER) {
        return new VyperInterfaceFunctionModifierImpl(node);
      }
      else if (type == IN_EXPRESSION) {
        return new VyperInExpressionImpl(node);
      }
      else if (type == LIST_TYPE) {
        return new VyperListTypeImpl(node);
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
      else if (type == STATE_VARIABLE_TYPE) {
        return new VyperStateVariableTypeImpl(node);
      }
      else if (type == STATIC_CALL_EXPRESSION) {
        return new VyperStaticCallExpressionImpl(node);
      }
      else if (type == STRUCT_DECLARATION) {
        return new VyperStructDeclarationImpl(node);
      }
      else if (type == STRUCT_EXPRESSION) {
        return new VyperStructExpressionImpl(node);
      }
      else if (type == STRUCT_EXPRESSION_MEMBER) {
        return new VyperStructExpressionMemberImpl(node);
      }
      else if (type == STRUCT_TYPE) {
        return new VyperStructTypeImpl(node);
      }
      else if (type == TERNARY_EXPRESSION) {
        return new VyperTernaryExpressionImpl(node);
      }
      else if (type == TUPLE_ASSIGNMENT_EXPRESSION) {
        return new VyperTupleAssignmentExpressionImpl(node);
      }
      else if (type == TYPE) {
        return new VyperTypeImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new VyperUnaryExpressionImpl(node);
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
