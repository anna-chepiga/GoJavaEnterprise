package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.jdbc.controllers.IngredientController;
import ua.goit.jdbc.model.entities.Storage;
import ua.goit.jdbc.model.StorageDao;
import ua.goit.jdbc.model.entities.Ingredient;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcStorageDao implements StorageDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageDao.class);
    private IngredientController ingredientController;
    private Storage storage = Storage.getInstance();

        /*storage = Storage.getInstance();
        Map<String, Integer> ingredients = getAll();
        storage.setIngredientMap(ingredients);*/

    @Override
    public void addIngredient(String ingredientName, int amount) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            Ingredient ingredient = ingredientController.searchByName(ingredientName);
            int ingredientId = ingredient.getId();

            boolean ingredientIsInStorage = checkIngredientPresentInStorage(ingredientId);

            if (ingredientIsInStorage) {
                changeIngredientAmount(ingredientName, amount, true);
                LOGGER.info("Ingredient present in the storage => changed the amount");
            } else {
                statement.executeUpdate("INSERT INTO storage (ingredient_id, amount) VALUES (ingredientId, amount)");
                Map<String, Integer> ingredients = getAll();
                storage.setIngredientMap(ingredients);
                LOGGER.info("Successfully added " + amount + " items of " + ingredientName);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int ingredientId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM storage WHERE ingredient_id = ?")) {
            statement.setInt(1, ingredientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void changeIngredientAmount(String ingredientName, int amount, boolean add) {
        Ingredient ingredient = ingredientController.searchByName(ingredientName);
        int ingredientId = ingredient.getId();

        Map<String, Integer> allIngredients = getAll();
        int currentAmount = allIngredients.get(ingredientName);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE storage SET amount = ? WHERE ingredient_id = ?")) {

            statement.setInt(2, ingredientId);

            if (add) {
                statement.setInt(1, currentAmount + amount);
            } else {
                statement.setInt(1, currentAmount - amount);
            }

            statement.executeUpdate();

            allIngredients = getAll();
            storage.setIngredientMap(allIngredients);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> searchByName(String name) {
        Map<String, Integer> result = new HashMap<>();
        Ingredient ingredient = ingredientController.searchByName(name);
        int ingredientId = ingredient.getId();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM storage WHERE ingredient_id=?")) {
            statement.setInt(1, ingredientId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result.put(name, resultSet.getInt("amount"));
            } else {
                throw new RuntimeException("Cannot find item with ingredient name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Map<String, Integer> getAll() {
        Map<String, Integer> result = new HashMap<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT storage.ingredient_id, ingredient.name, storage.amount " +
                    "FROM storage, ingredient WHERE storage.ingredient_id = ingredient.id");

            while (resultSet.next()) {
                result.put(resultSet.getString("name"), resultSet.getInt("amount"));
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Map<String, Integer> getEndingIngredients() {
        Map<String, Integer> result = new HashMap<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT ingredient.name, storage.amount " +
                     "FROM storage, ingredient WHERE storage.ingredient_id = ingredient.ID AND storage.amount < 40")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.put(resultSet.getString("name"), resultSet.getInt("amount"));
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private boolean checkIngredientPresentInStorage(int ingredientId) {
        boolean isInStorage = false;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT count(ingredient_id) FROM storage " +
                     "WHERE ingredient_id = ?")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                isInStorage = resultSet.getInt("ingredient_id") > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return isInStorage;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setIngredientController(IngredientController ingredientController) {
        this.ingredientController = ingredientController;
    }
}