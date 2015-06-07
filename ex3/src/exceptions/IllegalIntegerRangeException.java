package exceptions;

// Referenced classes of package exceptions:
//          	 LexicalException

public class IllegalIntegerRangeException extends LexicalException
{

    public IllegalIntegerRangeException()
    {
        this("Illegal IntegerRange: more than 12.");
    }

    public IllegalIntegerRangeException(String s)
    {
        super("Illegal IntegerRange: more than 12.\n"+s);
    }
}
