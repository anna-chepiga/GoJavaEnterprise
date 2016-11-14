package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.model.DishListDao;
import ua.goit.jdbc.model.entities.DishList;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDishListDao implements DishListDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(DishListDao.class);

    @Override
    public DishList searchById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM dishes_list WHERE id=?")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createDishList(resultSet);
            } else {
                throw new RuntimeException("Cannot find dish list with id " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    private DishList createDishList(ResultSet resultSet) throws SQLException {
        DishList dishList = new DishList();
        dishList.setDishId1(resultSet.getInt("dish_id_1"));
        dishList.setDishId2(resultSet.getInt("dish_id_2"));
        dishList.setDishId3(resultSet.getInt("dish_id_3"));
        dishList.setDishId4(resultSet.getInt("dish_id_4"));
        dishList.setDishId5(resultSet.getInt("dish_id_5"));
        dishList.setDishId6(resultSet.getInt("dish_id_6"));
        dishList.setDishId7(resultSet.getInt("dish_id_7"));
        dishList.setDishId8(resultSet.getInt("dish_id_8"));
        dishList.setDishId9(resultSet.getInt("dish_id_9"));
        dishList.setDishId10(resultSet.getInt("dish_id_10"));
        return dishList;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
