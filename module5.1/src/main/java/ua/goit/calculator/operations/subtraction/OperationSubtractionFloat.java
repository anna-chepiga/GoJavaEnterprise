package ua.goit.calculator.operations.subtraction;

import ua.goit.calculator.operations.OperationSubtraction;

public class OperationSubtractionFloat implements OperationSubtraction {
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
            Float.parseFloat(expression[0]);
            Float.parseFloat(expression[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] expression) {
        float a = Float.parseFloat(expression[0]);
        float b = Float.parseFloat(expression[2]);
        float result = a - b;
        return String.format("%,.2f - %,.2f = %,.2f", a, b, result);
    }
}