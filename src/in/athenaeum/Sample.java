package in.athenaeum;

public class Sample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void foo() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " is executing foo()");
            bar();
        }
    }

    public void bar() {
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " is executing bar()");
            baz();
        }
    }

    public void baz() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " is executing baz()");
        }
    }
}
