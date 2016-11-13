package ua.goit.lib.operations;

public interface Operation {
    String getOperationSymbol();
    boolean isOperationTypeCorrect(String[] expression);
    boolean isOperationSupported(String[] expression);
    String calculate(String[] expression);
}