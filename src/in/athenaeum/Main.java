package in.athenaeum;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            System.out.println("Running a task 1 on: " + Thread.currentThread().getId());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            System.out.println("Running a task 2 on: " + Thread.currentThread().getId());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executorService.submit(() -> {
            System.out.println("Running a task 3 on: " + Thread.currentThread().getId());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executorService.execute(() -> {
            System.out.println("Running a task 4 on: " + Thread.currentThread().getId());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
