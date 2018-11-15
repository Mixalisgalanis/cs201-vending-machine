package modules.external;

import behaviour.Consumer;
import behaviour.Lockable;
import modules.Module;
import recipes.consumables.Consumable;

public class ProductCase extends Module implements Consumer, Lockable{

    private boolean pluggable;

    public ProductCase() {
        super("Product Case");

    }

    //TODO Construct Product
	public Product getProduct() {
        return null;
	}

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

    @Override
    public void lock() {

    }

    @Override
    public void unLock() {

    }

    @Override
    public boolean isLocked() {
        return false;
    }
}
