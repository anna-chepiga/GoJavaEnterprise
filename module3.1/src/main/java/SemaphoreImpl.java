public class SemaphoreImpl implements Semaphore {
   private int availablePermits;
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
         while (availablePermits < permits) {
            lock.wait();
         }
         availablePermits -= permits;
      }
   }

   @Override
   public void release() {
      synchronized (lock) {
         availablePermits++;
         lock.notify();
      }
   }

   @Override
   public void release(int permits) {
      synchronized (lock) {
         availablePermits += permits;
         lock.notifyAll();
      }
   }

   @Override
   public int getAvailablePermits() {
      return availablePermits;
   }
}