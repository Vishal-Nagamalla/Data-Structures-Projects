import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Task {
    private int time;
    private int workers;
    private double money;
    private Queue<Worker> currentWorkers;

    public Task(int time, int workers, double money) {
        this.time = time;
        this.workers = workers;
        this.money = money;
        this.currentWorkers = new LinkedList<>();
    }

    public int getTime() {
        return time;
    }

    public int getWorkers() {
        return workers;
    }

    public double getMoney() {
        return money;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addWorker(Worker worker) {
        currentWorkers.add(worker);
    }

    public Worker removeWorker() {
        return currentWorkers.poll();
    }

    public void updateWorkers() {
        for(Worker worker : currentWorkers) {
            worker.incWorkingTime(1);
            worker.incMoney(getMoney()/((double)getTime())/getWorkers());
        }
    }

    @Override
    public String toString() {
        return "{" + time + ", " + workers + ", " + money + "}";
    }
}
