package modules.containers;

import recipes.consumables.Consumable;

public class MaterialContainer extends Container {

    //Constructor
    public MaterialContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }
}
