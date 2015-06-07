package exceptions;

// Referenced classes of package exceptions:
//            LexicalException

public class IllegalSymbolException extends LexicalException
{

    public IllegalSymbolException()
    {
        this("Illegal Symbol.");
    }

    public IllegalSymbolException(String s)
    {
        super("Illegal Symbol.\n"+s);
    }
}
