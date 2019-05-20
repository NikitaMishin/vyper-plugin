package com.vyperplugin.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.vyperplugin.psi.VyperTypes.*;

%%

%{
  public _VyperLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _VyperLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R

COMMENT=(#.*)
HEXLITERAL=hex\"([0-9a-fA-F]+)\"
STRINGLITERALDOUBLE=(\"([^\"\r\n\\]|\\.)*\")
STRINGLITERALDOUBLEB=(b\"([^\"\r\n\\]|\\.)*\")
STRINGLITERALSINGLE=('([^'\r\n\\]|\\.)*')
STRINGLITERALSINGLEB=(b'([^'\r\n\\]|\\.)*')
MULTILINESTRINGTOKEN=\"\"\"[\d\D]*?\"\"\"
DECIMALNUMBER=([0-9][_0-9]*)
FIXEDNUMBER=(([0-9][_0-9]*)+\.[_0-9]*|([0-9][_0-9]*)*\.([0-9][_0-9]*))
BOOLEANLITERAL=True|False
SCIENTIFICNUMBER=((([0-9][_0-9]*)+|([0-9][_0-9]*)+\.[_0-9]*|([0-9][_0-9]*|[0-9])*\.[_0-9]+)[Ee][+-]?[_0-9]+)
HEXNUMBER=(0[xX][_0-9a-fA-F]+)
WHITE_SPACE=[ \s\t\n\x0B\f\r]+
IDENTIFIER=([A-Za-z_][a-zA-Z_0-9]*)

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "@"                         { return DECORATOR; }
  ";"                         { return SEMICOLON; }
  ","                         { return COMMA; }
  "."                         { return DOT; }
  ":"                         { return COLON; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }
  "{"                         { return LBRACE; }
  "}"                         { return RBRACE; }
  "("                         { return LPAREN; }
  ")"                         { return RPAREN; }
  "import"                    { return IMPORT; }
  "from"                      { return FROM; }
  "+"                         { return PLUS; }
  "-"                         { return MINUS; }
  "*"                         { return MULT; }
  "/"                         { return DIV; }
  "**"                        { return EXPONENT; }
  "not"                       { return NOT; }
  "="                         { return ASSIGN; }
  "=>"                        { return TO; }
  "=="                        { return EQ; }
  "!="                        { return NEQ; }
  "+="                        { return PLUS_ASSIGN; }
  "-="                        { return MINUS_ASSIGN; }
  "*="                        { return MULT_ASSIGN; }
  "/="                        { return DIV_ASSIGN; }
  "%="                        { return PERCENT_ASSIGN; }
  "<"                         { return LESS; }
  "<="                        { return LESSEQ; }
  ">"                         { return MORE; }
  ">="                        { return MOREEQ; }
  "^"                         { return CARET; }
  "and"                       { return AND; }
  "or"                        { return OR; }
  "?"                         { return QUESTION; }
  "%"                         { return PERCENT; }
  "~"                         { return TILDE; }
  "<<"                        { return LSHIFT; }
  ">>"                        { return RSHIFT; }
  "constant"                  { return CONSTANT; }
  "public"                    { return PUBLIC; }
  "private"                   { return PRIVATE; }
  "nonreentrant"              { return NONREENTRANT; }
  "payable"                   { return PAYABLE; }
  "modifying"                 { return MODIFYING; }
  "event"                     { return EVENT; }
  "range"                     { return RANGE; }
  "BAD_CHARACTER"             { return BAD_CHARACTER; }
  "units"                     { return UNITS; }
  "as"                        { return AS; }
  "implements"                { return IMPLEMENTS; }
  "contract"                  { return CONTRACT; }
  "def"                       { return DEF; }
  "struct"                    { return STRUCT; }
  "elif"                      { return ELIF; }
  "else"                      { return ELSE; }
  "if"                        { return IF; }
  "for"                       { return FOR; }
  "in"                        { return IN; }
  "continue"                  { return CONTINUE; }
  "break"                     { return BREAK; }
  "return"                    { return RETURN; }
  "raise"                     { return RAISE; }
  "pass"                      { return PASS; }
  "TypeName"                  { return TYPENAME; }
  "clear"                     { return CLEAR; }
  "assert"                    { return ASSERT; }
  "int128"                    { return INT128; }
  "uint256"                   { return UINT256; }
  "bytes32"                   { return BYTES32; }
  "address"                   { return ADDRESS; }
  "bytes"                     { return BYTES; }
  "fixed"                     { return FIXED; }
  "bool"                      { return BOOL; }
  "string"                    { return STRING; }
  "map"                       { return MAP; }
  "ZERO_ADDRESS"              { return ZERO_ADDRESS; }
  "EMPTY_BYTES32"             { return EMPTY_BYTES32; }
  "MAX_INT128"                { return MAX_INT128; }
  "MIN_INT128"                { return MIN_INT128; }
  "MAX_DECIMAL"               { return MAX_DECIMAL; }
  "MIN_DECIMAL"               { return MIN_DECIMAL; }
  "MAX_UINT256"               { return MAX_UINT256; }

  {COMMENT}                   { return COMMENT; }
  {HEXLITERAL}                { return HEXLITERAL; }
  {STRINGLITERALDOUBLE}       { return STRINGLITERALDOUBLE; }
  {STRINGLITERALDOUBLEB}      { return STRINGLITERALDOUBLEB; }
  {STRINGLITERALSINGLE}       { return STRINGLITERALSINGLE; }
  {STRINGLITERALSINGLEB}      { return STRINGLITERALSINGLEB; }
  {MULTILINESTRINGTOKEN}      { return MULTILINESTRINGTOKEN; }
  {DECIMALNUMBER}             { return DECIMALNUMBER; }
  {FIXEDNUMBER}               { return FIXEDNUMBER; }
  {BOOLEANLITERAL}            { return BOOLEANLITERAL; }
  {SCIENTIFICNUMBER}          { return SCIENTIFICNUMBER; }
  {HEXNUMBER}                 { return HEXNUMBER; }
  {IDENTIFIER}                { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
