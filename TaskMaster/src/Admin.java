import java.text.NumberFormat;
import java.util.Queue;

public class Admin {
    public Admin() {

    }

    public boolean isViable(Task task, Queue<Worker> workers) {
        // CHECK WORKER CAPACITY & HOURLY WAGE PER WORKER
        return task.getWorkers() <= workers.size() && (task.getMoney()/((double)task.getTime()/60)/task.getWorkers()) >= 15;
    }

    public void timerUserReport(Queue<Worker> workers) {
        int totalTime = 0;
        int totalIdle = 0;
        Worker maxWorker = workers.peek();
        Worker minWorker = workers.peek();

        for (Worker worker : workers) {
            totalTime += worker.getWorkingTime();
            totalIdle += worker.getIdleTime();

            if (worker.getWorkingTime() > maxWorker.getWorkingTime())
                maxWorker = worker;
            else if (worker.getWorkingTime() < maxWorker.getWorkingTime())
                minWorker = worker;
        }

        double avgTime = totalTime / workers.size();
        double avgIdle = totalIdle / workers.size();

        System.out.println("\nTimer user report:");
        System.out.println("Total time worked = " + (int)(totalTime/60) + "hrs " + (int)(totalTime%60) + "mins");
        System.out.println("Total idle time worked = " + (int)(totalIdle/60) + "hrs " + (int)(totalIdle%60) + "mins");

        System.out.println("Average working hours per worker = " + (int)(avgTime/60) + "hrs " + (int)(avgTime%60) + "mins");
        System.out.println("Average idle time per worker = " + (int)(avgIdle/60) + "hrs " + (int)(avgIdle%60) + "mins");

        System.out.println("Most hours worked => " + maxWorker.getName() + " " + (int)(maxWorker.getWorkingTime()/60) + "hrs " + (int)(maxWorker.getWorkingTime()%60) + "mins");
        System.out.println("Least hours worked => " + minWorker.getName() + " "+ (int)(minWorker.getWorkingTime()/60) + "hrs " + (int)(minWorker.getWorkingTime()%60) + "mins\n");
    }

    public void wageReport(Queue<Worker> workers) {
        double totalMoney = 0;
        int totalTime = 0;
        int totalIdleTime = 0;
        NumberFormat nF = NumberFormat.getCurrencyInstance();

        for(Worker worker : workers) {
            totalMoney += worker.getMoneyEarned();
            totalTime += worker.getWorkingTime();
            totalIdleTime += worker.getIdleTime();
        }

        System.out.println("\nWage Report: ");
        System.out.println("Total Money Earned => " + nF.format(totalMoney));
        System.out.println("Average Money Earned per working hour => " + nF.format(totalMoney/(totalTime/60.0)));
        System.out.println("Average Money Earned per elapsed hour => " + nF.format(totalMoney/((totalTime+totalIdleTime)/60.0)) + "\n");
    }

    public void workerReport(Queue<Worker> workers) {
        NumberFormat nF = NumberFormat.getCurrencyInstance();
        System.out.println("\nWorker Report: ");
        System.out.printf("%-7s | %-9s | %-9s | %-8s | %-9s | %-12s |\n",
                "Name", "Work Time", "Idle Time", "$Total", "$/Work Hr", "$/Elapsed Hr");
        System.out.println("-----------------------------------------------------------------------");

        for(Worker worker : workers) {
            String name = worker.getName();
            String workTime = (int)(worker.getWorkingTime()/60) + "hrs " + (int)(worker.getWorkingTime()%60) + "m";
            String idleTime = (int)(worker.getIdleTime()/60) + "hrs " + (int)(worker.getIdleTime()%60) + "m";
            String totalMoney = nF.format(worker.getMoneyEarned());
            String workHour = nF.format(worker.getMoneyEarned()/(worker.getWorkingTime()/60.0));
            String workTotalHour = nF.format(worker.getMoneyEarned()/((worker.getWorkingTime()+worker.getIdleTime())/60.0));

            System.out.printf("%-7s | %-9s | %-9s | %-8s | %-9s | %-12s |", name,
                    workTime, idleTime, totalMoney, workHour, workTotalHour);
            System.out.println();
        }
    }
}
