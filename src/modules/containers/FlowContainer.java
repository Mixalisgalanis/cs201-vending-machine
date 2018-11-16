package modules.containers;

import devices.containers.FlowContainerDevice;
import recipes.consumables.Consumable;

public class FlowContainer<T extends FlowContainerDevice> extends Container<FlowContainerDevice> {

    //Constructor
    public FlowContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }
}
