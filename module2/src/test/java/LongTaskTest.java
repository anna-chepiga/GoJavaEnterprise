import static org.junit.Assert.assertEquals;

public class LongTaskTest {
    @org.junit.Test
    public void testExecute() throws Exception {
        Task<Long> task = new LongTask(500L);
        task.execute();

        long result = task.getResult();

        assertEquals(result, 1500);
    }
}