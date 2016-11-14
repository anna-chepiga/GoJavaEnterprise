package ua.goit.jdbc.model.entities;

public class Dish {
    private int id;
    private String name;
    private int category;
    private int ingredientsList;
    private double price;
    private int weight;

    public Dish(int id, String name, int category, int ingredientsList, double price, int weight) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredientsList = ingredientsList;
        this.price = price;
        this.weight = weight;
    }

    public Dish(String name, int category, int ingredientsList, double price, int weight) {
        this.name = name;
        this.category = category;
        this.ingredientsList = ingredientsList;
        this.price = price;
        this.weight = weight;
    }

    public Dish() {
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(int ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", ingredientsList=" + ingredientsList +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
