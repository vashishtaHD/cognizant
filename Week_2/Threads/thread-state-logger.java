package Week_2.Threads;

class SimpleTask extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Task running: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadStateLogger {
    public static void main(String[] args) throws InterruptedException {
        SimpleTask task = new SimpleTask();

        System.out.println("Before starting: " + task.getState());

        task.start();
        System.out.println("After starting: " + task.getState());

        Thread.sleep(2500);
        System.out.println("During execution: " + task.getState());

        task.join();
        System.out.println("After completion: " + task.getState());
    }
}
