package exceptions;
/** 
 * @file SyntacticException.java
 * @brief Syntactic exceptions
 * @author 
 * @version 
 * @date 
 */

public class SyntacticException extends OberonException
{
	private static final long serialVersionUID = 1L;
	public SyntacticException() {
		this("Syntactic error.");
	}
	public SyntacticException(String msg) {
		super("Syntactic error. (details:" + msg + ")");
	}
}
