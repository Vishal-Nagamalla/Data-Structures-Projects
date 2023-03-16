import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BowlingData {
public static void main(String[] args) {
    TreeMap<Integer, PriorityQueue<Bowler>> scoreMap = new TreeMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("BowlingData.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String firstName = data[0];
                String lastName = data[1];
                int score = Integer.parseInt(data[2]);

                Bowler bowler = new Bowler(firstName, lastName, score);

                if (scoreMap.containsKey(score)) {
                    PriorityQueue<Bowler> bowlers = scoreMap.get(score);
                    bowlers.add(bowler);
                } else {
                    PriorityQueue<Bowler> bowlers = new PriorityQueue<>();
                    bowlers.add(bowler);
                    scoreMap.put(score, bowlers);
                }
            }
            reader.close();

            for (Integer key : scoreMap.keySet()) {
                PriorityQueue<Bowler> bowlers = scoreMap.get(key);
                System.out.print(key + "=[");
                while (!bowlers.isEmpty()) {
                    Bowler bowler = bowlers.poll();
                    System.out.print(bowler.getFullName() + ",");
                }
                System.out.println("]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Bowler implements Comparable<Bowler> {
    private String firstName;
    private String lastName;
    private int score;

    public Bowler(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Bowler other) {
        int lastNameCompare = lastName.compareTo(other.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        } else {
            return firstName.compareTo(other.firstName);
        }
    }
}