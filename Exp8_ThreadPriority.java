public class Exp8_ThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread-1: " + i);
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread-2: " + i);
            }
        }, "Thread-2");
 
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Priority of Thread-1: " + t1.getPriority());
        System.out.println("Priority of Thread-2: " + t2.getPriority());

        t1.start();
        t2.start();
    }
}
