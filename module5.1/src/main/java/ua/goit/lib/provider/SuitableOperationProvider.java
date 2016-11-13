package ua.goit.lib.provider;

import ua.goit.lib.exception.UnsupportedOperationException;
import ua.goit.lib.exception.UnsupportedTypeException;
import ua.goit.lib.operations.Operation;

import java.util.List;

public interface SuitableOperationProvider {
    Operation getSuitableOperation(List<Operation> supportedOperations, String[] input)
            throws UnsupportedOperationException, UnsupportedTypeException;
}
