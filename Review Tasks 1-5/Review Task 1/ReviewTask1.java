import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class ReviewTask1 {
    public static String[] arr3;
    
    public static void main(String[] args) {
        fileReading();
        ReviewTask1 readingFromFile = new ReviewTask1();
        for(int i = 0; i<arr3.length; i++)
        {
            int num = Integer.parseInt(arr3[i]);
            System.out.println("Value at "+arr3[i]+": "+lucasNumbers(num));
        }
        

        

    }
    public static BigInteger lucasNumbers(int runNum)
    {
        BigInteger lastVal = BigInteger.valueOf(0);
        BigInteger newVal = BigInteger.valueOf(0);
       for(int i = 0; i<=runNum; i++)
       {
            if(i==0)
            {
                lastVal = BigInteger.valueOf(2);
            }
            else if(i==1)
            {
                newVal = BigInteger.valueOf(1);
            }
            else if(i>1)
            {
                BigInteger tempVal = newVal;
                newVal=newVal.add(lastVal);
                lastVal = tempVal;
            }
       }
       return newVal; 
    }
    public static void fileReading()
    {
        File readFile = new File("lucas.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(readFile));
			String readerText = "";
            String bigText = ""; 
			while((readerText=input.readLine())!= null)
			{
				bigText+=readerText +" ";
			}
			String[] theNumbers = bigText.split(" ");
            arr3 = theNumbers;
        } catch (IOException io) {
            System.out.println("Can't Read"+io);
        }
        

    }
    
}
    
