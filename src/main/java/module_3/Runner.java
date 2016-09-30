package module_3;

public class Runner {
   public static void main(String[] args) {
      Semaphore semaphore = new SemaphoreImpl(2);
      CommonResourceCounter counter = new CommonResourceCounter();

      new CountThread("Thread 1", semaphore, counter).start();
      new CountThread("Thread 2", semaphore, counter).start();
      new CountThread("Thread 3", semaphore, counter).start();
      new CountThread("Thread 4", semaphore, counter).start();
   }
}