package org.vyperlang.plugin.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static psi.org.vyperlang.plugin.VyperTypes.*;

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
  "import"                     { return IMPORT; }
  "from"                       { return FROM; }
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
  "^"                          { return CARET; }
  "and"                        { return AND; }
  "or"                         { return OR; }
  "?"                          { return QUESTION; }
  "%"                          { return PERCENT; }
  "~"                          { return TILDE; }
  "<<"                         { return LSHIFT; }
  ">>"                         { return RSHIFT; }
  "&"                          { return INTERSECTION; }
  "|"                          { return UNION; }
  "public"                     { return PUBLIC; }
  "immutable"                  { return IMMUTABLE; }
  "constant"                   { return CONSTANT; }
  "private"                    { return PRIVATE; }
  "nonreentrant"               { return NONREENTRANT; }
  "payable"                    { return PAYABLE; }
  "nonpayable"                 { return NONPAYABLE; }
  "external"                   { return EXTERNAL; }
  "view"                       { return VIEW; }
  "pure"                       { return PURE; }
  "event"                      { return EVENT; }
  "range"                      { return RANGE; }
  "string"                     { return STRING; }
  "bytes"                      { return BYTES; }
  "DynArray"                   { return DYNARRAY; }
  "HashMap"                    { return HASHMAP; }
  "as"                         { return AS; }
  "implements"                 { return IMPLEMENTS; }
  "interface"                  { return INTERFACE; }
  "def"                        { return DEF; }
  "struct"                     { return STRUCT; }
  "pass"                       { return PASS; }
  "internal"                   { return INTERNAL; }
  "elif"                       { return ELIF; }
  "else"                       { return ELSE; }
  "if"                         { return IF; }
  "for"                        { return FOR; }
  "in"                         { return IN; }
  "continue"                   { return CONTINUE; }
  "break"                      { return BREAK; }
  "return"                     { return RETURN; }
  "raise"                      { return RAISE; }
  "log"                        { return LOG; }
  "TypeName"                   { return TYPENAME; }
  "clear"                      { return CLEAR; }
  "assert"                     { return ASSERT; }
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
