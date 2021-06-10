package in.athenaeum;

public class Sample {
    private static final int[] buffer = new int[10];
    private static int counter = 0;

    public static boolean isFull() {
        return buffer.length == counter;
    }

    public static boolean isEmpty() {
        return counter == 0;
    }

    public static int getCounter() {
        return counter;
    }

    static class Producer {
        void produce() {
            while(isFull()) {}
            buffer[counter++] = 1;
        }
    }

    static class Consumer {
        void consume() {
            while(isEmpty()) {}
            buffer[--counter] = 0;
        }
    }
}
