package ua.goit.jdbc.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private int ingredientId;
    private int amount;
    private Map<String, Integer> ingredientMap = new HashMap<>();

    private static volatile Storage instance;

    public static Storage getInstance() {
        Storage localInstance = instance;
        if (localInstance == null) {
            synchronized (Storage.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Storage();
                }
            }
        }
        return localInstance;
    }

    public void setIngredientMap(Map<String, Integer> ingredientMap) {
        this.ingredientMap = ingredientMap;
    }

    public Map<String, Integer> getIngredientMap() {
        return ingredientMap;
    }
}
