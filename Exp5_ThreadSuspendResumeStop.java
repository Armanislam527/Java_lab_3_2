public class Exp5_ThreadSuspendResumeStop {
    static class ControlledThread extends Thread {
        private boolean suspended = false;
        private boolean stopped = false;

        public synchronized void suspendThread() {
            suspended = true;
        }

        public synchronized void resumeThread() {
            suspended = false;
            notifyAll();
        }

        public synchronized void stopThread() {
            stopped = true;
            notifyAll();
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                synchronized (this) {
                    while (suspended && !stopped) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (stopped) {
                        return;
                    }
                }
                System.out.println("Thread running: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ControlledThread thread = new ControlledThread();
        thread.start();
        Thread.sleep(1500);

        System.out.println("Suspending thread...");
        thread.suspendThread();
        Thread.sleep(1500);

        System.out.println("Resuming thread...");
        thread.resumeThread();
        Thread.sleep(1500);

        System.out.println("Stopping thread...");
        thread.stopThread();
        System.out.println("Program completed.");
    }
}
