import java.io.*;
import exceptions.*;

%%

%public
%class OberonScanner
%type int

%unicode

%line
%column

%ignorecase

%debug

%eofval{
  return Lexical.EOF;
%eofval}

%yylexthrow LexicalException

/* main character classes */
LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = "(*"~"*)"
MismatchedComment = "(*" ([^\*] | "*"+[^\)])* | ([^\(]|"("+[^\*])* "*)"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer */
Integer = [1-9]+[0-9]* | 0[0-7]*
IllegalOctal = 0[0-7]*[9|8]+[0-9]*
IllegalInteger = {Integer}+{Identifier}+


%%

<YYINITIAL> {

  /* keywords */
  "BOOLEAN"                      { return Lexical.BOOLEAN; }
  "INTEGER"                      { return Lexical.INTEGER; }
  "DO"                           { return Lexical.DO; }
  "WHILE"                        { return Lexical.WHILE; }
  "IF"                           { return Lexical.IF; }
  "ELSE"                         { return Lexical.ELSE; }
  "ELSIF"                        { return Lexical.ELSIF; }
  "THEN"                         { return Lexical.THEN; }
  "CONST"                        { return Lexical.CONST; }
  "BEGIN"                        { return Lexical.BEGIN; }
  "END"                          { return Lexical.END; }

  /* reservedword */
  "MODULE"                       { return Lexical.MODULE; }
  "TYPE"                         { return Lexical.TYPE; }
  "PROCEDURE"                    { return Lexical.PROCEDURE; }
  "RECORD"                       { return Lexical.RECORD; }
  "ARRAY"                        { return Lexical.ARRAY; }
  "OF"                           { return Lexical.OF; }
  "VAR"                          { return Lexical.VAR; }
  "DIV"                          { return Lexical.DIV; }
  "MOD"                          { return Lexical.MOD; }
  "OR"                           { return Lexical.OR; }
  "Read"                         { return Lexical.READ; }
  "Write"                        { return Lexical.WRITE; }
  "Writeln"                      { return Lexical.WRITELN; }

  /* boolean */
  "TRUE"                         { return Lexical.TRUE; }
  "FALSE"                        { return Lexical.FALSE; }
  
  /* separators */
  "("                            { return Lexical.LPAREN; }
  ")"                            { return Lexical.RPAREN; }
  "["                            { return Lexical.LBRACK; }
  "]"                            { return Lexical.RBRACK; }
  ";"                            { return Lexical.SEMICOLON; }
  ":"                            { return Lexical.COLON; }
  ","                            { return Lexical.COMMA; }
  "."                            { return Lexical.DOT; }
  
  /* operators */
  "="                            { return Lexical.EQ; }
  ">"                            { return Lexical.GT; }
  "<"                            { return Lexical.LT; }
  "~"                            { return Lexical.NOT; }
  "<="                           { return Lexical.LTEQ; }
  ">="                           { return Lexical.GTEQ; }
  "#"                            { return Lexical.NOTEQ; }
  "&"                            { return Lexical.AND; }
  "+"                            { return Lexical.PLUS; }
  "-"                            { return Lexical.MINUS; }
  "*"                            { return Lexical.MULT; }
  
  {Integer}                      { if(yylength() > 12)
                                        throw new IllegalIntegerRangeException();
                                   return Lexical.Integer; }
  {IllegalInteger}               { throw new IllegalIntegerException(); }
  {IllegalOctal}                 { throw new IllegalOctalException(); }
  
  /* comments */
  {Comment}                      { return Lexical.Comment; }
  {MismatchedComment}            { throw new MismatchedCommentException(); }

  /* whitespace */
  {WhiteSpace}                   { return Lexical.WhiteSpace; }

  /* identifiers */ 
  {Identifier}                   { if(yylength() > 24)
                                        throw new IllegalIdentifierLengthException();
                                   return Lexical.Identifier; }  
  .                              { throw new IllegalSymbolException(); } 

}