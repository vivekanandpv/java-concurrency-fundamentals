package in.athenaeum;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //  Using Callable, value can be returned. This is wrapped in Future<V>
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(500);
            return 500;
        });

        System.out.println("Return value: " + future.get());    //  future.get() is blocking

        Future<String> result = executorService.submit(() -> {
            if (true) {
                throw new RuntimeException("Oops!");
            }

            return "Hi there!";
        });

        //  When we use Callable, the exception can be propagated from the thread
        //  Actual exception is wrapped in ExecutionException
        try {
            System.out.println("String result: " + result.get());
        } catch (ExecutionException e) {
            System.out.println("Caught exception");
        }


        executorService.shutdown();
    }
}
