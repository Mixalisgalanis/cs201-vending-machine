package modules.containers;

import behaviour.Consumer;
import behaviour.Provider;
import modules.Module;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.ContainerDevice;

abstract public class Container<T extends ContainerDevice> extends Module<ContainerDevice> implements Provider {

    //Class Variables
    private int capacity;
    private Consumable consumable;
    private boolean plugged;

    //Constructor
    Container(String name, int capacity, Consumable consumable, T device) {
        super(name, device);
        this.capacity = capacity;
        this.consumable = consumable;
        plugged = false;
    }

    Container(T device) {
        super(device);
        capacity = device.getCapacity();
    }

    //Getters & Setters
    @Override
    public T getDevice() {
        return (T) super.getDevice();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        assert plugged;
        if (quantity <= getConsumable().getQuantity()) {
            consumer.acceptAndLoad(getConsumable().getPart(quantity));
        }
    }

    @Override
    public void provide(Consumer consumer) {
        assert plugged;
        consumer.acceptAndLoad(getConsumable());
        setConsumable(null);
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
