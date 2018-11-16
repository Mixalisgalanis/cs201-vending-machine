package modules.external;

import behaviour.Consumer;
import behaviour.Lockable;
import devices.external.ProductCaseDevice;
import modules.Module;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.consumables.Consumable;
import recipes.product.Product;
import recipes.product.ProductBuilder;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer{

    //Class variables
    private boolean pluggable;
    private ProductBuilder builder;
    private Product product;



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
        if (pluggable) {
            if (product == null) this.product.setConsumables(consumable);
        }
        //TODO check if we need more if cases
    }
    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {
            setPlugged(true);
            consumer.setPlugged(true);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            setPlugged(false);
            consumer.setPlugged(false);
        }
    }

    @Override
    public void unPlugAll() {
        //TODO figure out what we're supposed to do here!
    }

    @Override
    public boolean isPlugged() {
        return this.pluggable;
    }

    @Override
    public void setPlugged(boolean plugged) {
        this.pluggable = plugged;
    }

    public Product getProduct() {
        //no need to construct product in here because we will prepare the container then fill it(acceptAndLoad)and then we just need to return the product
        return product;
    }

    public void prepareProduct(Recipe recipe){
        product = new Product(recipe.getName(),recipe.getPrice());
        builder.addConsumables();
    }
}
