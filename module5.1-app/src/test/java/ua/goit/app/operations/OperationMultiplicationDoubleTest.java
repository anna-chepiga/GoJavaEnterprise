package ua.goit.app.operations;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.calculator.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationMultiplicationDoubleTest {
    Operation multiplicationDouble = new OperationMultiplicationDouble();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"5", "*", "88.02"}, true},
                {new String[]{String.valueOf(Double.MAX_VALUE), "*", "-15.6"}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"five", "*", "88.02"}, false},
                {new String[]{"notNumber", "*", "-15.6"}, false},
                {new String[]{"50", "-", "-144.5"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() { // local coma symbol
        return new Object[][]{
                {new String[]{"5", "*", "100"}, "5,00 * 100,00 = 500,00"},
                {new String[]{"44.95", "*", "-0.36"}, "44,95 * -0,36 = -16,18"},
                {new String[]{"-10", "*", "-5"}, "-10,00 * -5,00 = 50,00"},
        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("*", multiplicationDouble.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, multiplicationDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, multiplicationDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, multiplicationDouble.calculate(input));
    }
}