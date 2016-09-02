package module_2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class NumberExecutorTest {
    private static Task<Integer> intTask1 = new IntTask(20);
    private static Task<Integer> intTask2 = new IntTask(5);
    private static Task<Integer> intTask3 = new IntTask(100);
    private static Task<Long> longTask1 = new LongTask(40L);
    private static Task<Long> longTask2 = new LongTask(-10L);
    private static Task<Long> longTask3 = new LongTask(0L);

    private static Validator<Number> validator = new NumberValidator();

    private static List<Task<? extends Number>> taskList = asList(intTask1, intTask2, intTask3,
            longTask1, longTask2, longTask3);


    @Test
    public void addTask() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        for (Task<? extends Number> task : taskList) {
            executor.addTask(task);
        }

        List<Task<? extends Number>> tasks = executor.getTasks();

        assertEquals(taskList, tasks);
    }

    @Test(expected = ExecuteCalledException.class)
    public void addTaskException() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.execute();

        executor.addTask(intTask1);
    }

    @Test
    public void addTaskWithValidator() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(intTask2, validator);
        executor.addTask(longTask1, validator);

        executor.execute();

        Number expected1 = executor.getValidResults().get(0);
        Number expected2 = executor.getInvalidResults().get(0);

        assertEquals(expected1, 120L);
        assertEquals(expected2, 10);
    }

    @Test(expected = ExecuteNotCalledException.class)
    public void addTaskWithValidatorWithException() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(intTask1, validator);

        executor.getInvalidResults();
    }

    @Test
    public void execute() throws Exception {
        Executor<Number> executor = new NumberExecutor();
        executor.addTask(intTask1);
        executor.addTask(intTask2);
        executor.addTask(longTask1);

        executor.execute();

        List<Number> valid = Arrays.asList(40, 120L);
        List<Number> invalid = Arrays.asList(10);

        assertEquals(executor.getValidResults(), valid);
        assertEquals(executor.getInvalidResults(), invalid);
    }

    @Test
    public void getValidResults() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(intTask1);
        executor.addTask(intTask3);

        executor.execute();

        List<Number> valid = Arrays.asList(40, 200);

        assertEquals(executor.getValidResults(), valid);
    }

    @Test(expected = ExecuteNotCalledException.class)
    public void getValidResultsWithException() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(intTask1);

        executor.getValidResults();
    }

    @Test
    public void getInvalidResults() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(longTask3);

        executor.execute();

        List<Number> invalid = Arrays.asList(0L);

        assertEquals(executor.getInvalidResults(), invalid);
    }

    @Test(expected = ExecuteNotCalledException.class)
    public void getInvalidResultsWithException() throws Exception {
        Executor<Number> executor = new NumberExecutor();

        executor.addTask(intTask3);

        executor.getValidResults();
    }
}