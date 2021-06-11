package in.athenaeum;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sample {
    private static final int[] buffer = new int[10];
    private static int counter = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

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
            try {
                lock.lock();
                if (isFull()) {
                    notFull.await();
                }
                buffer[counter++] = 1;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer {
        void consume() throws InterruptedException {
            try {
                lock.lock();
                if (isEmpty()) {
                    notEmpty.await();
                }
                buffer[--counter] = 0;
                notFull.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
