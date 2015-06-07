package exceptions;

/**
 * Missing operand.
 *
 * @author 
 * @author 
 * @version 
 **/
public class MissingOperandException extends SyntacticException
{
	public MissingOperandException() {
		this("Missing operand.");
	}
	public MissingOperandException(String msg) {
		super(msg);
	}	

}