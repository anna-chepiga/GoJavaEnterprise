package ua.goit.app.operations;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import ua.goit.calculator.operations.Operation;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class OperationSubtractionDateTest {
    private Operation subtractionDate = new OperationSubtractionDate();

    @DataProvider
    public static Object[][] dataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"01.01.2016", "-", "15.12.2015"}, true},
                {new String[]{"31.12.2050", "-", "04.06.1556"}, true},
        };
    }

    @DataProvider
    public static Object[][] wrongDataForIsOperationSupported() {
        return new Object[][]{
                {new String[]{"32.01.2016", "-", "01.01.2016"}, false},
                {new String[]{"31.01.2016", "-", "01.13.2016"}, false},
                {new String[]{"31/01/2016", "-", "01.01.2016"}, false},
                {new String[]{"31-01-2016", "-", "01.01.2016"}, false},
                {new String[]{"31.jan.2016", "-", "01.01.2016"}, false},
                {new String[]{"31.01.2016", "+", "01.13.2016"}, false},
        };
    }

    @DataProvider
    public static Object[][] dataForCalculateMethod() {
        return new Object[][]{
                {new String[]{"01.01.2016", "-", "31.12.2015"}, "01.01.2016 - 31.12.2015 = 1 day(s) "},
                {new String[]{"01.01.2016", "-", "01.12.2015"}, "01.01.2016 - 01.12.2015 = 1 month(s) "},
                {new String[]{"01.01.2016", "-", "04.05.2013"}, "01.01.2016 - 04.05.2013 = 2 year(s) 7 month(s) 28 day(s) "},
                {new String[]{"01.01.2016", "-", "01.01.2016"}, "01.01.2016 - 01.01.2016 = 0"},


        };
    }

    @Test
    public void getOperationSymbol() throws Exception {
        assertEquals("-", subtractionDate.getOperationSymbol());
    }

    @Test
    @UseDataProvider("dataForIsOperationSupported")
    public void isOperationSupportedTrue(String[] input, boolean expected) throws Exception {
        assertEquals(expected, subtractionDate.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("wrongDataForIsOperationSupported")
    public void isOperationSupportedFalse(String[] input, boolean expected) throws Exception {
        assertEquals(expected, subtractionDate.isOperationSupported(input));
    }

    @Test
    @UseDataProvider("dataForCalculateMethod")
    public void calculate(String[] input, String result) throws Exception {
        assertEquals(result, subtractionDate.calculate(input));
    }
}