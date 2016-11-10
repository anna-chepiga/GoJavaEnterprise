package ua.goit.app.operations;

import ua.goit.calculator.operations.OperationMultiplication;

public class OperationMultiplicationDouble implements OperationMultiplication {
    public String getOperationSymbol() {
        return operationSymbol;
    }

    public boolean isOperationTypeCorrect(String[] input) {
        return input[1].equals(operationSymbol);
    }

    public boolean isOperationSupported(String[] input) {
        if (input.length != correctInputLength || !isOperationTypeCorrect(input)) {
            return false;
        }

        try {
            Double.parseDouble(input[0]);
            Double.parseDouble(input[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String calculate(String[] input) {
        double a = Double.parseDouble(input[0]);
        double b = Double.parseDouble(input[2]);
        double result = a * b;
        return String.format("%,.2f * %,.2f = %,.2f", a, b, result);
    }
}
