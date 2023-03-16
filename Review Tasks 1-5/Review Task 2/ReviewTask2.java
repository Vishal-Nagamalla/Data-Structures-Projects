import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewTask2 {
    public static String[] arr; 
    public static void main(String[] args) {
        readerOfFile();
        for(int i = 0; i<arr.length; i+=2)
        {
            int number1 = i;
            int number2 = i+1;
            int readerNumber = Integer.parseInt(arr[number1]); 
            int readerNumber2 = Integer.parseInt(arr[number2]); 
            int bigNum = 0;
            ArrayList<Integer> arr = new ArrayList();
            for(int j = 1; j<readerNumber; j++)
            {
                if(readerNumber%j==0)
                {
                    bigNum+=j;
                    arr.add(j);
                }
            }
            


            int bigNum2 = 0;
            ArrayList<Integer> arr2 = new ArrayList();
            for(int j = 1; j<readerNumber2; j++)
            {
                if(readerNumber2%j==0)
                {
                    bigNum2+=j;
                    arr2.add(j);
                }
            }
           
            if(bigNum == readerNumber2 && bigNum2 == readerNumber)
            {
                System.out.println("The numbers "+readerNumber+" and "+ readerNumber2+" are amicable.");
    
            }
            else if(bigNum != readerNumber2 || bigNum2 != readerNumber)
            {
                System.out.println("The numbers "+readerNumber+" and "+ readerNumber2+" are not amicable.");
            }
            System.out.print("\t");
            printNumbers(arr, readerNumber);
            System.out.println(" Sum is "+bigNum+". ");
            System.out.print("\t");
            printNumbers(arr2, readerNumber2);
            System.out.println(" Sum is "+bigNum2+". ");
            System.out.println();
    }

        }
       
    public static void printNumbers(ArrayList<Integer> arr, int readerNumber)
    {
        if(arr.size()>2)
        {
        for(int i = 0; i<arr.size(); i++)
        {
            if(i<arr.size()-2)
            {
                if(i==0)
                {
                    System.out.print("Factors of "+readerNumber+" are "+arr.get(i)+", ");
                }
                else
                {
                    System.out.print(arr.get(i)+", ");
                }

            }
            else if(i==arr.size()-2)
            {
                System.out.print(arr.get(i)+" ");

            }
            else if(i==arr.size()-1)
            {
                System.out.print("and "+arr.get(i)+".");
            }
        }
    }
    else if(arr.size()==2)
    {
        for(int i = 0; i<arr.size(); i++)
        {
            System.out.print("Factors of "+readerNumber+" are "+arr.get(i)+", ");
        }
    }
    else if(arr.size()==1)
    {
        for(int i = 0; i<arr.size(); i++)
        {
            System.out.print("The only factor of "+readerNumber+" is "+arr.get(i)+". ");
        }
    }
    else if(arr.size()==0)
    {
        for(int i = 0; i<arr.size(); i++)
        {
            System.out.print("There are no factors of "+readerNumber);
        }
    }
    }
    public static void readerOfFile()
    {
        File readFile = new File("amicableOrNot.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(readFile));
			String readerText = "";
            String bigText = ""; 
			while((readerText=input.readLine())!= null)
			{
				bigText+=readerText +" ";
			}
			String[] theNumbers = bigText.split(" ");
            arr = theNumbers;
        } catch (IOException io) {
            System.out.println("Can't Read"+io);
        }


    }




}