package exceptions;

// Referenced classes of package exceptions:
//            SyntacticException

public class MissingOperatorException extends SyntacticException
{

    public MissingOperatorException()
    {
        this("Missing Operator Exception.");
    }

    public MissingOperatorException(String s)
    {
        super(s);
    }
}