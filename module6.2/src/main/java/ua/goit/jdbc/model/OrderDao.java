package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.Order;

import java.util.List;

public interface OrderDao {
    int add(Order order);
    Order searchById(int id);
    void addDishToOrder(String dishName, int orderId); // check isOpen
    void deleteDishFromOrder(String dishName, int orderId); //check isOpen
    void delete(int orderId); // check isOpen
    boolean closeOrder(int orderId);
    List<Order> selectOpenOrders();
    List<Order> selectClosedOrders();
    List<Order> getAll();
}
