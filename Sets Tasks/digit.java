import java.util.HashSet;
import java.util.Set;

public class digit {
  public static void main(String[] args) {
    String number = "1562836047588678243489701526384857673";
    Set<Character> digits = new HashSet<>();

    for (int i = 0; i < number.length(); i++) {
      char digit = number.charAt(i);
      if (!digits.contains(digit)) {
        digits.add(digit);
        if (digits.size() == 10) {
          System.out.println("The index of the digit that completes the set is: " + i);
          break;
        }
      }
    }
  }
}
