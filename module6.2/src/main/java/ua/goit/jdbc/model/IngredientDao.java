package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.Ingredient;

public interface IngredientDao {
    Ingredient searchByName(String name);
}
