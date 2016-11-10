package ua.goit.app.operations;

import ua.goit.calculator.operations.OperationDivision;

public class OperationDivisionLong implements OperationDivision {
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
            Long.parseLong(input[0]);
            Long.parseLong(input[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String calculate(String[] input) {
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[2]);
        double result = (double) a / b;
        return String.format("%d / %d = %,.2f", a, b, result);
    }
}
