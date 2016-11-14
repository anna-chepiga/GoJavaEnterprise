package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.jdbc.model.DishDao;
import ua.goit.jdbc.model.entities.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishDao implements DishDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(DishDao.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dish");

            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Dish searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM dish WHERE name=?")) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createDish(resultSet);
            } else {
                throw new RuntimeException("Cannot find dish with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public int add(Dish dish) {
        int addedID;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO dish" +
                     "(name, category, ingredients_list_id, price, weight)" +
                     "VALUES (?,?,?,?,?)")) {
            statement.setString(1, dish.getName());
            statement.setInt(2, dish.getCategory());
            statement.setInt(3, dish.getIngredientsList());
            statement.setDouble(4, dish.getPrice());
            statement.setInt(5, dish.getWeight());

            statement.executeUpdate();

            addedID = getMaxID();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return addedID;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM dish WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id"));
        dish.setName(resultSet.getString("name"));
        dish.setCategory(resultSet.getInt("category"));
        dish.setIngredientsList(resultSet.getInt("ingredients_list_id"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setWeight(resultSet.getInt("weight"));
        return dish;
    }

    private int getMaxID() {
        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM dish");

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
}
