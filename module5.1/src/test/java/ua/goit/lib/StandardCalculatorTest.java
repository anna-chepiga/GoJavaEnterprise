package ua.goit.lib;

import org.junit.Before;
import org.junit.Test;
import ua.goit.lib.operations.addition.OperationAdditionFloat;
import ua.goit.lib.operations.subtraction.OperationSubtractionLong;
import ua.goit.lib.operations.unary.OperationSquareRootDouble;
import ua.goit.lib.provider.ExtendedUnaryOperationProvider;

import static org.junit.Assert.*;

public class StandardCalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StandardCalculator(new ExtendedUnaryOperationProvider());
        calculator.addToSupportedOperations(new OperationAdditionFloat());
        calculator.addToSupportedOperations(new OperationSubtractionLong());
        calculator.addToSupportedOperations(new OperationSquareRootDouble());
    }

    @Test
    public void calculate() throws Exception {
        assertEquals("5,50 + 2,20 = 7,70", calculator.calculate("5.5 + 2.2"));
        assertEquals("5000 - 1000 = 4000", calculator.calculate("5000 - 1000"));
        assertEquals("Sqrt(64,64) = 8,04", calculator.calculate("sqrt 64.64"));
        assertEquals("UnsupportedOperationExceptionOperation: log", calculator.calculate("log 100"));
        assertEquals("UnsupportedOperationExceptionOperation: *", calculator.calculate("9 * 8"));
        assertEquals("UnsupportedOperationExceptionOperation: /", calculator.calculate("7 / 3"));
        assertEquals("UnsupportedTypeException", calculator.calculate("5 + kj"));
    }
}