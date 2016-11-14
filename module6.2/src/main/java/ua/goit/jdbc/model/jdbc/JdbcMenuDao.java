package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.controllers.DishController;
import ua.goit.jdbc.model.MenuDao;
import ua.goit.jdbc.model.entities.Dish;
import ua.goit.jdbc.model.entities.Menu;
import ua.goit.jdbc.controllers.DishListController;
import ua.goit.jdbc.model.entities.DishList;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMenuDao implements MenuDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDao.class);
    private DishListController dishListController;
    private DishController dishController;

    @Override
    public List<Menu> getAll() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM menu");

            while (resultSet.next()) {
                Menu menu = createMenu(resultSet);
                result.add(menu);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Menu searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu WHERE name=?")) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createMenu(resultSet);
            } else {
                throw new RuntimeException("Cannot find menu with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int add(Menu menu) {
        int addedID;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO menu " +
                     "(name, dishes_list_id) VALUES (?,?)")) {
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getDishesList());

            statement.executeUpdate();

            addedID = getMaxID();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return addedID;
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM menu WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addDishToMenu(Dish dish, String menuName) {
        boolean added = false;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Menu menu = searchByName(menuName);
            int dishListId = menu.getDishesList();

            int dishId = dish.getId();

            DishList dishList = dishListController.searchById(dishListId);
            List<Integer> dishes = dishList.getAllDishes();

            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i) == 0) {
                    String query = "UPDATE dishes_list SET dish_id_" + (i + 1)
                            + " = " + dishId + " where id = " + dishListId;
                    statement.executeUpdate(query);
                    added = true;
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return added;
    }

    @Override
    public boolean addDishToMenu(String dishName, String menuName) {
        boolean added = false;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Menu menu = searchByName(menuName);
            int dishListId = menu.getDishesList();

            Dish dish = dishController.searchByName(dishName);
            int dishId = dish.getId();

            DishList dishList = dishListController.searchById(dishListId);
            List<Integer> dishes = dishList.getAllDishes();

            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i) == 0) {
                    String query = "UPDATE dishes_list SET dish_id_" + (i + 1)
                            + " = " + dishId + " where id = " + dishListId;
                    statement.executeUpdate(query);
                    added = true;
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return added;
    }

    @Override
    public void deleteDishFromMenu(String dishName, String menuName) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Menu menu = searchByName(menuName);
            int dishListId = menu.getDishesList();

            Dish dish = dishController.searchByName(dishName);
            int dishId = dish.getId();

            DishList dishList = dishListController.searchById(dishListId);
            List<Integer> dishes = dishList.getAllDishes();

            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i) == dishId) {
                    String query = "UPDATE dishes_list SET dish_id_" + (i + 1) + " = NULL WHERE id = " + dishListId;
                    statement.executeUpdate(query);
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    private Menu createMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        menu.setId(resultSet.getInt("id"));
        menu.setName(resultSet.getString("name"));
        menu.setDishesList(resultSet.getInt("dishes_list_id"));
        return menu;
    }

    private int getMaxID() {
        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM menu");

            if (resultSet.next()) {
                maxID = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return maxID;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDishListController(DishListController dishListController) {
        this.dishListController = dishListController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }
}
