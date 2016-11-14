package ua.goit.jdbc.model.entities;

import java.util.ArrayList;
import java.util.List;

public class DishList {
    private int id;
    private int dishId1;
    private int dishId2;
    private int dishId3;
    private int dishId4;
    private int dishId5;
    private int dishId6;
    private int dishId7;
    private int dishId8;
    private int dishId9;
    private int dishId10;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId1() {
        return dishId1;
    }

    public void setDishId1(int dishId1) {
        this.dishId1 = dishId1;
    }

    public int getDishId2() {
        return dishId2;
    }

    public void setDishId2(int dishId2) {
        this.dishId2 = dishId2;
    }

    public int getDishId3() {
        return dishId3;
    }

    public void setDishId3(int dishId3) {
        this.dishId3 = dishId3;
    }

    public int getDishId4() {
        return dishId4;
    }

    public void setDishId4(int dishId4) {
        this.dishId4 = dishId4;
    }

    public int getDishId5() {
        return dishId5;
    }

    public void setDishId5(int dishId5) {
        this.dishId5 = dishId5;
    }

    public int getDishId6() {
        return dishId6;
    }

    public void setDishId6(int dishId6) {
        this.dishId6 = dishId6;
    }

    public int getDishId7() {
        return dishId7;
    }

    public void setDishId7(int dishId7) {
        this.dishId7 = dishId7;
    }

    public int getDishId8() {
        return dishId8;
    }

    public void setDishId8(int dishId8) {
        this.dishId8 = dishId8;
    }

    public int getDishId9() {
        return dishId9;
    }

    public void setDishId9(int dishId9) {
        this.dishId9 = dishId9;
    }

    public int getDishId10() {
        return dishId10;
    }

    public void setDishId10(int dishId10) {
        this.dishId10 = dishId10;
    }

    public List<Integer> getAllDishes() {
        List<Integer> dishes = new ArrayList<>();
        dishes.add(dishId1);
        dishes.add(dishId2);
        dishes.add(dishId3);
        dishes.add(dishId4);
        dishes.add(dishId5);
        dishes.add(dishId6);
        dishes.add(dishId7);
        dishes.add(dishId8);
        dishes.add(dishId9);
        dishes.add(dishId10);
        return dishes;
    }
}
