package module_2;

import org.junit.*;

import static org.junit.Assert.*;

public class IntTaskTest {
    @org.junit.Test
    public void testExecute() throws Exception {
        Task<Integer> task = new IntTask(400);
        task.execute();

        int result = task.getResult();

        assertEquals(result, 800);
    }

}