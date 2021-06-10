package in.athenaeum;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Sample.Producer producer = new Sample.Producer();
	    Sample.Consumer consumer = new Sample.Consumer();

	    Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Producer completes");
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    consumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
