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
        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Future<Integer> future = service.submit(() -> {
                for (int j = 0; j < 10_000_000; j++) {
                    increment();
                    decrement();
                }

                return 0;
            });

            futures.add(future);
        }

        for(Future<Integer> f: futures) {
            f.get();
        }



        System.out.println("Final count: " + counter);

        service.shutdown();

    }

    public static void increment() {
        try {
            lock.lock();
            ++counter;
        } finally {
            lock.unlock();
        }
    }

    public static void decrement() {
        try {
            lock.lock();
            --counter;
        } finally {
            lock.unlock();
        }
    }
}
