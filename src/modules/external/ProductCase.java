package modules.external;

import behaviour.Consumer;
import behaviour.Lockable;
import devices.external.ProductCaseDevice;
import modules.Module;
import recipes.consumables.Consumable;

public class ProductCase extends Module<ProductCaseDevice> implements Consumer{

    //Class variables
    private boolean pluggable;

    //Constructor
    public ProductCase() {
        super("Product Case");
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

    /*public Product getProduct() {
        //TODO Construct Product
        return null;
    }*/
}
