package modules.containers;

import behaviour.Consumer;
import modules.Module;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

import java.util.concurrent.TimeUnit;

public class DosingContainer extends Container<DosingContainerDevice> {

    //Constructors
    public DosingContainer(String name, int capacity, Consumable consumable, DosingContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public DosingContainer(DosingContainerDevice device) {
        super(device);
        setName((device.getName().contains("Device")) ? device.getName().substring(0, device.getName().indexOf("Device"))
                : device.getName());
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert isPlugged();
        assert consumer != null;
        assert quantity > 0;
        int remainingQuantity = quantity;
        if (remainingQuantity <= getConsumable().getQuantity()) {
            int dose = getDevice().doseSize();
            getDevice().open();
            while (remainingQuantity > 0) {
                getDevice().releaseDose(((Module) consumer).getDevice());
                remainingQuantity -= dose;
                consumer.acceptAndLoad(getConsumable().getPart(dose));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getDevice().close();
        }
    }
}
