class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " is running " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

public class Exp6_CreateThreadClass {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread-A");
        MyThread thread2 = new MyThread("Thread-B");

        thread1.start();
        thread2.start();
    }
}
