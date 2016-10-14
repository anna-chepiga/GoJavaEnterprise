import static org.junit.Assert.assertEquals;

public class IntTaskTest {
    @org.junit.Test
    public void testExecute() throws Exception {
        Task<Integer> task = new IntTask(400);
        task.execute();

        int result = task.getResult();

        assertEquals(result, 800);
    }

}