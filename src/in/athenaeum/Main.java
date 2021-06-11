package in.athenaeum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(() -> {
            increment();
        });

        thread.start();

        thread.interrupt();

        thread.join();
    }

    public static void increment() {
        try {
            lock.lockInterruptibly();
            Thread.sleep(100);
            ++counter;
        } catch (InterruptedException e) {
            System.out.println("Interrupted...");
        }
    }
}
