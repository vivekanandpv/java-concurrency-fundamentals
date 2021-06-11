package in.athenaeum;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //  Single-thread
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(4);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //  Scheduled Executor
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(() -> System.out.println("Hi there"), 2000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Hi there"), 2000, 5000, TimeUnit.MILLISECONDS);  //  after initial delay, scheduled at every 5000; start of the task delay
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Hi there"), 2000, 3000, TimeUnit.MILLISECONDS);   //  after initial delay, scheduled 3000 after the previous task; end of the task delay
    }
}
