package org.vyperlang.plugin.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.vyperlang.plugin.psi.VyperTypes.*;

%%

%{
  public _BaseVyperLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _BaseVyperLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT=(#.*)
HEXLITERAL=hex\"([0-9a-fA-F]+)\"
STRINGLITERALDOUBLE=(\"([^\"\r\n\\]|\\.)*\")
STRINGLITERALDOUBLEB=(b\"([^\"\r\n\\]|\\.)*\")
STRINGLITERALSINGLE=('([^'\r\n\\]|\\.)*')
STRINGLITERALSINGLEB=(b'([^'\r\n\\]|\\.)*')
MULTILINESTRINGTOKEN=(\"\"\"([^\"]|\"[^\"]|\"\"[^\"])*\"\"\")
DECIMALNUMBER=([0-9][_0-9]*)
FIXEDNUMBER=(([0-9][_0-9]*)+\.[_0-9]*|([0-9][_0-9]*)*\.([0-9][_0-9]*))
BOOLEANLITERAL=True|False
SCIENTIFICNUMBER=((([0-9][_0-9]*)+|([0-9][_0-9]*)+\.[_0-9]*|([0-9][_0-9]*|[0-9])*\.[_0-9]+)[Ee][+-]?[_0-9]+)
HEXNUMBER=(0[xX][_0-9a-fA-F]+)
INTM=(int[0-9]+)
UINTM=(uint[0-9]+)
BYTESM=(bytes[0-9]+)
NEWLINE=(\r?\n)
BREAK_LINE=([ \t\n\x0B\f\r]*\\[ \t\n\x0B\f\r]*\n)
IDENTIFIER=([A-Za-z_][a-zA-Z_0-9]*)

%%
<YYINITIAL> {
  {WHITE_SPACE}                { return WHITE_SPACE; }

  "@"                          { return DECORATOR; }
  ";"                          { return SEMICOLON; }
  ","                          { return COMMA; }
  "."                          { return DOT; }
  ":"                          { return COLON; }
  "["                          { return LBRACKET; }
  "]"                          { return RBRACKET; }
  "{"                          { return LBRACE; }
  "}"                          { return RBRACE; }
  "("                          { return LPAREN; }
  ")"                          { return RPAREN; }
  "..."                        { return ELLIPSIS; }
  ":="                         { return WALRUS; }
  "+"                          { return PLUS; }
  "-"                          { return MINUS; }
  "*"                          { return MULT; }
  "/"                          { return DIV; }
  "**"                         { return EXPONENT; }
  "not"                        { return NOT; }
  "="                          { return ASSIGN; }
  "=>"                         { return TO; }
  "=="                         { return EQ; }
  "!="                         { return NEQ; }
  "+="                         { return PLUS_ASSIGN; }
  "-="                         { return MINUS_ASSIGN; }
  "*="                         { return MULT_ASSIGN; }
  "/="                         { return DIV_ASSIGN; }
  "%="                         { return PERCENT_ASSIGN; }
  "<"                          { return LESS; }
  "<="                         { return LESSEQ; }
  ">"                          { return MORE; }
  ">="                         { return MOREEQ; }
  "and"                        { return AND; }
  "or"                         { return OR; }
  "?"                          { return QUESTION; }
  "%"                          { return PERCENT; }
  "~"                          { return TILDE; }
  "<<"                         { return LSHIFT; }
  ">>"                         { return RSHIFT; }
  "&"                          { return AMPERSAND; }
  "|"                          { return PIPE; }
  "^"                          { return CARET; }
  "bound"                      { return BOUND; }
  "String"                     { return STRING; }
  "Bytes"                      { return BYTES; }
  "DynArray"                   { return DYNARRAY; }
  "HashMap"                    { return HASHMAP; }
  "constant"                   { return CONSTANT; }
  "public"                     { return PUBLIC; }
  "immutable"                  { return IMMUTABLE; }
  "from"                       { return FROM; }
  "import"                     { return IMPORT; }
  "as"                         { return AS; }
  "implements"                 { return IMPLEMENTS; }
  "uses"                       { return USES; }
  "exports"                    { return EXPORTS; }
  "initializes"                { return INITIALIZES; }
  "interface"                  { return INTERFACE; }
  "def"                        { return DEF; }
  "pure"                       { return PURE; }
  "view"                       { return VIEW; }
  "nonpayable"                 { return NONPAYABLE; }
  "payable"                    { return PAYABLE; }
  "struct"                     { return STRUCT; }
  "enum"                       { return ENUM; }
  "flag"                       { return FLAG; }
  "event"                      { return EVENT; }
  "indexed"                    { return INDEXED; }
  "external"                   { return EXTERNAL; }
  "internal"                   { return INTERNAL; }
  "deploy"                     { return DEPLOY; }
  "nonreentrant"               { return NONREENTRANT; }
  "elif"                       { return ELIF; }
  "else"                       { return ELSE; }
  "if"                         { return IF; }
  "for"                        { return FOR; }
  "in"                         { return IN; }
  "continue"                   { return CONTINUE; }
  "break"                      { return BREAK; }
  "return"                     { return RETURN; }
  "raise"                      { return RAISE; }
  "pass"                       { return PASS; }
  "log"                        { return LOG; }
  "extcall"                    { return EXTCALL; }
  "staticcall"                 { return STATICCALL; }
  "range"                      { return RANGE; }
  "assert"                     { return ASSERT; }
  "self"                       { return SELF; }
  "address"                    { return ADDRESS; }
  "bool"                       { return BOOL; }

  {COMMENT}                    { return COMMENT; }
  {HEXLITERAL}                 { return HEXLITERAL; }
  {STRINGLITERALDOUBLE}        { return STRINGLITERALDOUBLE; }
  {STRINGLITERALDOUBLEB}       { return STRINGLITERALDOUBLEB; }
  {STRINGLITERALSINGLE}        { return STRINGLITERALSINGLE; }
  {STRINGLITERALSINGLEB}       { return STRINGLITERALSINGLEB; }
  {MULTILINESTRINGTOKEN}       { return MULTILINESTRINGTOKEN; }
  {DECIMALNUMBER}              { return DECIMALNUMBER; }
  {FIXEDNUMBER}                { return FIXEDNUMBER; }
  {BOOLEANLITERAL}             { return BOOLEANLITERAL; }
  {SCIENTIFICNUMBER}           { return SCIENTIFICNUMBER; }
  {HEXNUMBER}                  { return HEXNUMBER; }
  {INTM}                       { return INTM; }
  {UINTM}                      { return UINTM; }
  {BYTESM}                     { return BYTESM; }
  {NEWLINE}                    { return NEWLINE; }
  {BREAK_LINE}                 { return BREAK_LINE; }
  {IDENTIFIER}                 { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
