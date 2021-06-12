package in.athenaeum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private static Map<String, String> cacheMap = new HashMap<>();
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Future<Integer>> putFutures = new ArrayList<>();
        List<Future<String>> getFutures = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            int j = i;
            Future<Integer> future = service.submit(() -> {
                set("A"+j, "Country-" +j);
                return j;
            });
            putFutures.add(future);
        }

        for(Future<Integer> f: putFutures) {
            System.out.println("Added..." + f.get());
        }

        for (int i = 1; i <= 4; i++) {
            int j = i;
            Future<String> future = service.submit(() -> get("A"+j));
            getFutures.add(future);
        }

        for(Future<String> f: getFutures) {
            System.out.println(f.get());
        }

        service.shutdown();
    }

    public static String get(String key) throws InterruptedException {
        try {
            readLock.lock();
            Thread.sleep(500);
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static void set(String key, String value) throws InterruptedException {
        try {
            writeLock.lock();
            Thread.sleep(1000);
            cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
