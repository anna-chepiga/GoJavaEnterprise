package ua.goit.app.operations;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.calculator.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationAdditionDateTest {
    private Operation additionDate = new OperationAdditionDate();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"01.01.2016", "+", "15", "days"}, true},
                {new String[]{"01.01.2016", "+", "15", "months"}, true},
                {new String[]{"01.01.2016", "+", "15", "years"}, true},
                {new String[]{"01.01.2016", "+", "1", "day"}, true},
                {new String[]{"01.01.2016", "+", "1", "month"}, true},
                {new String[]{"01.01.2016", "+", "1", "year"}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"01.01.2016", "-", "15", "days"}, false},
                {new String[]{"32.01.2016", "+", "20", "days"}, false},
                {new String[]{"15.13.2016", "+", "20", "days"}, false},
                {new String[]{"01/01/2016", "+", "20", "days"}, false},
                {new String[]{"01-01-2016", "+", "20", "days"}, false},
                {new String[]{"01.jan.2016", "+", "20", "days"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() {
        return new Object[][]{
                {new String[]{"01.01.2016", "+", "15", "days"}, "01.01.2016 + 15 days = 16.01.2016"},
                {new String[]{"01.01.2016", "+", "15", "months"}, "01.01.2016 + 15 months = 01.04.2017"},
                {new String[]{"01.01.2016", "+", "15", "years"}, "01.01.2016 + 15 years = 01.01.2031"},
                {new String[]{"01.01.2016", "+", "1", "day"}, "01.01.2016 + 1 day = 02.01.2016"},
                {new String[]{"01.01.2016", "+", "1", "month"}, "01.01.2016 + 1 month = 01.02.2016"},
                {new String[]{"01.01.2016", "+", "1", "lakj"}, "Incorrect adding data"},

        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("+", additionDate.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, additionDate.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, additionDate.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, additionDate.calculate(input));
    }
}