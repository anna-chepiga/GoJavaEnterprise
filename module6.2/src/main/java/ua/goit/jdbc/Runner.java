package ua.goit.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Runner {
    private static Scanner sc = new Scanner(System.in);

    // CHECK TIME ZONE BEFORE USING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Utils utils = context.getBean(Utils.class);

        start(utils);
    }

    public static void start(Utils utils) {
        System.out.println("What are you going to work with?:\n" +
                "1) Employee, 2) Dish, 3) Menu, 4) Order, 5) Kitchen history, 6) Storage");

        String choice = sc.next();

        switch (choice) {
            case "Employee":
                utils.useEmployee();
                break;
            case "Dish":
                utils.useDish();
                break;
            case "Menu":
                utils.useMenu();
                break;
            case "Order":
                utils.useOrder();
                break;
            case "Kitchen history":
                utils.useKitchenHistory();
                break;
            case "Storage":
                utils.useStorage();
                break;
            default:
                System.out.println("Incorrect choice");
        }
    }
}