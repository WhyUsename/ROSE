//Main.java
import java.io.*;
public class Main
{
	public static void main(String argv[])throws Exception
	{
		if (argv.length == 0)
		{
			System.out.println("Usage : java Main <inputfile>");
		}
		else
		{
			for (int i = 0; i < argv.length; i++) 
			{
				OberonScanner obj = new OberonScanner(new java.io.FileReader(argv[i]));
				Parser p=new Parser(obj);
				System.out.println(argv[i] + ":");
				try
				{	
					p.parse();
				}
				catch(Exception ex)
				{
					System.out.println(obj.get_line()+" line "+ obj.get_column()+" column");
					System.out.println(ex.getMessage());
				}
				System.out.println();
			}
		}
	}
}