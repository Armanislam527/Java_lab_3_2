class DemoThread extends Thread {
    public DemoThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " => " + i);
            Thread.yield();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

public class Exp7_YieldStopSleepThread {
    public static void main(String[] args) throws InterruptedException {
        DemoThread t1 = new DemoThread("Thread-1");
        DemoThread t2 = new DemoThread("Thread-2");

        t1.start();
        t2.start();

        Thread.sleep(800);
        System.out.println("Stopping Thread-1 using stop()...");
        t1.stop();

        System.out.println("Stopping Thread-2 using stop()...");
        t2.stop();
    }
}
