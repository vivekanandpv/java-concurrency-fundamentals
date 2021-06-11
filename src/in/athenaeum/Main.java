package in.athenaeum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int j = i;
            Future<Integer> future = service.submit(() -> {
                doTask(j);
                return 0;
            });

            futures.add(future);
        }

        for(Future<Integer> f : futures) {
            f.get();
        }

        service.shutdown();
    }

    public static void doTask(int i) throws InterruptedException {
        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println("Starting the task..." + i);
                Thread.sleep(1000);
                System.out.println("Completing the task..." + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Doing another task..." + i);
        }
    }
}
