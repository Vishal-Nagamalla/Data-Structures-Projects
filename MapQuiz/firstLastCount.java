import java.util.HashMap;
import java.util.Map;

public class firstLastCount {
    public int firstLastCount(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int result = 0;

        for (String word : words) {
            String key = word.charAt(0) + "" + word.charAt(word.length() - 1);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (int value : map.values()) {
            if (value > 2) {
                result += value;
            }
        }
        return result;
    }    
}