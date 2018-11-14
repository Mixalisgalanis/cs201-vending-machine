package modules.containers;

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
}
