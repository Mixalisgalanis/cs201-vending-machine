package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

import java.util.concurrent.TimeUnit;

public class DosingContainer extends Container<DosingContainerDevice> {

    private static int instance = 1;

    //Constructors
    public DosingContainer(String name, int capacity, Consumable consumable, DosingContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public DosingContainer(DosingContainerDevice device) {
        super(device);
        setName(getClass().getSimpleName() + (instance++));
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert isPlugged();
        int remainingQuantity = quantity;
        if (remainingQuantity <= getConsumable().getQuantity()) {
            int dose = getDevice().doseSize();
            while (remainingQuantity > 0) {
                getDevice().releaseDose(getDevice());
                remainingQuantity -= dose;
                consumer.acceptAndLoad(getConsumable().getPart(dose));
                try {
                    TimeUnit.MILLISECONDS.sleep(300);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
