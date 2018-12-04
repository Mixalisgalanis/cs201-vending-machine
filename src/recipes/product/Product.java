package recipes.product;

import recipes.consumables.Consumable;

public class Product {

    //Class variables
    private String productName;
    private int productCost;

    private Consumable consumables;

    //Constructor
    public Product(String productName, int productCost) {
        this.productName = productName;
        this.productCost = productCost;
    }

    public Product(){}

    //Getters & Setters

    public void setConsumables(Consumable consumables) {
        this.consumables = consumables;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}
