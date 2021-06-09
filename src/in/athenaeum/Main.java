package in.athenaeum;

public class Main {

    public static void main(String[] args) {
	    Runnable runnable = () -> {
            System.out.println("Executing " + Thread.currentThread().getName());
        };

	    Thread thread = new Thread(runnable);
	    thread.setName("Custom Thread");

	    thread.start(); //  thread.run() launches the task in the main thread
    }
}
