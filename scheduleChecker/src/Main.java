public class Main {
    public static void main(String[] args) {
        scheduleChecker sc = new scheduleChecker("Schedule1.txt");
        if (sc.check()) {
            System.out.println("Schedule is Valid!");
        } else {
            System.out.println("Invalid Schedule, ERROR -> " + sc.getError());
        }
    }
}
