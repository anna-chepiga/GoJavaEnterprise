package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.IngredientDao;
import ua.goit.jdbc.model.entities.Ingredient;

public class IngredientController {
    private IngredientDao ingredientDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);

    public Ingredient searchByName(String name) {
        if (name == null) {
            throw new RuntimeException("Trying to use NULL name");
        }

        return ingredientDao.searchByName(name);
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
    }
}
