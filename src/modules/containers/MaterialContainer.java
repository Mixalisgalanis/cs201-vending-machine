package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.MaterialContainerDevice;

public class MaterialContainer extends Container<MaterialContainerDevice> {

    //Constructor
    public MaterialContainer(String name, int capacity, Consumable consumable, MaterialContainerDevice device) {
        super(name, capacity, consumable, device);
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
