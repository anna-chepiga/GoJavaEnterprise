package module_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumArray implements SquareSum {
   @Override
   public long getSquareSum(int[] values, int numberOfThreads)
           throws ExecutionException, InterruptedException {

      int[][] splitValues = new int[numberOfThreads][];
      int elements = values.length / numberOfThreads;

      int lastElements = values.length % numberOfThreads == 0 ? elements :
              elements + values.length % numberOfThreads;

      Phaser phaser = new Phaser(numberOfThreads);
      ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
      List<Future<Long>> list = new ArrayList<>();

      for (int i = 0; i < numberOfThreads; i++) {
         int position = i * elements;

         if (numberOfThreads - i == 1) {
            elements = lastElements;
         }

         final int[] valuesToCalculate = splitValues[i] = new int[elements];
         System.arraycopy(values, position, valuesToCalculate, 0, elements);

         Future<Long> future = executor.submit(() -> {
            long sum = 0;

            for (int element : valuesToCalculate) {
               int square = element * element;
               sum = sum + square;
            }

            phaser.arriveAndDeregister();

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