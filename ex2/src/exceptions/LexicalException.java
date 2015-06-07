package exceptions;
/** 
 * @file LexicalException.java
 * @brief lexical exceptions
 * @author 
 * @version 
 * @date 
 */

public class LexicalException extends OberonException
{
	private static final long serialVersionUID = 1L;
	public LexicalException() {
		this("Lexical error.");
	}
	public LexicalException(String msg) {
		super("Lexical error. (details:" + msg + ")");
	}
}
