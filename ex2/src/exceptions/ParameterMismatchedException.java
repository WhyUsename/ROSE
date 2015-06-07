/** 
 * @file ParameterMismatchedException.java
 * @brief type mismatched exceptions
 * @author 
 * @version 
 * @date 
 */
package exceptions;

@SuppressWarnings("serial")
public class ParameterMismatchedException extends SemanticException{
	String expected;
	String received;
	int line;
	/** 
	 * @brief ParameterMismatchedException 
	 * 
	 * @param expected type
	 * @param received type
	 * @param line where does error occur
	 */
	public ParameterMismatchedException(String expected, String received, int line){
		this.expected = expected;
		this.received = received;
		this.line = line;
	}

	public String toString(){
		return "Type mismatched at line " + line + " expected: " + expected + " but received " + received;
	}
}
