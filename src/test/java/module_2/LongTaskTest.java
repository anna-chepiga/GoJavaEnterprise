package module_2;

import org.junit.*;

import static org.junit.Assert.*;

public class LongTaskTest {
    @org.junit.Test
    public void testExecute() throws Exception {
        Task<Long> task = new LongTask(500L);
        task.execute();

        long result = task.getResult();

        assertEquals(result, 1500);
    }

}