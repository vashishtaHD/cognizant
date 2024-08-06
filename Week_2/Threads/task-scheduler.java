package Week_2.Threads;

class Task extends Thread {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " started");

        try {
            System.out.println(name + " sleeping");
            Thread.sleep(2000);

            System.out.println(name + " yielding");
            Thread.yield();

            System.out.println(name + " resuming");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " completed");
    }
}

public class TaskScheduler {
    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        task1.start();
        task2.start();

        task1.join();
        System.out.println("Task 1 joined");

        task3.start();

        task2.join();
        task3.join();

        System.out.println("All tasks completed");
    }
}
