package exceptions;

/**
 * Missing left parenthesis.
 *
 * @author 
 * @author 
 * @version 
 **/
public class MissingLeftParenthesisException extends SyntacticException
{
	public MissingLeftParenthesisException() {
		this("Missing left parenthesis.");
	}
	public MissingLeftParenthesisException(String msg) {
		super(msg);
	}	

}
