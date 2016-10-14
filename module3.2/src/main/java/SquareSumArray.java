import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumArray implements SquareSum {
   @Override
   public long getSquareSum(int[] values, int amountOfThreads)
           throws ExecutionException, InterruptedException, IllegalStateException {

      if (amountOfThreads <= 0) throw new IllegalStateException("amount of threads cannot be negative or zero");

      if (values.length < amountOfThreads) {
         System.out.println("No logic in calculating " + values.length + " elements in " +
                 amountOfThreads + " threads, therefore changing amount of threads to 1");
         amountOfThreads = 1;
      }

      int elements = values.length / amountOfThreads;
      int remainder = values.length % amountOfThreads == 0 ? 0 : values.length % amountOfThreads;

      Phaser phaser = new Phaser(amountOfThreads);
      ExecutorService executor = Executors.newFixedThreadPool(amountOfThreads);
      List<Future<Long>> list = new ArrayList<>();

      for (int i = 0; i < amountOfThreads; i++) {
         int start = i * elements;
         int finish = i + 1 != amountOfThreads ? start + elements : start + elements + remainder;

         Future<Long> future = executor.submit(() -> {
            long sum = 0;
            for (int j = start; j < finish; j++) {
               sum += values[j] * values[j];
            }

            phaser.arriveAndAwaitAdvance();

            return sum;
         });

         list.add(future);

      }

      executor.shutdown();

      long squareSum = 0;
      for (Future<Long> future : list) {
         squareSum += future.get();
      }

      return squareSum;
   }
}