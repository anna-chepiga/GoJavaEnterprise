package ua.goit.calculator.operations.addition;

import ua.goit.calculator.operations.OperationAddition;

public class OperationAdditionDouble implements OperationAddition {
    @Override
    public boolean isOperationTypeCorrect(String[] expression) {
        return expression[1].equals(operationSymbol);
    }

    @Override
    public String getOperationSymbol() {
        return operationSymbol;
    }

    @Override
    public boolean isOperationSupported(String[] expression) {
        if (expression.length != correctInputLength || !isOperationTypeCorrect(expression)) {
            return false;
        }

        try {
            Double.parseDouble(expression[0]);
            Double.parseDouble(expression[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] expression) {
        double a = Double.parseDouble(expression[0]);
        double b = Double.parseDouble(expression[2]);
        double result = a + b;
        return String.format("%,.2f + %,.2f = %,.2f", a, b, result);
    }
}