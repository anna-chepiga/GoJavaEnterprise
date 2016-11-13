package ua.goit.lib;

import ua.goit.lib.exception.UnsupportedOperationException;
import ua.goit.lib.exception.UnsupportedTypeException;
import ua.goit.lib.operations.Operation;
import ua.goit.lib.provider.SuitableOperationProvider;

import java.util.ArrayList;
import java.util.List;


public class StandardCalculator implements Calculator {
    private List<Operation> supportedOperations = new ArrayList<>();
    private SuitableOperationProvider operationProvider;

    public StandardCalculator(SuitableOperationProvider operationProvider) {
        this.operationProvider = operationProvider;
    }

    @Override
    public void addToSupportedOperations(Operation operation) {
        supportedOperations.add(operation);
    }

    @Override
    public List<Operation> getSupportedOperations() {
        return supportedOperations;
    }

    @Override
    public void setSupportedOperations(List<Operation> supportedOperations) {
        this.supportedOperations = supportedOperations;
    }

    @Override
    public String calculate(String expression) {
        String[] input = expression.split(" ");

        Operation suitableOperation;

        try {
            suitableOperation = operationProvider.getSuitableOperation(supportedOperations, input);
        } catch (UnsupportedOperationException e) {
            return e.getClass().getSimpleName() + e.getMessage();
        } catch (UnsupportedTypeException e) {
            return e.getClass().getSimpleName();
        }

        return suitableOperation.calculate(input);
    }
}