package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.DishList;

public interface DishListDao {
    DishList searchById(int id);
}
