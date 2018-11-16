package modules.containers;

import devices.containers.MaterialContainerDevice;
import recipes.consumables.Consumable;

public class MaterialContainer extends Container<MaterialContainerDevice> {

    //Constructor
    public MaterialContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }
}
