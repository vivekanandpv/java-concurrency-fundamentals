package in.athenaeum;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //  Single-thread
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        //  shutdown is a graceful method; soft-way
        //  no further tasks are enqueued
        //  service will wait for all queued tasks to complete

        //  shutdownNow is a hard way
        //  halt all the running tasks; do not execute the already queued but yet to start tasks
        //  then shutdown

        //  awaitTermination(timeout)
        //  it will first issue a shutdown and waits for the timeout
        //  if the service is not shutdown, it will do the hard shutdown then

    }
}
