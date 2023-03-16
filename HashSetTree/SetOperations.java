import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class SetOperations {
    public static void main(String[] args) {
        ArrayList<HashSet<Integer>> sets = new ArrayList<>();
        Random rand = new Random();
        int numSets = rand.nextInt(6) + 3; 
        for (int i = 0; i < numSets; i++) {
            HashSet<Integer> set = new HashSet<>();
            while (set.size() < 10) {
                set.add(rand.nextInt(30) + 1);
            }
            sets.add(set);
        }

        for (int i = 0; i < sets.size(); i++) {
            System.out.println("Set " + (i + 1) + " => " + sets.get(i));
        }

        HashSet<Integer> intersection = intersection(sets.get(0), sets.get(1));
        for (int i = 2; i < sets.size(); i++) {
            intersection = intersection(intersection, sets.get(i));
        }

        HashSet<Integer> union = union(sets.get(0), sets.get(1));
        for (int i = 2; i < sets.size(); i++) {
            union = union(union, sets.get(i));
        }

        System.out.println("Intersection => " + intersection);
        System.out.println("Union => " + union);
    }

    public static HashSet<Integer> intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public static HashSet<Integer> union(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }
}
