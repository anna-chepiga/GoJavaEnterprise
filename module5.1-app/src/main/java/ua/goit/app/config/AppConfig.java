package ua.goit.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.goit.app.operations.OperationAdditionDate;
import ua.goit.app.operations.OperationDivisionLong;
import ua.goit.app.operations.OperationMultiplicationDouble;
import ua.goit.app.operations.OperationSubtractionDate;
import ua.goit.calculator.Calculator;
import ua.goit.calculator.StandardCalculator;
import ua.goit.calculator.operations.Operation;
import ua.goit.calculator.operations.addition.OperationAdditionDouble;
import ua.goit.calculator.operations.subtraction.OperationSubtractionFloat;
import ua.goit.calculator.operations.subtraction.OperationSubtractionLong;
import ua.goit.calculator.operations.unary.OperationSquareRootDouble;
import ua.goit.calculator.provider.ExtendedUnaryOperationProvider;
import ua.goit.calculator.provider.SuitableOperationProvider;

import java.util.List;

@Configuration
@Scope("prototype")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Calculator calculator(List<Operation> operations) {
        Calculator calculator = new StandardCalculator(operationProvider());
        calculator.setSupportedOperations(operations);
        return calculator;
    }

    @Bean
    public SuitableOperationProvider operationProvider() {
        return new ExtendedUnaryOperationProvider();
    }

    @Bean
    public Calculator calculator() {
        return new StandardCalculator(operationProvider());
    }

    @Bean
    public Operation additionDouble() {
        return new OperationAdditionDouble();
    }

    @Bean
    public Operation subtractionLong() {
        return new OperationSubtractionLong();
    }

    @Bean
    public Operation squareRootDouble() {
        return new OperationSquareRootDouble();
    }

    @Bean
    public Operation subtractionFloat() {
        return new OperationSubtractionFloat();
    }

    @Bean
    public Operation additionDate() {
        return new OperationAdditionDate();
    }

    @Bean
    public Operation subtractionDate() {
        return new OperationSubtractionDate();
    }

    @Bean
    public Operation divisionLong() {
        return new OperationDivisionLong();
    }

    @Bean
    public Operation multiplicationDouble() {
        return new OperationMultiplicationDouble();
    }
}