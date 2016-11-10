package ua.goit.calculator.provider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.goit.calculator.exception.UnsupportedOperationException;
import ua.goit.calculator.operations.Operation;
import ua.goit.calculator.operations.addition.OperationAdditionDouble;
import ua.goit.calculator.operations.unary.OperationSquareRootDouble;
import ua.goit.calculator.exception.UnsupportedTypeException;
import ua.goit.calculator.operations.subtraction.OperationSubtractionInteger;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class ExtendedUnaryOperationProviderTest {
    private static List<Operation> operations =
            Arrays.asList(new OperationAdditionDouble(), new OperationSubtractionInteger(), new OperationSquareRootDouble());

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new String[]{"5.8", "+", "10.5"}, operations, OperationAdditionDouble.class},
                {new String[]{"100", "-", "40"}, operations, OperationSubtractionInteger.class},
                {new String[]{"sqrt", "64"}, operations, OperationSquareRootDouble.class},
        };
    }

    @Test
    @UseDataProvider("data")
    public void getSuitableOperation(String[] input, List<Operation> operations, Class expected)
            throws UnsupportedTypeException, UnsupportedOperationException {
        ExtendedUnaryOperationProvider provider = new ExtendedUnaryOperationProvider();

        assertEquals(expected, provider.getSuitableOperation(operations, input).getClass());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getSuitableOperationWrongOperation() throws UnsupportedTypeException, UnsupportedOperationException {
        ExtendedUnaryOperationProvider provider = new ExtendedUnaryOperationProvider();

        provider.getSuitableOperation(operations, new String[]{"5", "*", "15"});
        provider.getSuitableOperation(operations, new String[]{"5", "/", "15"});
        provider.getSuitableOperation(operations, new String[]{"log", "15"});
    }

    @Test(expected = UnsupportedTypeException.class)
    public void getSuitableOperationWrongType() throws UnsupportedTypeException, UnsupportedOperationException {
        DefaultSuitableOperationProvider provider = new DefaultSuitableOperationProvider();

        provider.getSuitableOperation(operations, new String[]{"notNumber", "+", "5"});
        provider.getSuitableOperation(operations, new String[]{"5", "+", "notNumber"});
        provider.getSuitableOperation(operations, new String[]{"sqrt", "notNumber"});
    }
}