public class Worker {
    private String name;
    private int idleTime;
    private int workingTime;
    private int timeLeft;
    private double moneyEarned;
    private boolean working;

    public Worker(String name) {
        this.name = name;
        this.idleTime = 0;
        this.workingTime = 0;
        this.timeLeft = 0;
        this.moneyEarned = 0;
        this.working = false;
    }

    public String getName() {
        return this.name;
    }
    public int getIdleTime() {
        return this.idleTime;
    }
    public int getWorkingTime() {
        return workingTime;
    }
    public int getTimeLeft() {
        return timeLeft;
    }
    public double getMoneyEarned() {
        return moneyEarned;
    }
    public boolean isWorking() {
        return working;
    }

    public void incIdleTime(int amount) {
        this.idleTime += amount;
    }
    public void incWorkingTime(int amount) {
        this.workingTime += amount;
    }
    public void incMoney(double amount) {
        this.moneyEarned += amount;
    }
    public void setTimeLeft(int val) {
        this.timeLeft = val;
    }
    public void decreaseTimeLeft(int amount) {
        this.timeLeft -= amount;
    }
    public void setWorking(boolean val) {
        this.working = val;
    }

    public String toString() {
        return getName() + ", " + getWorkingTime() + " minutes worked, " + getIdleTime() + " minutes idle, " + getMoneyEarned() + " earned";
    }
}
