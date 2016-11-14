package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.CookedDishDao;
import ua.goit.jdbc.model.entities.CookedDish;

import java.util.List;

public class CookedDishController {
    private CookedDishDao cookedDishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(CookedDishController.class);

    public void add(CookedDish cookedDish) {
        if (cookedDish != null) {
            int addedID = cookedDishDao.add(cookedDish);
            LOGGER.info("Successfully added cooked dish with ID " + addedID);
        } else {
            LOGGER.error("Trying to add NULL dish");
        }
    }

    public List<CookedDish> getAll() {
        return cookedDishDao.getAll();
    }

    public void setCookedDishDao(CookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) { }
}
