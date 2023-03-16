import java.util.HashMap;
import java.util.Map;

public class goldenratio {
    public static void main(String[] args) {
        String goldenRatio = "1.61803398874989484820458683436563811772030917980576286213544862270526046281890244970720720418939113748475408807538689175212663386222353693179318006076672635";
        Map<String, Integer> combinations = new HashMap<>();
        for (int i = 2; i < goldenRatio.length(); i++) {
            String combination = goldenRatio.substring(i - 2, i);
            if (combinations.containsKey(combination)) {
                combinations.put(combination, combinations.get(combination) + 1);
            } else {
                combinations.put(combination, 1);
            }
        }
        int totalCombinations = combinations.size();
        System.out.println("Total combinations: " + totalCombinations);
        for (Map.Entry<String, Integer> entry : combinations.entrySet()) {
            if (entry.getValue() > 4) {
                System.out.println("Combination " + entry.getKey() + " occurred " + entry.getValue() + " times.");
            }
        }
    }
}
