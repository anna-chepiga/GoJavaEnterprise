package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.model.CookedDishDao;
import ua.goit.jdbc.model.entities.CookedDish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCookedDishDao implements CookedDishDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(CookedDishDao.class);

    @Override
    public int add(CookedDish cookedDish) {
        int addedID;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO cooked_dishes" +
                     "(dish_id, cooker_id, order_id, date)" +
                     "VALUES (?,?,?,?)")) {

            statement.setInt(1, cookedDish.getDishId());
            statement.setInt(2, cookedDish.getCookerId());
            statement.setInt(3, cookedDish.getOrderId());
            statement.setDate(4, cookedDish.getDate());

            statement.executeUpdate();

            addedID = getMaxID();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return addedID;
    }

    @Override
    public List<CookedDish> getAll() {
        List<CookedDish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cooked_dishes");

            while (resultSet.next()) {
                CookedDish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private CookedDish createDish(ResultSet resultSet) throws SQLException {
        CookedDish dish = new CookedDish();
        dish.setId(resultSet.getInt("id"));
        dish.setDishId(resultSet.getInt("dish_id"));
        dish.setCookerId(resultSet.getInt("cooker_id"));
        dish.setOrderId(resultSet.getInt("order_id"));
        dish.setDate(resultSet.getDate("date"));
        return dish;
    }

    private int getMaxID() {
        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM cooked_dishes");

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
