package ua.goit.lib;

import ua.goit.lib.operations.Operation;

import java.util.List;

public interface Calculator {
    String calculate(String expression);
    void addToSupportedOperations(Operation operation);
    List<Operation> getSupportedOperations();
    void setSupportedOperations(List<Operation> supportedOperations);
}
