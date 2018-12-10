package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.MaterialContainerDevice;

public class MaterialContainer extends Container<MaterialContainerDevice> {

    //Constructors
    public MaterialContainer(String name, int capacity, Consumable consumable, MaterialContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public MaterialContainer(MaterialContainerDevice device) {
        super(device);
        setName((device.getName().contains("Device")) ? device.getName().substring(0, device.getName().indexOf("Device"))
                : device.getName());
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert isPlugged();
        if (quantity <= getConsumable().getQuantity()) {
            getDevice().open();
            consumer.acceptAndLoad(getConsumable().getPart(quantity));
            getDevice().releaseMaterial(getDevice());
            getDevice().close();
        }

    }
}
