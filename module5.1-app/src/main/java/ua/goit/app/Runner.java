package ua.goit.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.app.config.AppConfig;
import ua.goit.calculator.Calculator;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        //2 ways of configuration

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml", "aop-context.xml");

        Calculator calculator = context.getBean(Calculator.class);
        start(calculator);
    }

    private static void start(Calculator calculator) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "Enter arithmetical expression separating data by space, e.g. [5 + 17] or [sqrt 25]\n" +
                "For adding dates use [01.01.2016 + 15 days/months/years]\n" +
                "For subtracting dates use later date first and earlier second, e.g. [31.01.2016 - 01.01.2016]");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) break;
            System.out.println(calculator.calculate(input));
        }
    }
}