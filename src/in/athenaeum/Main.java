package in.athenaeum;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        IntWrapper wrapper = new IntWrapper();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1_000_000; j++) {
                    wrapper.increment();
                    wrapper.decrement();
                }
            });

            threadList.add(thread);
        }

        for (Thread t : threadList) {
            t.start();
        }

        for (Thread t : threadList) {
            t.join();
        }

        System.out.println("Final counter: " + wrapper.getCounter());
    }
}
