package ua.goit.lib.operations.unary;

import ua.goit.lib.operations.OperationSquareRoot;

public class OperationSquareRootDouble implements OperationSquareRoot {
    @Override
    public boolean isOperationTypeCorrect(String[] expression) {
        return expression[0].equals(operationSymbol);
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
            Double.parseDouble(expression[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] expression) {
        double a = Double.parseDouble(expression[1]);

        double result = Math.sqrt(a);
        return String.format("Sqrt(%,.2f) = %,.2f", a, result);
    }
}