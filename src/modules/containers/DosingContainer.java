package modules.containers;

import behaviour.Consumer;
import devices.DeviceType;
import devices.containers.DosingContainerDevice;
import recipes.consumables.Consumable;

public class DosingContainer extends Container<DosingContainerDevice> {

    //Constructor
    public DosingContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (isPlugged()) {
            if (quantity <= getConsumable().getQuantity() && getType().equals(DeviceType.DosingContainer)) {
                int dose = ((DosingContainerDevice) getDevice()).doseSize();
                while (quantity > 0) {
                    consumer.acceptAndLoad(getConsumable().getPart(dose));
                    ((DosingContainerDevice) getDevice()).releaseDose(getDevice());
                    quantity -= dose;
                }
            }
        }
    }

}
