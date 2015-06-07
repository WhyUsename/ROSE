package exceptions;

// Referenced classes of package exceptions:
//            SemanticException

public class ProcedureNameMismatchedException extends SemanticException
{

    public ProcedureNameMismatchedException()
    {
        this("Procedure Name Mismatched Exception.");
    }

    public ProcedureNameMismatchedException(String s)
    {
        super(s);
    }
}