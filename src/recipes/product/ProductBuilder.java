package recipes.product;

import recipes.RecipeManager;
import recipes.consumables.Consumable;

public class ProductBuilder {

    //Class variables
    private Product product;
    private RecipeManager recipeManager;

    public ProductBuilder(String productName, int productCost, RecipeManager recipeManager) {
        this.product = new Product(productName, productCost);
        this.recipeManager = recipeManager;
    }

    //Getters & Setters
    public Product getProduct(){
        return this.product;
    }

    public void setProductCost(int productCost) {
        this.product.setProductCost(productCost);
    }

    public void setProductName(String productName) {
        this.product.setProductName(productName);
    }

    //Other Methods
    public void addConsumable(Consumable consumable){

    }

    public void addConsumables(){

    }

}