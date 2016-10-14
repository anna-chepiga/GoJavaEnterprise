import java.util.*;

public class ListUtils {
   private List<Integer> list;
   private int size;
   private Random random;

   public ListUtils(int size, ArrayList<Integer> arrayList) {
      this.size = size;
      this.list = arrayList;
      random = new Random(100);
   }

   public ListUtils(int size, LinkedList<Integer> linkedList) {
      this.size = size;
      this.list = linkedList;
      random = new Random(100);
   }

   private long sumPopulate = 0;
   private static final int REPETITIONS = 100;

   public void populate() {
      for (int i = 0; i < REPETITIONS; i++) {
         list.clear();
         long start = System.nanoTime();

         for (int j = 0; j < size; j++) {
            list.add(j, j);
         }

         long finish = System.nanoTime();
         long timeConsumed = finish - start;

         sumPopulate += timeConsumed;
      }
   }

   private long calcPopulate() {
      return sumPopulate / REPETITIONS;
   }

   private long calcAdd() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         list.add(i * random.nextInt(100), 666);
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcGet() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         list.get(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;

      }

      return sum / REPETITIONS;
   }

   private long calcContains() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         list.contains(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcIterAdd() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         list.listIterator().add(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcRemove() {
      long sum = 0;

      for (int i = 0; i < REPETITIONS; i++) {
         long start = System.nanoTime();
         list.remove(i * random.nextInt(100));
         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;
      }

      return sum / REPETITIONS;
   }

   private long calcIterRemove() {
      long sum = 0;

      for (int i = REPETITIONS * 5; i < REPETITIONS * 6; i++) {
         long start = System.nanoTime();

         Iterator<Integer> iter = list.listIterator();
         if (iter.next() % 2 == 0) {
            iter.remove();
         }

         long finish = System.nanoTime();

         long timeConsumed = finish - start;
         sum += timeConsumed;
      }

      return sum / REPETITIONS;
   }

   public String getResults() {
      return String.format("%-,10d%-,10d%-,10d%-,10d%-,13d%-,10d%-,10d%n", calcAdd(), calcGet(),
              calcRemove(), calcContains(), calcPopulate(), calcIterAdd(), calcIterRemove());
   }
}
