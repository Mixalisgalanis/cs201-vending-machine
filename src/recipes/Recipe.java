package recipes;

import recipes.consumables.ingredients.Ingredient;

import java.util.ArrayList;

public class Recipe {

    //Class variables
    private String name;
    private int code;
    private int cost;
    private String type;
    private boolean available;

    private ArrayList<Ingredient> ingredients;

    //Constructor
    public Recipe(String name, int code, int cost, String type) {
        this.name = name;
        this.code = code;
        this.cost = cost;
        this.type = type;
        this.available = false;
    }

    //Getter & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
