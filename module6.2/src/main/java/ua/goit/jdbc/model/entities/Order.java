package ua.goit.jdbc.model.entities;

import java.sql.Date;

public class Order {
    private int id;
    private int waiterId;
    private int dishesListId;
    private int tableNumber;
    private Date date;
    private boolean isOpen = true;

    public Order(int id, int waiterId, int dishesListId, int tableNumber, Date date) {
        this.id = id;
        this.waiterId = waiterId;
        this.dishesListId = dishesListId;
        this.tableNumber = tableNumber;
        this.date = date;
    }

    public Order(int waiterId, int dishesListId, int tableNumber, Date date) {
        this.waiterId = waiterId;
        this.dishesListId = dishesListId;
        this.tableNumber = tableNumber;
        this.date = date;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getDishesListId() {
        return dishesListId;
    }

    public void setDishesListId(int dishesListId) {
        this.dishesListId = dishesListId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void close() {isOpen = false;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", waiterId=" + waiterId +
                ", dishesListId=" + dishesListId +
                ", tableNumber=" + tableNumber +
                ", date=" + date +
                ", isOpen=" + isOpen +
                '}';
    }
}
