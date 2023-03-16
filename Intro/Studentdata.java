import java.io.*;
import java.util.*;
public class Studentdata
{
	public Studentdata()
	{

		File name = new File("Data_intro.csv");  // File name must match that on computer
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";
			while((text=input.readLine())!= null) // Keep reading until end of file (null)
			{
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}
			System.out.println("FILE CONTENTS:\n"+output);

		}
		catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
	}
	public static void main(String[] args)
	{
		Studentdata app = new Studentdata();
	}
}