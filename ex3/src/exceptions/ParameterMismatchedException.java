package exceptions;

// Referenced classes of package exceptions:
//            SemanticException

public class ParameterMismatchedException extends SemanticException
{

    public ParameterMismatchedException()
    {
        this("Parameter Mismatched Exception.");
    }

    public ParameterMismatchedException(String s)
    {
        super(s);
    }
}