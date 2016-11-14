package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.DishListDao;
import ua.goit.jdbc.model.entities.DishList;

public class DishListController {
    private DishListDao dishListDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(DishListController.class);

    public DishList searchById(int id) {
        if (id < 0) {
            throw new RuntimeException("Trying to use negative ID");
        }

        return dishListDao.searchById(id);
    }

    public void setDishListDao(DishListDao dishListDao) {
        this.dishListDao = dishListDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
    }
}
