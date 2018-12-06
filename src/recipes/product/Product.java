package recipes.product;

import recipes.consumables.Consumable;

import java.util.HashMap;

public class Product {

    //Class variables
    private final String productName;

    private HashMap<String, Consumable> consumables;

    //Constructor
    public Product(String productName) {
        this.productName = productName;
    }

    public HashMap<String, Consumable> getConsumables() {
        return consumables;
    }
}
