import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberValidatorTest {
    private static Validator<Number> validator = new NumberValidator();

    @Test
    public void isValidTrue() throws Exception {
        assertTrue(validator.isValid(50));
    }

    @Test
    public void isValidFalse() throws Exception {
        assertFalse(validator.isValid(1));
    }
}