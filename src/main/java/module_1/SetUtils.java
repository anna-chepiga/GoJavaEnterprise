package module_1;

import java.util.*;

public class SetUtils {
   private Set<Integer> set;
   private int size;

   public SetUtils(int size, HashSet<Integer> hashSet) {
      this.size = size;
      this.set = hashSet;
   }

   public SetUtils(int size, TreeSet<Integer> treeSet) {
      this.size = size;
      this.set = treeSet;
   }

   private long sumPopulate = 0;
   private final int REPETITIONS = 100;

   private Random random = new Random(100);

   public void populate() {
      for (int i = 0; i < REPETITIONS; i++) {
         set.clear();
         long start = System.nanoTime();

         for (int j = 0; j < size; j++) {
            set.add(j);
         }

         long finish = System.nanoTime();
         long timeConsumed = finish - start;

         sumPopulate = sumPopulate + timeConsumed;
      }
   }

   private long calcPopulate() {
      return sumPopulate / REPETITIONS;
   }

   private long calcAdd() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         set.add(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum = sum + timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcContains() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         set.contains(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum = sum + timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcRemove() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         set.remove(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum = sum + timeConsumed;
      }

      return sum / REPETITIONS;
   }

   public String getResults() {
      return String.format("%-,10d%-10s%-,10d%-,10d%-,13d%-10s%-10s%n", calcAdd(), "---",
              calcRemove(), calcContains(), calcPopulate(), "---", "---");
   }
}