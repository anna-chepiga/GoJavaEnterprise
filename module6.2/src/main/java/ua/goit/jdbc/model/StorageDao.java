package ua.goit.jdbc.model;

import java.util.Map;

public interface StorageDao {
    void addIngredient(String ingredientName, int amount);
    void delete(int ingredientId);
    void changeIngredientAmount(String ingredientName, int amount, boolean add); //true - adding amount, false - deducting amount
    Map<String, Integer> searchByName(String name);
    Map<String, Integer> getAll();
    Map<String, Integer> getEndingIngredients(); // ending means < 40
}
