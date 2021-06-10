package in.athenaeum;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Sample.Producer producer = new Sample.Producer();
	    Sample.Consumer consumer = new Sample.Consumer();

	    Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }

            System.out.println("Producer completes");
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                consumer.consume();
            }

            System.out.println("Consumer completes");
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Final counter: " + Sample.getCounter());
    }
}
