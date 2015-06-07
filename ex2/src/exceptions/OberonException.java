package exceptions;
/** 
 * @file OberonException.java
 * @brief root of all errors in oberon
 * @author 
 * @version 
 * @date
 */

public class OberonException extends Exception
{
	private static final long serialVersionUID = 1L;
	public OberonException() {
		this("Compiler error.");
	}
	public OberonException(String msg) {
		super("Compiler error. (details:" + msg + ")");
	}
}
