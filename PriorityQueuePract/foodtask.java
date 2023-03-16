import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class foodtask {
    public static void main(String[] args){
        PriorityQueue<String>[] pq = new PriorityQueue[24];

        for(int i = 0; i < 24; i++){
            pq[i] = new PriorityQueue<String>();
        }

        File name = new File("foods.txt"); 
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name)); 
			String text,output="";
			while((text=input.readLine())!= null){
                pq[output.length()].add(output);
            }
		}catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
    }
}
