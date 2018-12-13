package recipes.product;

import recipes.consumables.Consumable;
import recipes.consumables.Cup;

import java.util.HashMap;

public class Product {

    //Class variables
    private final String productName;

    private final HashMap<String, Consumable> consumables;
    private Cup cup;

    //Constructor
    public Product(String productName) {
        this.productName = productName;
        consumables = new HashMap<>();
    }

    //Setters & Getters
    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public String getProductName() {
        return productName;
    }

    public HashMap<String, Consumable> getConsumables() {
        return consumables;
    }
}
