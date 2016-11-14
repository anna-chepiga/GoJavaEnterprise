package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.StorageDao;

import java.util.Map;

public class StorageController {
    private StorageDao storageDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageController.class);

    public void addIngredient(String ingredientName, int amount) {
        if (ingredientName != null && amount > 0) {
            storageDao.addIngredient(ingredientName, amount);
            LOGGER.info("Successfully added ingredient " + ingredientName + ", amount " + amount);
        } else {
            LOGGER.error("Cannot add NULL ingredient or negative amount");
        }
    }

    public void delete(int ingredientId) {
        if (ingredientId > 0) {
            storageDao.delete(ingredientId);
            LOGGER.info("Successfully deleted ingredient with id " + ingredientId);
        } else {
            LOGGER.error("Cannot delete ingredient with negative ID");
        }
    }

    public void changeIngredientAmount(String ingredientName, int amount, boolean add) {
        if (ingredientName != null && amount > 0) {
            storageDao.changeIngredientAmount(ingredientName, amount, add);

            if (add) {
                LOGGER.info("Successfully added " + amount + " items of ingredient " + ingredientName);
            } else {
                LOGGER.info("Successfully removed " + amount + " items of ingredient " + ingredientName);
            }
        } else {
            LOGGER.error("Trying to use NULL ingredient or negative amount");
        }
    }

    public Map<String, Integer> searchByName(String name) {
        if (name == null) {
            throw new RuntimeException("Cannot search ingredient by NULL name");
        }

        return storageDao.searchByName(name);
    }

    public Map<String, Integer> getAll() {
        return storageDao.getAll();
    }

    public Map<String, Integer> getEndingIngredients() {
        return storageDao.getEndingIngredients();
    }

    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) { }
}
