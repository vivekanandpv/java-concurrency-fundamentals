package in.athenaeum;

public class IntWrapper {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increment() {
        ++counter;
    }

    public void decrement() {
        --counter;
    }
}
