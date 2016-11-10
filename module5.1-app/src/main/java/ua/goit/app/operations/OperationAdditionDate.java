package ua.goit.app.operations;

import ua.goit.calculator.operations.Operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class OperationAdditionDate implements Operation {
    private final String operationSymbol = "+";
    private final int correctInputLength = 4;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private final DateTimeFormatter exceptionChecker = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
            exceptionChecker.parse(input[0]);
            Integer.parseInt(input[2]);
        } catch (DateTimeParseException | NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String calculate(String[] input) {
        Calendar c1 = Calendar.getInstance();
        Date result;

        try {
            c1.setTime(dateFormatter.parse(input[0]));
            int amount = Integer.parseInt(input[2]);

            Calendar cTotal = (Calendar) c1.clone();

            switch (input[3]) {
                case "days":
                case "day":
                    cTotal.add(Calendar.DATE, amount);
                    break;
                case "months":
                case "month":
                    cTotal.add(Calendar.MONTH, amount);
                    break;
                case "years":
                case "year":
                    cTotal.add(Calendar.YEAR, amount);
                    break;
                default:
                    return "Incorrect data type, use days, months or years";
            }

            result = cTotal.getTime();
        } catch (ParseException e) {
            return "Impossible to format dates";
        }

        return String.format("%s %s %s %s = %s", input[0], input[1], input[2], input[3], dateFormatter.format(result));
    }
}