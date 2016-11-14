package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.Dish;
import ua.goit.jdbc.model.entities.Menu;

import java.util.List;

public interface MenuDao {
    int add(Menu menu);

    void deleteById(int id);

    boolean addDishToMenu(Dish dish, String menuName);

    boolean addDishToMenu(String dishName, String menuName);

    void deleteDishFromMenu(String dishName, String menuName);

    Menu searchByName(String name);

    List<Menu> getAll();
}
