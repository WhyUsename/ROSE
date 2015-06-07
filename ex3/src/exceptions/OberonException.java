package exceptions;

public class OberonException extends Exception
{

    public OberonException ()
    {
        this("Error found in the program.");
    }

    public OberonException(String s)
    {
        super(s);
    }
}
