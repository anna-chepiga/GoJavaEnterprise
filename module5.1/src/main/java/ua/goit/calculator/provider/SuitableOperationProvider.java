package ua.goit.calculator.provider;

import ua.goit.calculator.exception.UnsupportedOperationException;
import ua.goit.calculator.exception.UnsupportedTypeException;
import ua.goit.calculator.operations.Operation;

import java.util.List;

public interface SuitableOperationProvider {
    Operation getSuitableOperation(List<Operation> supportedOperations, String[] input)
            throws UnsupportedOperationException, UnsupportedTypeException;
}
