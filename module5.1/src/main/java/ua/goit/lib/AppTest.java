package ua.goit.lib;

import ua.goit.lib.operations.addition.OperationAdditionDouble;
import ua.goit.lib.operations.subtraction.OperationSubtractionInteger;
import ua.goit.lib.operations.unary.OperationSquareRootDouble;
import ua.goit.lib.provider.ExtendedUnaryOperationProvider;


import java.util.Scanner;

public class AppTest {
    public static void main(String[] args) {
        Calculator calculator = new StandardCalculator(new ExtendedUnaryOperationProvider());
        calculator.addToSupportedOperations(new OperationAdditionDouble());
        calculator.addToSupportedOperations(new OperationSubtractionInteger());
        calculator.addToSupportedOperations(new OperationSquareRootDouble());

        System.out.println("Enter expression");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) break;
            System.out.println(calculator.calculate(input));
        }
    }
}