package Week_2.Threads;

import java.util.LinkedList;
import java.util.Queue;

class DataQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;

    public DataQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int data) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(data);
        System.out.println("Produced: " + data);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int data = queue.remove();
        System.out.println("Consumed: " + data);
        notifyAll();
        return data;
    }
}

class Producer extends Thread {
    private DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                dataQueue.produce(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                dataQueue.consume();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        DataQueue dataQueue = new DataQueue(5);
        Producer producer = new Producer(dataQueue);
        Consumer consumer = new Consumer(dataQueue);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Producer-Consumer demonstration completed");
    }
}
