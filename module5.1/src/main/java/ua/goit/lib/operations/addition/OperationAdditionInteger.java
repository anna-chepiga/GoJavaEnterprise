package ua.goit.lib.operations.addition;

import ua.goit.lib.operations.OperationAddition;

public class OperationAdditionInteger implements OperationAddition {
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
            Integer.parseInt(expression[0]);
            Integer.parseInt(expression[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] expression) {
        int a = Integer.parseInt(expression[0]);
        int b = Integer.parseInt(expression[2]);
        int result = a + b;
        return String.format("%d + %d = %d", a, b, result);
    }
}