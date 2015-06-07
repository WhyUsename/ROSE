package exceptions;


// Referenced classes of package exceptions:
//            LexicalException

public class MismatchedCommentException extends LexicalException
{

    public MismatchedCommentException()
    {
        this("Mismatched Comment.");
    }

    public MismatchedCommentException(String s)
    {
        super("Mismatched Comment.\n"+s);
    }
}
