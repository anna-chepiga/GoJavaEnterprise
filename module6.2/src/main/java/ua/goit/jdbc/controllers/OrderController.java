package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.OrderDao;
import ua.goit.jdbc.model.entities.Order;

import java.util.List;

public class OrderController {
    private OrderDao orderDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public Order searchById(int id) {
        if (id < 0) {
            throw new RuntimeException("Cannot search order by negative ID");
        }

        return orderDao.searchById(id);
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public void add(Order order) {
        if (order != null) {
            int addedID = orderDao.add(order);
            LOGGER.info("Successfully added order with ID " + addedID);
        } else {
            LOGGER.error("Cannot add a NULL order");
        }
    }

    public void addDishToOrder(String dishName, int orderId) {
        if (dishName != null && orderId > 0) {
            orderDao.addDishToOrder(dishName, orderId);
            LOGGER.info("Successfully added dish " + dishName + " to order id " + orderId);
        } else {
            LOGGER.error("Cannot add a dish to order: dish is NULL or order id is negative");
        }
    }

    public void deleteDishFromOrder(String dishName, int orderId) {
        if (dishName != null && orderId > 0) {
            orderDao.deleteDishFromOrder(dishName, orderId);
            LOGGER.info("Successfully deleted dish " + dishName + " from order id " + orderId);
        } else {
            LOGGER.error("Cannot delete a dish from order: dish is NULL or order id is negative");
        }
    }

    public void deleteById(int orderId) {
        if (orderId > 0) {
            orderDao.delete(orderId);
            LOGGER.info("Successfully deleted order with id " + orderId);
        } else {
            LOGGER.error("Trying to user negative ID");
        }
    }

    public void closeOrder(int orderId) {
        if (orderId > 0) {
            boolean closed = orderDao.closeOrder(orderId);

            if (closed) {
                LOGGER.info("Order with id " + orderId + " successfully closed");
            } else {
                LOGGER.info("Order with id " + orderId + " has been closed before");
            }
        } else {
            LOGGER.error("Cannot close order with negative ID");
        }
    }

    public List<Order> selectOpenOrders() {
        return orderDao.selectOpenOrders();
    }

    public List<Order> selectClosedOrders() {
        return orderDao.selectClosedOrders();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
    }
}