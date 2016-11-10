package ua.goit.calculator;

import ua.goit.calculator.operations.Operation;

import java.util.List;

public interface Calculator {
    String calculate(String expression);
    void addToSupportedOperations(Operation operation);
    List<Operation> getSupportedOperations();
    void setSupportedOperations(List<Operation> supportedOperations);
}
