package exceptions;

// Referenced classes of package exceptions:
//            SemanticException

public class TypeMismatchedException extends SemanticException
{

    public TypeMismatchedException()
    {
        this("Type Mismatched Exception.");
    }

    public TypeMismatchedException(String s)
    {
        super(s);
    }
}