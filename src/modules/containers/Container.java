package modules.containers;

import behaviour.Consumer;
import behaviour.Provider;
import devices.containers.ContainerDevice;
import modules.Module;
import recipes.consumables.Consumable;

abstract public class Container<T extends ContainerDevice> extends Module<T> implements Provider {

    //Class Variables
    private int capacity;
    private Consumable consumable;
    private boolean plugged;

    //Constructor
    Container(String name, int capacity, Consumable consumable) {
        super(name);
        this.capacity = capacity;
        this.consumable = consumable;
        this.plugged = false;
    }

    //Getters & Setters
    public int getCapacity() {
        return capacity;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    //Implemented Methods
    @Override
    public void provide(Consumer consumer, int quantity) {
        if (plugged) {
            if (quantity <= getConsumable().getQuantity()) {
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        if (plugged) {
            consumer.acceptAndLoad(getConsumable());
            setConsumable(null);
        }
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

    }

    @Override
    public boolean isPlugged() {
        return plugged;
    }


    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

}
