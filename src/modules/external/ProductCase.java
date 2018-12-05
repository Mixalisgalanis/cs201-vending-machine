package modules.external;

import behaviour.Consumer;
import modules.Module;
import recipes.Recipe;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.ProcessedIngredient;
import recipes.product.Product;
import recipes.product.ProductBuilder;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

import java.util.PrimitiveIterator;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer {

    //Class variables
    private boolean pluggable;
    private ProductBuilder builder;
    private Product product;
    private ProcessedIngredient processedIngredient;



    //Constructor
    public ProductCase(String productName, int procuctCost, ProductCaseDevice device) {
        super(device);
        setName(getClass().getSimpleName());
        product = new Product(productName, procuctCost);
        pluggable = false;
        builder = new ProductBuilder(productName, procuctCost);
        processedIngredient = new ProcessedIngredient("Cup");
    }

    public ProductCase(ProductCaseDevice device) {
        super("ProductCase", device);
        product = new Product();
        pluggable = false;
        processedIngredient = new ProcessedIngredient("Cup");
    }


    //Other Methods
    public void addProcessedIngredients(ProcessedIngredient p){this.processedIngredient.addIngredients(p);}

    @Override
    public void acceptAndLoad(Consumable consumable) {
        if (pluggable) {
            if (product == null) {
                product.setConsumables(consumable);
                getDevice().loadIngredient(consumable.toString());
                //TODO check what "toString" returns
            }
        }
        //TODO check if we need more if cases
    }

    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {
            getDevice().connect(((Module)consumer).getDevice());
            setPlugged(true);
            consumer.setPlugged(true);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            setPlugged(false);
            getDevice().disconnect(((Module)consumer).getDevice());
            consumer.setPlugged(false);
        }
    }

    @Override
    public void unPlugAll() {
        getDevice().disconnectAll();
        //TODO figure out what we're supposed to do here!
    }

    @Override
    public boolean isPlugged() {
        return pluggable;
    }

    @Override
    public void setPlugged(boolean plugged) {
        pluggable = plugged;
    }

    public Product getProduct() {
        //no need to construct product in here because we will prepare the container then fill it(acceptAndLoad)and then we just need to return the product
        return product;
    }

    public void prepareProduct(Recipe recipe) {
        product = new Product(recipe.getName(), recipe.getPrice());
        builder.addConsumables();
    }
}
