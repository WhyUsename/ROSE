package exceptions;

// Referenced classes of package exceptions:
//            SyntacticException

public class MissingLeftParenthesisException extends SyntacticException
{

    public MissingLeftParenthesisException()
    {
        this("Missing LeftParenthesis Exception.");
    }

    public MissingLeftParenthesisException(String s)
    {
        super( s);
    }
}