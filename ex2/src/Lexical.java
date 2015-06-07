public class Lexical
{
	  public static final int BOOLEAN = 1;
	  public static final int INTEGER = 2;
	  public static final int DO = 3;
	  public static final int WHILE = 4;
	  public static final int IF = 5;
	  public static final int ELSE = 6;
	  public static final int ELSIF = 7;
	  public static final int THEN = 8;
	  public static final int CONST = 9; 
	  public static final int BEGIN = 10;
	  public static final int END = 11;

	  public static final int MODULE = 12;
	  public static final int TYPE =13;
	  public static final int PROCEDURE = 14;
	  public static final int RECORD = 15;
	  public static final int ARRAY = 16;
	  public static final int OF = 17;
	  public static final int VAR = 18;
	  public static final int DIV = 19;
	  public static final int MOD = 20;
	  public static final int OR = 21;
	  public static final int READ = 22;
	  public static final int WRITE = 23;
	  public static final int WRITELN = 24;

	  public static final int TRUE = 25;
	  public static final int FALSE = 26;
 
	  public static final int LPAREN = 27;
	  public static final int RPAREN = 28;
	  public static final int LBRACK = 29;
	  public static final int RBRACK = 30;
	  public static final int SEMICOLON = 31;
	  public static final int COMMA = 32;
	  public static final int DOT = 33;

	  public static final int EQ = 34;
	  public static final int GT = 35;
	  public static final int LT = 36;
	  public static final int NOT = 37;
	  public static final int LTEQ = 38;
	  public static final int GTEQ = 39;
	  public static final int NOTEQ = 40;
	  public static final int AND = 41;
	  public static final int PLUS = 42;
	  public static final int MINUS = 43;
	  public static final int MULT = 44; 	   

	  public static final int Integer = 45;
	  public static final int Comment = 46; 
	  public static final int WhiteSpace = 47;
	  public static final int Identifier = 48;

	  public static final int IllegalSymbol = -1;
	  public static final int IllegalInteger = -2;	
	  public static final int IllegalItegerRange = -3;	
	  public static final int IllegalOctal = -4;
	  public static final int IllegalIdentifierLength = -5;
	  public static final int MismatchedComment = -6;	

	  public static final int EOF = 0;
}