package ua.goit.lib.operations.unary;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.lib.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationSquareRootDoubleTest {
    private Operation sqrtDouble = new OperationSquareRootDouble();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"sqrt", "18.44"}, true},
                {new String[]{"sqrt", String.valueOf(Double.MAX_VALUE)}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"log", "notNumber"}, false},
                {new String[]{"notOperation", "888"}, false},
                {new String[]{"50", "*", "-144.5"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() { // local coma symbol
        return new Object[][]{
                {new String[]{"sqrt", "16"}, "Sqrt(16,00) = 4,00"},
                {new String[]{"sqrt", "557.14"}, "Sqrt(557,14) = 23,60"},
                {new String[]{"sqrt", "-25"}, "Sqrt(-25,00) = NaN"},
        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("sqrt", sqrtDouble.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, sqrtDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, sqrtDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, sqrtDouble.calculate(input));
    }
}