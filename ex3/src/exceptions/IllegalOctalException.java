package exceptions;


// Referenced classes of package exceptions:
//            LexicalException

public class IllegalOctalException extends LexicalException
{

    public IllegalOctalException()
    {
        this("Illegal Octal number.");
    }

    public IllegalOctalException(String s)
    {
        super("Illegal Octal number.\n"+s);
    }
}
