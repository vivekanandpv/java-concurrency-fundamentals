package in.athenaeum;

public class Sample {
    private static final int[] buffer = new int[10];
    private static int counter = 0;
    private static Object lock = new Object();

    public static boolean isFull() {
        return buffer.length == counter;
    }

    public static boolean isEmpty() {
        return counter == 0;
    }

    public static int getCounter() {
        return counter;
    }

    static class Producer {
        void produce() throws InterruptedException {
            synchronized (lock) {
                if (isFull()) {
                    lock.wait();
                }
                buffer[counter++] = 1;
                lock.notify();
            }
        }
    }

    static class Consumer {
        void consume() throws InterruptedException {
            synchronized (lock) {
                if (isEmpty()) {
                    lock.wait();
                }
                buffer[--counter] = 0;
                lock.notify();
            }
        }
    }
}
