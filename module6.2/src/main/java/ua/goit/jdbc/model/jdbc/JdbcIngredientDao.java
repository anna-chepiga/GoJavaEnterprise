package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.model.IngredientDao;
import ua.goit.jdbc.model.entities.Ingredient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcIngredientDao implements IngredientDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientDao.class);

    @Override
    public Ingredient searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredient WHERE name=?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createIngredient(resultSet);
            } else {
                throw new RuntimeException("Cannot find ingredient with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    private Ingredient createIngredient(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        return ingredient;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
