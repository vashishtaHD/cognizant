package Week_2.Threads;

import java.util.Random;

class Sensor extends Thread {
    private String name;
    private Random random = new Random();

    public Sensor(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " reading: " + random.nextInt(100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SensorSimulation {
    public static void main(String[] args) throws InterruptedException {
        Sensor[] sensors = new Sensor[3];
        for (int i = 0; i < sensors.length; i++) {
            sensors[i] = new Sensor("Sensor " + (i + 1));
            sensors[i].start();
        }

        for (Sensor sensor : sensors) {
            sensor.join();
        }

        System.out.println("All sensors have completed data collection.");
    }
}
