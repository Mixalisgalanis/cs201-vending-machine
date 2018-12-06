package recipes.product;

import recipes.consumables.Consumable;
import recipes.consumables.Cup;

import java.util.HashMap;

public class Product {

    //Class variables
    private final String productName;

    private HashMap<String, Consumable> consumables;
    private Cup cup;

    //Constructor
    public Product(String productName) {
        this.productName = productName;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public HashMap<String, Consumable> getConsumables() {
        return consumables;
    }
}
