import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Master {
    public Master() {
        // INITIALIZE HELPER CLASSES
        Admin admin = new Admin();

        // INITIALIZE WORKER AND TASK QUEUES
        Queue<Worker> workers = new LinkedList<>();
        Queue<Task> tasks = new LinkedList<>();

        // INITIALIZE LISTS OF CURRENT TASKS BEING WORKED ON
        ArrayList<Task> currentTasks = new ArrayList<>();

        BufferedReader input = null;
        try {
            // READ FILE
            input = new BufferedReader(new FileReader("TaskData.txt"));

            // LOAD WORKERS
            String[] workerData = input.readLine().trim().split(",");
            for(String s : workerData)
                workers.add(new Worker(s));

            // LOAD TASKS
            String text;
            while((text = input.readLine()) != null) {
                String[] taskData = text.trim().split(",");
                Task task = new Task(Integer.parseInt(taskData[0]), Integer.parseInt(taskData[1]), Integer.parseInt(taskData[2]));

                if(admin.isViable(task, workers))
                    tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Viable Tasks --> " + tasks);

        // START NEW TIMER THREAD
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // CHECK IF ALL TASKS HAVE BEEN COMPLETED
                if (!currentTasks.isEmpty() || !tasks.isEmpty()) {

                    // HANDLE TASKS THAT NEED TO BE DONE
                    if(!tasks.isEmpty()) {
                        Task currentTask = tasks.peek();

                        if (workers.size() >= currentTask.getWorkers()) {
                            for (int i = 0; i < currentTask.getWorkers(); i++)
                                currentTask.addWorker(workers.poll());
                            currentTasks.add(tasks.poll());
                            System.out.println(currentTask + " --> TASK ADDED TO CURRENT TASKS");
                        } else {
                            for (Worker worker : workers) {
                                worker.incIdleTime(1);
                            }
                        }
                    }

                    // HANDLE TASKS THAT ARE BEING WORKED ON
                    for (int i = 0; i < currentTasks.size(); i++) {
                        Task task = currentTasks.get(i);
                        task.updateWorkers();
                        task.setTime(task.getTime() - 1);

                        if (task.getTime() == 0) {
                            System.out.println(task + " --> TASK COMPLETED");
                            currentTasks.remove(task);

                            for (int j = 0; j < task.getWorkers(); j++)
                                workers.add(task.removeWorker());
                        }
                    }
                } else {
                    // HANDLE REPORTS
                    System.out.println("\nALL TASKS COMPLETED");

                    admin.timerUserReport(workers);
                    admin.workerReport(workers);
                    admin.wageReport(workers);

                    this.cancel();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, new Date(), 50);
    }
}
