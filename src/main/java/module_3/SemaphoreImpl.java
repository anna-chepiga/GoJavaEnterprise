package module_3;

public class SemaphoreImpl implements Semaphore {
    private int availablePermits = 0;
    private final Object lock = new Object();

    public SemaphoreImpl(int permits) {
        availablePermits = permits;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (lock) {
            while (availablePermits == 0) {
                lock.wait();
            }
            availablePermits--;
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        synchronized (lock) {
            while (availablePermits == 0) {
                lock.wait();
            }
            availablePermits -= permits;
        }
    }

    @Override
    public void release() {
        synchronized (lock) {
            if (availablePermits == 0) {
                lock.notify();
            }
            availablePermits++;
        }
    }

    @Override
    public void release(int permits) {
        synchronized (lock) {
            if (availablePermits == 0) {
                lock.notifyAll();
            }
            availablePermits += permits;
        }
    }

    @Override
    public int getAvailablePermits() {
        return availablePermits;
    }
}