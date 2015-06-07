/**
 * 
 * @author Zemin Chen
 * update 10-07-02
 * when get a token, return a Symbol onto the OberonParser.
 * Symbol includes :
 * int sym, whick tag the token,
 * String name, the content of the token.
 */

public class Symbol 
{
	public int sym;
	public String name;
	
	public Symbol(int sym, String name)
	{
		this.sym = sym;
		this.name = name;
	}
	
	public Symbol(int sym)
	{
		this.sym = sym;
		this.name = null;
	}
}
