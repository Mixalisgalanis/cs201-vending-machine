package modules.containers;

import devices.containers.DosingContainerDevice;
import recipes.consumables.Consumable;

public class DosingContainer extends Container<DosingContainerDevice> {

    //Constructor
    public DosingContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }


}
