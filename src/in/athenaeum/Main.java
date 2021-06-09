package in.athenaeum;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Sample sample = new Sample();

	    Thread t1 = new Thread(() -> {
	        sample.foo();
        });

	    Thread t2 = new Thread(() -> {
	       sample.bar();
        });

	    t1.setName("Thread 1");
	    t2.setName("Thread 2");

	    t1.start();
	    t2.start();

	    t1.join();
	    t2.join();
    }
}
