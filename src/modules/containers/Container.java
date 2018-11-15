package modules.containers;

import behaviour.Consumer;
import devices.containers.ContainerDevice;
import recipes.consumables.Consumable;
import behaviour.Provider;
import modules.Module;

abstract public class Container extends Module implements Provider{
    //Class Variables
    private int capacity;
    private Consumable consumable;


    //Constructor
    Container(String name, int capacity, Consumable consumable) {
        super(name);
        this.capacity = capacity;
        this.consumable = consumable;
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

    }

    @Override
    public void provide(Consumer consumer) {

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
}
