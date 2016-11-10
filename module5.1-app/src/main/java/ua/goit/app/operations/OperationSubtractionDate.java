package ua.goit.app.operations;

import ua.goit.calculator.operations.Operation;

import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OperationSubtractionDate implements Operation {
    private final String operationSymbol = "-";
    private final int correctInputLength = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public String getOperationSymbol() {
        return operationSymbol;
    }

    @Override
    public boolean isOperationTypeCorrect(String[] input) {
        return input[1].equals(operationSymbol);
    }

    @Override
    public boolean isOperationSupported(String[] input) {
        if (input.length != correctInputLength || !isOperationTypeCorrect(input)) {
            return false;
        }

        try {
            formatter.parse(input[0]);
            formatter.parse(input[2]);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    @Override
    public String calculate(String[] input) {
        StringBuilder sb = new StringBuilder();

        LocalDate date1 = LocalDate.parse(input[0], formatter);
        LocalDate date2 = LocalDate.parse(input[2], formatter);

        Period period = Period.between(date2, date1);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        if (years != 0) sb.append(years).append(" year(s) ");
        if (months != 0) sb.append(months).append(" month(s) ");
        if (days != 0) sb.append(days).append(" day(s) ");
        if (sb.length() == 0) sb.append(0);

        return String.format("%s - %s = %s", input[0], input[2], sb.toString());
    }
}