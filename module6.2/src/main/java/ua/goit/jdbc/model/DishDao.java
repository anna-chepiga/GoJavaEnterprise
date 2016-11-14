package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.Dish;

import java.util.List;

public interface DishDao {
    List<Dish> getAll();

    Dish searchByName(String name);

    int add(Dish dish);

    void deleteById(int id);
}
