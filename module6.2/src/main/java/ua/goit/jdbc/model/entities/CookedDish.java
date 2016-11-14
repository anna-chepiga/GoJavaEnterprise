package ua.goit.jdbc.model.entities;

import java.sql.Date;

public class CookedDish {
    private int id;
    private int dishId;
    private int cookerId;
    private int orderId;
    private Date date;

    public CookedDish(int dishId, int cookerId, int orderId, Date date) {
        this.dishId = dishId;
        this.cookerId = cookerId;
        this.orderId = orderId;
        this.date = date;
    }

    public CookedDish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCookerId() {
        return cookerId;
    }

    public void setCookerId(int cookerId) {
        this.cookerId = cookerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", cookerId=" + cookerId +
                ", orderId=" + orderId +
                ", date=" + date +
                '}';
    }
}
