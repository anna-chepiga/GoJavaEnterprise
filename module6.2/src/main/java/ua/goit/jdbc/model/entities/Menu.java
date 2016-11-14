package ua.goit.jdbc.model.entities;

public class Menu {
    private int id;
    private String name;
    private int dishesList;

    public Menu(String name, int dishesList) {
        this.name = name;
        this.dishesList = dishesList;
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDishesList() {
        return dishesList;
    }

    public void setDishesList(int dishesList) {
        this.dishesList = dishesList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishesList=" + dishesList +
                '}';
    }
}
