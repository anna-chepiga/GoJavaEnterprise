package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.jdbc.model.entities.Dish;
import ua.goit.jdbc.model.DishDao;

import java.util.List;

public class DishController {
    private DishDao dishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getAllDishes() {
        return dishDao.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish searchByName(String name) {
        if (name == null) {
            throw new RuntimeException("Cannot find dish by NULL name");
        }

        return dishDao.searchByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(Dish dish) {
        if (dish != null) {
            int addedID = dishDao.add(dish);
            LOGGER.info("Successfully added dish with ID " + addedID);
        } else {
            LOGGER.error("Cannot add a NULL dish");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDish(int id) {
        if (id > 0) {
            dishDao.deleteById(id);
            LOGGER.info("Successfully deleted dish with id " + id);
        } else {
            LOGGER.error("Cannot delete dish with negative ID");
        }
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
    }
}