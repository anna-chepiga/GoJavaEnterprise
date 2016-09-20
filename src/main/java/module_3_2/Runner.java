package module_3_2;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Runner {
   public static void main(String[] args) {
      int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      int threads = 3;

      SquareSum sumArray = new SquareSumArray();

      try {
         long sum = sumArray.getSquareSum(array, threads);
         System.out.println("Array: " + Arrays.toString(array) + "\nSquare sum of elements: " + sum);
      } catch (ExecutionException | InterruptedException e) {
         e.printStackTrace();
      }
   }
}