package exceptions;

// Referenced classes of package exceptions:
//            SyntacticException

public class MissingOperandException extends SyntacticException
{

    public MissingOperandException()
    {
        this("Missing Operand Exception.");
    }

    public MissingOperandException(String s)
    {
        super(s);
    }
}