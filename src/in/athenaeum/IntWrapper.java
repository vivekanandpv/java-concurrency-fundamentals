package in.athenaeum;

public class IntWrapper {
    private int counter = 0;
    private final Object lock = new Object();

    public int getCounter() {
        return counter;
    }

    public void increment() {
        synchronized (lock) {
            ++counter;
        }
    }

    public void decrement() {
        synchronized (lock) {
            --counter;
        }
    }
}
