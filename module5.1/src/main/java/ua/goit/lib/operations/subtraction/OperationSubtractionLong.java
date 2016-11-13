package ua.goit.lib.operations.subtraction;

import ua.goit.lib.operations.OperationSubtraction;

public class OperationSubtractionLong implements OperationSubtraction {
    @Override
    public String getOperationSymbol() {
        return operationSymbol;
    }

    @Override
    public boolean isOperationTypeCorrect(String[] expression) {
        return expression[1].equals(operationSymbol);
    }

    @Override
    public boolean isOperationSupported(String[] expression) {
        if (expression.length != correctInputLength || !isOperationTypeCorrect(expression)) {
            return false;
        }

        try {
            Long.parseLong(expression[0]);
            Long.parseLong(expression[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] expression) {
        long a = Long.parseLong(expression[0]);
        long b = Long.parseLong(expression[2]);
        long result = a - b;
        return String.format("%d - %d = %d", a, b, result);
    }
}