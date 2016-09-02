package module_2;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Task<Integer> task1 = new IntTask(10);
        Task<Integer> task2 = new IntTask(20);
        Task<Integer> task3 = new IntTask(30);

        List<Task<Integer>> integerTasks = Arrays.asList(task1, task2, task3);

        Executor<Number> numberExecutor = new NumberExecutor();

        try {
            for (Task<Integer> intTask : integerTasks) {
                numberExecutor.addTask(intTask);
            }

            //numberExecutor.execute();

            Task<Long> l = new LongTask(15L);

            numberExecutor.addTask(l, new NumberValidator());

            numberExecutor.execute();

            System.out.println("Valid results:");
            numberExecutor.getValidResults().forEach(System.out::println);

            System.out.println("Invalid results:");
            numberExecutor.getInvalidResults().forEach(System.out::println);
        } catch (ExecuteCalledException | ExecuteNotCalledException e) {
            System.out.println(e.getMessage());
        }
    }
}