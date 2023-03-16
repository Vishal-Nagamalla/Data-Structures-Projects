import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class birthdays {
  public static void main(String[] args) {
    int numTrials = 100;
    int totalPeople = 0;
    Random rand = new Random();

    for (int i = 0; i < numTrials; i++) {
      Set<Integer> birthdays = new HashSet<>();
      int numPeople = 0;
      while (birthdays.size() < 365) {
        numPeople++;
        birthdays.add(rand.nextInt(365) + 1);
      }
      totalPeople += numPeople;
    }

    double averagePeople = (double) totalPeople / numTrials;
    System.out.println("Average number of people needed: " + averagePeople);
  }
}
