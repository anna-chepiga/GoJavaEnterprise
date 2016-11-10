package ua.goit.calculator.provider;

import ua.goit.calculator.exception.UnsupportedOperationException;
import ua.goit.calculator.exception.UnsupportedTypeException;
import ua.goit.calculator.operations.Operation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExtendedUnaryOperationProvider implements SuitableOperationProvider {
    @Override
    public Operation getSuitableOperation(List<Operation> supportedOperations, String[] input)
            throws UnsupportedOperationException, UnsupportedTypeException {

        List<Operation> tmpResult = supportedOperations.stream()
                .filter(op -> op.getOperationSymbol().equals(input[1]) || op.getOperationSymbol().equals(input[0]))
                .collect(Collectors.toList());

        String exceptionMsg = input.length == 3 ? "Operation: " + input[1] : "Operation: " + input[0];

        if (tmpResult.size() == 0) throw new UnsupportedOperationException(exceptionMsg);

        /*Operation result = null;

        for (Operation op : tmpResult) {
            if (op.isOperationSupported(input)) {
                Operation result = op;
                break;
            } else {
                throw new UnsupportedTypeException();
            }
        }

        return result;*/

        Optional<Operation> result = tmpResult.stream()
                .filter(so -> so.isOperationSupported(input))
                .findFirst();

        return result.orElseThrow(UnsupportedTypeException::new);
    }
}