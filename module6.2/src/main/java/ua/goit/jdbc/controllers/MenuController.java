package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import ua.goit.jdbc.model.MenuDao;
import ua.goit.jdbc.model.entities.Dish;
import ua.goit.jdbc.model.entities.Menu;

import java.util.List;

public class MenuController {
    private MenuDao menuDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    public List<Menu> getAll() {
        return menuDao.getAll();
    }

    public Menu searchByName(String name) {
        if (name == null) {
            throw new RuntimeException("Cannot search menu by NULL name");
        }

        return menuDao.searchByName(name);
    }

    public void addMenu(Menu menu) {
        if (menu != null) {
            int addedID = menuDao.add(menu);
            LOGGER.info("Successfully added menu with id " + addedID);
        } else LOGGER.error("Cannot add a NULL menu");
    }

    public void deleteById(int id) {
        if (id > 0) {
            menuDao.deleteById(id);
            LOGGER.info("Successfully deleted menu with id " + id);
        } else {
            LOGGER.error("Cannot delete menu with negative idD");
        }
    }

    public void addDishToMenu(Dish dishName, String menuName) {
        if (dishName != null && menuName != null) {
            boolean added = menuDao.addDishToMenu(dishName, menuName);
            if (added) {
                LOGGER.info("Successfully added dish " + dishName.getName() + " to menu " + menuName);
            } else {
                LOGGER.info("The list is full, unable to add more dishes");
            }
        } else {
            LOGGER.error("Cannot add dish to menu: one of them is NULL");
        }
    }

    public void addDishToMenu(String dishName, String menuName) {
        if (dishName != null && menuName != null) {
            boolean added = menuDao.addDishToMenu(dishName, menuName);

            if (added) {
                LOGGER.info("Successfully added dish " + dishName + " to menu " + menuName);
            } else {
                LOGGER.info("The list is full, unable to add more dishes");
            }
        } else {
            LOGGER.error("Cannot add dish to menu: one of them is NULL");
        }
    }

    public void deleteDishFromMenu(String dishName, String menuName) {
        if (dishName != null && menuName != null) {
            menuDao.deleteDishFromMenu(dishName, menuName);
            LOGGER.info("Successfully deleted dish " + dishName + " from menu " + menuName);
        } else {
            LOGGER.error("Cannot delete dish from menu: one of them is NULL");
        }
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
    }
}
