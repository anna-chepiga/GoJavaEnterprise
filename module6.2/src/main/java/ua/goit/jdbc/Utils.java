package ua.goit.jdbc;

import ua.goit.jdbc.controllers.*;
import ua.goit.jdbc.model.entities.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    private Scanner sc = new Scanner(System.in);
    private CookedDishController cookedDishController;
    private DishController dishController;
    private EmployeeController employeeController;
    private MenuController menuController;
    private OrderController orderController;
    private StorageController storageController;


    public void useEmployee() {
        System.out.println("Select action:\n" +
                "1) See all employees; 2) Search for an employee by name; 3) Delete an employee by ID");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                List<Employee> allEmployees = employeeController.getAllEmployees();
                allEmployees.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Enter employee name");
                String name = sc.next();
                Employee employee = employeeController.searchByName(name);
                System.out.println(employee);
                break;
            case 3:
                System.out.println("Enter employee ID");
                int id = sc.nextInt();
                employeeController.deleteEmployee(id);
                break;
        }
    }

    public void useDish() {
        System.out.println("Select action:\n1) See all dishes; 2) Add new dish; " +
                "3) Search for a dish by name; 4) Delete a dish by ID");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                List<Dish> allDishes = dishController.getAllDishes();
                allDishes.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Enter dish name");
                String name = sc.next();

                System.out.println("Enter dish category");
                int category = sc.nextInt();

                System.out.println("Enter ID of ingredients list");
                int ingredientsList = sc.nextInt();

                System.out.println("Enter price");
                double price = sc.nextDouble();

                System.out.println("Enter weight");
                int weight = sc.nextInt();

                Dish dish = new Dish(name, category, ingredientsList, price, weight);

                dishController.addDish(dish);
                break;
            case 3:
                System.out.println("Enter dish name");
                String dishName = sc.next();
                Dish selectedDish = dishController.searchByName(dishName);
                System.out.println(selectedDish);
                break;
            case 4:
                System.out.println("Enter dish ID");
                int id = sc.nextInt();

                dishController.deleteDish(id);
                break;
        }

    }

    public void useMenu() {
        System.out.println("Select action:\n1) See all menus; 2) Add new menu; 3) Search for a menu by name; " +
                "4) Delete menu by ID; 5) Add a dish to menu; 6) Delete a dish from menu");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                List<Menu> allMenus = menuController.getAll();
                allMenus.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Enter menu name");
                String menuName = sc.next();

                System.out.println("Enter ID of dishes list");
                int id = sc.nextInt();

                Menu menu = new Menu(menuName, id);

                menuController.addMenu(menu);
                break;
            case 3:
                System.out.println("Enter menu name");
                String name = sc.next();
                Menu selectedMenu = menuController.searchByName(name);
                System.out.println(selectedMenu);
                break;
            case 4:
                System.out.println("Enter menu ID");
                int menuId = sc.nextInt();
                menuController.deleteById(menuId);
                break;
            case 5:
                System.out.println("Enter dish name");
                String dishToAdd = sc.next();

                System.out.println("Enter menu name");
                String menuToAdd = sc.next();

                menuController.addDishToMenu(dishToAdd, menuToAdd);
                break;
            case 6:
                System.out.println("Enter dish name");
                String dishToDelete = sc.next();

                System.out.println("Enter menu name");
                String menuToDelete = sc.next();

                menuController.deleteDishFromMenu(dishToDelete, menuToDelete);
                break;
        }
    }

    public void useOrder() {
        System.out.println("Select action:\n1) See open orders; 2) See closed orders; 3) Add order; " +
                "4) Delete an order by ID; 5) Close an order; 6) Add a dish to an order; " +
                "7) Delete a dish from an order");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                List<Order> openOrders = orderController.selectOpenOrders();
                openOrders.forEach(System.out::println);
                break;
            case 2:
                List<Order> closedOrders = orderController.selectClosedOrders();
                closedOrders.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Enter waiter ID");
                int waiterId = sc.nextInt();

                System.out.println("Enter dishes list ID");
                int dishesListId = sc.nextInt();

                System.out.println("Enter table number");
                int tableNumber = sc.nextInt();

                System.out.println("Enter date");
                long date = sc.nextLong();

                Order order = new Order(waiterId, dishesListId, tableNumber, new Date(date));

                orderController.add(order);
                break;
            case 4:
                System.out.println("Enter ID of the order to delete");
                int idToDelete = sc.nextInt();
                orderController.deleteById(idToDelete);
                break;
            case 5:
                System.out.println("Enter order ID to close");
                int idToClose = sc.nextInt();
                orderController.closeOrder(idToClose);
                break;
            case 6:
                System.out.println("Enter dish name");
                String dishName = sc.next();

                System.out.println("Enter order ID");
                int orderId = sc.nextInt();

                orderController.addDishToOrder(dishName, orderId);
                break;
            case 7:
                System.out.println("Enter dish name");
                String name = sc.next();

                System.out.println("Enter order ID");
                int id = sc.nextInt();

                orderController.deleteDishFromOrder(name, id);
                break;
        }
    }

    public void useKitchenHistory() {
        System.out.println("Select action:\n1) Add cooked dish; 2) See all cooked dishes");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                System.out.println("Enter dish ID");
                int dishId = sc.nextInt();

                System.out.println("Enter cooker ID");
                int cookerId = sc.nextInt();

                System.out.println("Enter order ID");
                int orderId = sc.nextInt();

                System.out.println("Enter date");
                long date = sc.nextLong();

                CookedDish dish = new CookedDish(dishId, cookerId, orderId, new Date(date));
                cookedDishController.add(dish);
                break;
            case 2:
                List<CookedDish> cookedDishList = cookedDishController.getAll();
                cookedDishList.forEach(System.out::println);
        }
    }

    public void useStorage() {
        System.out.println("Select action:\n1) See all ingredients in storage 2) Add new ingredients to storage; " +
                "3) Delete ingredients from storage; 4) Change amount of an ingredient in storage; " +
                "5) Search for an ingredient by name; 6) See ending ingredients");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                Map<String, Integer> all = storageController.getAll();
                all.entrySet().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Enter ingredient name");
                String name = sc.next();

                System.out.println("Enter ingredient amount");
                int amount = sc.nextInt();

                storageController.addIngredient(name, amount);
                break;
            case 3:
                System.out.println("Enter ingredient ID to delete");
                int id = sc.nextInt();
                storageController.delete(id);
                break;
            case 4:
                System.out.println("Enter ingredient name");
                String ingredient = sc.next();

                System.out.println("Enter ingredient amount");
                int amount1 = sc.nextInt();

                System.out.println("Enter true if you need to add amount; and false if you need to deduct amount");
                boolean add = sc.nextBoolean();

                storageController.changeIngredientAmount(ingredient, amount1, add);
                break;
            case 5:
                System.out.println("Enter ingredient name");
                String ingredientName = sc.next();
                Map<String, Integer> result = storageController.searchByName(ingredientName);
                result.entrySet().forEach(System.out::println);
                break;
            case 6:
                Map<String, Integer> endingIngredients = storageController.getEndingIngredients();
                endingIngredients.entrySet().forEach(System.out::println);
                break;
        }
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setCookedDishController(CookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }
}