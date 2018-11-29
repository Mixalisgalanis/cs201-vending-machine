package modules.external;

import behaviour.Consumer;
import modules.Module;
import recipes.Recipe;
import recipes.consumables.Consumable;
import recipes.product.Product;
import recipes.product.ProductBuilder;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer {

    //Class variables
    private boolean pluggable;
    private ProductBuilder builder;
    private Product product;


    //Constructor
    public ProductCase(String productName, int procuctCost, ProductCaseDevice device) {
        super("ProductCase", device);
        this.pluggable = false;
        this.builder = new ProductBuilder(productName, procuctCost);
    }

    public ProductCase(ProductCaseDevice device) {
        super("ProductCase", device);
    }


    //Other Methods
    @Override
    public void acceptAndLoad(Consumable consumable) {
        if (pluggable) {
            if (product == null) {
                this.product.setConsumables(consumable);
                getDevice().loadIngredient(consumable.toString());
                //TODO check what "toString" returns
            }
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
        //TODO check if any device operation is needed
        return product;
    }

    public void prepareProduct(Recipe recipe) {
        product = new Product(recipe.getName(), recipe.getPrice());
        builder.addConsumables();
    }
}
