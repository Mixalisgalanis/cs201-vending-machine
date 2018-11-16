package modules.external;

import behaviour.Consumer;
import behaviour.Lockable;
import devices.external.ProductCaseDevice;
import modules.Module;
import recipes.RecipeManager;
import recipes.consumables.Consumable;
import recipes.product.Product;
import recipes.product.ProductBuilder;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer{

    //Class variables
    private boolean pluggable;
    private ProductBuilder builder;

    //Constructor
    public ProductCase(String productName, int procuctCost, RecipeManager recipeManager) {
        super("ProductCase");
        this.pluggable = false;
        this.builder = new ProductBuilder(productName,procuctCost,recipeManager);
    }

    public ProductCase(){
        super("ProductCase");
    }

    //Other Methods
    @Override
    public void acceptAndLoad(Consumable consumable) {

    }

    @Override
    public void plug(Consumer consumer) {

    }

    @Override
    public void unPlug(Consumer consumer) {

    }

    @Override
    public void unPlugAll() {

    }

    @Override
    public boolean isPlugged() {
        return false;
    }

    @Override
    public void setPlugged(boolean plugged) {

    }

    public Product getProduct() {
        //TODO Construct Product
        return null;
    }

    public void prepareProduct(){
        builder.addConsumables();
    }
}
