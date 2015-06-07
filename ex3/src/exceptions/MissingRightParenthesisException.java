package exceptions;

// Referenced classes of package exceptions:
//            SyntacticException

public class MissingRightParenthesisException extends SyntacticException
{

    public MissingRightParenthesisException()
    {
        this("Missing Right Parenthesis Exception.");
    }

    public MissingRightParenthesisException(String s)
    {
        super(s);
    }
}