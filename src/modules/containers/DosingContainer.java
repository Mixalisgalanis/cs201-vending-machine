package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

public class DosingContainer extends Container<DosingContainerDevice> {

    //Constructor
    public DosingContainer(String name, int capacity, Consumable consumable, DosingContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        int remainingQuantity = quantity;
        if (isPlugged()) {
            if (remainingQuantity <= getConsumable().getQuantity()) {
                int dose =  getDevice().doseSize();
                while (remainingQuantity > 0) {
                    consumer.acceptAndLoad(getConsumable().getPart(dose));
                    getDevice().releaseDose(getDevice());
                    remainingQuantity -= dose;
                }
            }
        }
    }

}
