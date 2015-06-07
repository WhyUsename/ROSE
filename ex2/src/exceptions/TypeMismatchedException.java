package exceptions;

/**
 * Type mismatched founded. 
 *
 * @author 
 * @author 
 * @version 
 */
public class TypeMismatchedException extends SemanticException
{
	public TypeMismatchedException(){
		this("Type is not matching.");
	}
	public TypeMismatchedException(String msg){
		super(msg);
	}
}
