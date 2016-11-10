package ua.goit.app.operations;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.calculator.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationDivisionLongTest {
    private Operation divisionLong = new OperationDivisionLong();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"5", "/", "88"}, true},
                {new String[]{String.valueOf(Long.MAX_VALUE), "/", "15"}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"five", "/", "88.2"}, false},
                {new String[]{"notNumber", "/", "-15.6"}, false},
                {new String[]{"50", "+", "-144.5"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() { // local coma symbol
        return new Object[][]{
                {new String[]{"5", "/", "1"}, "5 / 1 = 5,00"},
                {new String[]{"44", "/", "-9"}, "44 / -9 = -4,89"},
                {new String[]{"55", "/", "0"}, "55 / 0 = Infinity"},
                {new String[]{"0", "/", "0"}, "0 / 0 = NaN"},
        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("/", divisionLong.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, divisionLong.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, divisionLong.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, divisionLong.calculate(input));
    }
}