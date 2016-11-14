package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.controllers.DishController;
import ua.goit.jdbc.controllers.DishListController;
import ua.goit.jdbc.model.entities.Dish;
import ua.goit.jdbc.model.entities.DishList;
import ua.goit.jdbc.model.OrderDao;
import ua.goit.jdbc.model.entities.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcOrderDao implements OrderDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);
    private DishController dishController;
    private DishListController dishListController;

    @Override
    public Order searchById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM restaurant.`order` WHERE id=?")) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createOrder(resultSet);
            } else {
                throw new RuntimeException("Cannot find order with id " + id);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM restaurant.`order`");

            while (resultSet.next()) {
                Order order = createOrder(resultSet);
                result.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int add(Order order) {
        int addedID;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO `order` " +
                     "(waiter_id, dishes_list, table_number, date, is_open)" +
                     "VALUES (?,?,?,?,?)")) {
            statement.setInt(1, order.getWaiterId());
            statement.setInt(2, order.getDishesListId());
            statement.setInt(3, order.getTableNumber());
            statement.setDate(4, order.getDate());
            statement.setBoolean(5, order.isOpen());

            statement.executeUpdate();

            addedID = getMaxID();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return addedID;
    }

    @Override
    public void addDishToOrder(String dishName, int orderId) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Order order = searchById(orderId);
            if (!order.isOpen()) {
                throw new RuntimeException("Cannot change closed order");
            }

            int dishListId = order.getDishesListId();

            Dish dish = dishController.searchByName(dishName);
            int dishId = dish.getId();

            DishList dishList = dishListController.searchById(dishListId);
            List<Integer> dishes = dishList.getAllDishes();

            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i) == 0) {
                    String query = "UPDATE dishes_list SET dish_id_" + (i + 1)
                            + " = " + dishId + " where id = " + dishListId;
                    statement.executeUpdate(query);
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDishFromOrder(String dishName, int orderId) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Order order = searchById(orderId);

            if (!order.isOpen()) {
                throw new RuntimeException("Cannot change closed order");
            }

            int dishListId = order.getDishesListId();

            Dish dish = dishController.searchByName(dishName);
            int dishId = dish.getId();

            DishList dishList = dishListController.searchById(dishListId);
            List<Integer> dishes = dishList.getAllDishes();

            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i) == dishId) {
                    String query = "UPDATE dishes_list SET dish_id_" + (i + 1)
                            + " = NULL where id = " + dishListId;
                    statement.executeUpdate(query);
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int orderId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM restaurant.`order` WHERE id=?")) {
            Order order = searchById(orderId);
            if (!order.isOpen()) {
                throw new RuntimeException("Cannot delete closed order");
            }

            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean closeOrder(int orderId) {
        Order order = searchById(orderId);

        if (!order.isOpen()) {
            return false;
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE restaurant.`order`" +
                     "SET is_open = FALSE WHERE id = ?")) {
            statement.setInt(1, orderId);

            statement.executeUpdate();
            order.setOpen(false);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public List<Order> selectOpenOrders() {
        List<Order> allOrders = getAll();
        return allOrders.stream().filter(Order::isOpen).collect(Collectors.toList());
    }

    @Override
    public List<Order> selectClosedOrders() {
        List<Order> allOrders = getAll();
        return allOrders.stream().filter(order -> !order.isOpen()).collect(Collectors.toList());
    }

    private int getMaxID() {
        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM restaurant.order");

            if (resultSet.next()) {
                maxID = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return maxID;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setWaiterId(resultSet.getInt("waiter_id"));
        order.setDishesListId(resultSet.getInt("dishes_list"));
        order.setTableNumber(resultSet.getInt("table_number"));
        order.setDate(resultSet.getDate("date"));
        order.setOpen(resultSet.getBoolean("is_open"));
        return order;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setDishListController(DishListController dishListController) {
        this.dishListController = dishListController;
    }
}
