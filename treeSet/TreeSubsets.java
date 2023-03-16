import java.util.*;
import java.io.*;

public class TreeSubsets {
  public static void main(String[] args) {
    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int i = 0; i < 30; i++)
      ts.add((int)(Math.random()*100+1));

    // Create a subset of all elements >= 80
    TreeSet<Integer> tail = (TreeSet<Integer>)ts.tailSet(80);

    // Print the new Set
    for (int i : tail)
      System.out.print(i+" ");
    System.out.println();

    TreeSet<String> words = new TreeSet<String>();

    try {
        BufferedReader input = new BufferedReader(new FileReader("sgb-words.txt"));
        String word = "";
        while((word = input.readLine()) != null) {
            words.add(word);
        }
        input.close();
    } catch (IOException io) {
        System.err.println("Error reading file => " + io);
    }
    
    // Get the subset of words from "thing" to "throw"
    SortedSet<String> subset = words.subSet("thing", "throw");
    
    // Print the subset
    for (String w : subset) {
        System.out.println(w);
    }
    
	try
	{
		BufferedReader input = new BufferedReader(new FileReader("/Users/vishal/Desktop/Programming/Data Structures Projects/Sets Tasks/treeSet/sgb-words.txt"));  // This object reads text line-by-line
		String word = "";
		while((word=input.readLine())!= null) // Keep reading until end of file (null)
		{
			//load
		}

	}
	catch (IOException io)
	{
		System.err.println("Error reading file => "+io);
	}
  }
}