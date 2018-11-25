package modules.containers;

import behaviour.Consumer;
import devices.DeviceType;
import devices.containers.MaterialContainerDevice;
import recipes.consumables.Consumable;

public class MaterialContainer extends Container<MaterialContainerDevice> {

    //Constructor
    public MaterialContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }

    public void provide(Consumer consumer, int quantity) {
        if (isPlugged()) {
            if (quantity <= getConsumable().getQuantity() && getType().equals(DeviceType.MaterialContainer)) {
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
                ((MaterialContainerDevice) getDevice()).releaseMaterial(getDevice());
            }
        }
    }
}
