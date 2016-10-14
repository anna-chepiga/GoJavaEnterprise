public interface Semaphore {
    void acquire() throws InterruptedException;

    void acquire(int permits) throws InterruptedException;

    void release();

    void release(int permits);

    int getAvailablePermits();
}