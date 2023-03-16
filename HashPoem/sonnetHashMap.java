import java.util.HashMap;
import java.util.Iterator;

public class sonnetHashMap {
    public static void main(String[] args) {
        // Initialize the HashMap and the poem
        HashMap<Character, Integer> map = new HashMap<>();
        String poem = "Oh HashMap, how thy keys and values entwine, " + 
                      "A perfect union, bound by code and fate. " + 
                      "Thy entries, stored in memory divine, " + 
                      "A treasure trove of data, sealed and great. " + 
                      "Thy methods, simple yet powerful, keep " + 
                      "Our data safe and accessible with ease. " + 
                      "With put and get, thy secrets we can reap, " + 
                      "And traverse thy structures with such ease. " + 
                      "Thy performance, too, is quite impressive, " + 
                      "With constant time for lookups, we're astound. " + 
                      "Thy simplicity and speed possessive, " + 
                      "Thy value, in our hearts, forever bound. " + 
                      "Oh HashMap, thy worth cannot be measured, " + 
                      "in code and data, thy worth is un-treasured.";
        
        // Convert poem to lowercase
        poem = poem.toLowerCase();
        
        // Split the poem into words
        String[] words = poem.split("\\s+");
        
        // Iterate through the words
        for (String word : words) {
            char firstLetter = word.charAt(0);
            if (map.containsKey(firstLetter)) {
                map.put(firstLetter, map.get(firstLetter) + 1);
            } else {
                map.put(firstLetter, 1);
            }
        }
        
        // Iterate through the HashMap and remove even counts
        Iterator<Character> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            char key = iterator.next();
            if (map.get(key) % 2 == 0) {
                iterator.remove();
            }
        }
        
        // Print the remaining entries
        for (char key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
        }
    }
}
