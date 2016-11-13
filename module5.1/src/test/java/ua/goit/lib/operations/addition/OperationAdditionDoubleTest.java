package ua.goit.lib.operations.addition;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.lib.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationAdditionDoubleTest {
    private Operation additionDouble = new OperationAdditionDouble();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"5", "+", "88.02"}, true},
                {new String[]{String.valueOf(Double.MAX_VALUE), "+", "-15.6"}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"five", "+", "88.02"}, false},
                {new String[]{"notNumber", "-", "-15.6"}, false},
                {new String[]{"50", "*", "-144.5"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() { // local coma symbol
        return new Object[][]{
                {new String[]{"5.5", "+", "1.1"}, "5,50 + 1,10 = 6,60"},
                {new String[]{"0", "+", "44.20"}, "0,00 + 44,20 = 44,20"},
                {new String[]{"0", "+", "0"}, "0,00 + 0,00 = 0,00"},
        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("+", additionDouble.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, additionDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, additionDouble.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, additionDouble.calculate(input));
    }
}