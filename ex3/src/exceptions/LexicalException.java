package exceptions;

// Referenced classes of package exceptions:
//            OberonException

public class LexicalException extends OberonException
{

    public LexicalException()
    {
        this("Lexical error.");
    }

    public LexicalException(String s)
    {
        super("LexicalException :\n" + s);
    }
}
