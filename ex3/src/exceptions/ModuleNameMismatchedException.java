package exceptions;

// Referenced classes of package exceptions:
//            SemanticException

public class ModuleNameMismatchedException extends SemanticException
{

    public ModuleNameMismatchedException()
    {
        this("Module Name Mismatched Exception.");
    }

    public ModuleNameMismatchedException(String s)
    {
        super( s);
    }
}