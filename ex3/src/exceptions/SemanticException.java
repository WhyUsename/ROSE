package exceptions;

// Referenced classes of package exceptions:
//            OberonException

public class SemanticException extends OberonException
{

    public SemanticException()
    {
        this("Semantic Exception.");
    }

    public SemanticException(String s)
    {
        super("Semantic Exception: "+s);
    }
}
