package ua.goit.lib.provider;

import ua.goit.lib.exception.UnsupportedOperationException;
import ua.goit.lib.operations.Operation;
import ua.goit.lib.exception.UnsupportedTypeException;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultSuitableOperationProvider implements SuitableOperationProvider {
    public Operation getSuitableOperation(List<Operation> supportedOperations, String[] input)
            throws UnsupportedOperationException, UnsupportedTypeException {

        List<Operation> tmpResult = supportedOperations.stream()
                .filter(op -> op.getOperationSymbol().equals(input[1]))
                .collect(Collectors.toList());

        if (tmpResult.size() == 0) throw new UnsupportedOperationException("Operation: " + input[1]);

        Operation result = null;

        for (Operation op : tmpResult) {
            if (op.isOperationSupported(input)) {
                result = op;
            } else {
                throw new UnsupportedTypeException();
            }
        }

        return result;
    }
}