package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.CookedDish;

import java.util.List;

public interface CookedDishDao {
    int add(CookedDish cookedDish);
    List<CookedDish> getAll();

}
