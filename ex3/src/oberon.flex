/*author @Zemin Chen*/
import java_cup.runtime.*;
import java.io.*;
import exceptions.*;


%%


%public
%class OberonScanner
%cupsym Symbol
%type java_cup.runtime.Symbol
%cup
%unicode
%ignorecase
%eofval{
	return ToCupSymbol(Symbol.EOF);
%eofval}
%yylexthrow LexicalException
%line 
%column


%{

int get_line(){	return yyline;}
int get_column(){	return yycolumn;}


private java_cup.runtime.Symbol ToCupSymbol(int type) 
{
	return new java_cup.runtime.Symbol(type);
}
private java_cup.runtime.Symbol ToCupSymbol(int type, Object value)
{
	return new java_cup.runtime.Symbol(type, value);
}
%}

MismatchedComment= "(*" ([^\*] | "*"+[^\)])+ | ([^\(]|"("+[^\*])+"*)"
Comment		= "(*"~"*)"
WhiteSpace 	= " "|\t|\b|\f|\r|\n|\r\n
IllegalInteger	= {Number}+{Identifier}+
Identifier		= [:jletter:]+[:jletterdigit:]*
Number		= {Decimal} | {Octal}
Decimal		= 0 | [1-9]+[0-9]*
Octal		= 0[0-7]+
IllegalOctal	= 0[0-7]*[9|8]+[0-9]*


%%


<YYINITIAL>
{
/*arithmetic operator*/
"+"		{return ToCupSymbol(Symbol.PLUS);}
"-"		{return ToCupSymbol(Symbol.MINUS);}
"*"		{return ToCupSymbol(Symbol.TIMES);}
"DIV"		{return ToCupSymbol(Symbol.DIVIDE);}
"MOD"		{return ToCupSymbol(Symbol.MOD);}
":="		{return ToCupSymbol(Symbol.ASSIGN);}

/*logicol operator*/
"="		{return ToCupSymbol(Symbol.EQ);}
"#"		{return ToCupSymbol(Symbol.NEQ);}
"<"		{return ToCupSymbol(Symbol.LT);}
"<="		{return ToCupSymbol(Symbol.LE);}
">"		{return ToCupSymbol(Symbol.GT);}
">="		{return ToCupSymbol(Symbol.GE);}
"OR"		{return ToCupSymbol(Symbol.OR);}
"&"		{return ToCupSymbol(Symbol.AND);}
"~"		{return ToCupSymbol(Symbol.NOT);}

/*keyword and reserved word*/
"IF"		{return ToCupSymbol(Symbol.IF);}
"THEN"		{return ToCupSymbol(Symbol.THEN);}
"ELSIF"		{return ToCupSymbol(Symbol.ELSIF);}
"ELSE"		{return ToCupSymbol(Symbol.ELSE);}
"WHILE"		{return ToCupSymbol(Symbol.WHILE);}    
"DO"		{return ToCupSymbol(Symbol.DO);}
"BEGIN"		{return ToCupSymbol(Symbol.BEGIN);}
"CONST"		{return ToCupSymbol(Symbol.CONST);}
"END"		{return ToCupSymbol(Symbol.END);}
"MODULE"	{return ToCupSymbol(Symbol.MODULE);}
"OF"		{return ToCupSymbol(Symbol.OF);}
"PROCEDURE"	{return ToCupSymbol(Symbol.PROCEDURE);}
"RECORD"		{return ToCupSymbol(Symbol.RECORD);}
"ARRAY"		{return ToCupSymbol(Symbol.ARRAY);}
"VAR"		{return ToCupSymbol(Symbol.VAR);}
"TYPE"		{return ToCupSymbol(Symbol.TYPE);}
"READ"		{return ToCupSymbol(Symbol.READ);}
"WRITE"		{return ToCupSymbol(Symbol.WRITE);}
"writeln"		{return ToCupSymbol(Symbol.WRITELN);}
"INTEGER"	{return ToCupSymbol(Symbol.INTEGER);}
"BOOLEAN"	{return ToCupSymbol(Symbol.BOOLEAN);}


/*other tokens*/
";"		{return ToCupSymbol(Symbol.SEMI);}
":"		{return ToCupSymbol(Symbol.COLON);}
","		{return ToCupSymbol(Symbol.COMMA);}
"."		{return ToCupSymbol(Symbol.PERIOD);}
"["		{return ToCupSymbol(Symbol.LBRACKET);}
"]"		{return ToCupSymbol(Symbol.RBRACKET);}
"("		{return ToCupSymbol(Symbol.LPAREN);}
")"		{return ToCupSymbol(Symbol.RPAREN);}

/*comment, identifier and number*/
{WhiteSpace}	{/*skip it*/}
{Comment}	{/*Do nothing*/}
{MismatchedComment}	
		{throw new MismatchedCommentException(yytext());}
{IllegalOctal}	{throw new IllegalOctalException(yytext());}
{IllegalInteger}	{throw new IllegalIntegerException(yytext());}
{Number}		{
			if (yylength() > 12)
				throw new IllegalIntegerRangeException(yytext());
			if (yytext().charAt(0) == '0' && yytext().length()>1)
				return ToCupSymbol(Symbol.NUMBER, yytext());
			return ToCupSymbol(Symbol.NUMBER, yytext());
		}
{Identifier}		{
			if (yylength() > 24)
				throw new IllegalIdentifierLengthException(yytext());
			return ToCupSymbol(Symbol.IDENTIFIER, yytext());
		}
}
[^]		{throw new IllegalSymbolException(yytext());}









