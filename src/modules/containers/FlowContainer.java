package modules.containers;

import behaviour.Consumer;
import devices.DeviceType;
import devices.containers.FlowContainerDevice;
import recipes.consumables.Consumable;

import java.util.concurrent.TimeUnit;

public class FlowContainer<T extends FlowContainerDevice> extends Container<FlowContainerDevice> {

    //Constructor
    public FlowContainer(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (isPlugged()) {
            if (quantity <= getConsumable().getQuantity() && getType().equals(DeviceType.FlowContainer)) {
                int streamRate = ((FlowContainerDevice) getDevice()).streamRate();
                try {
                    TimeUnit.SECONDS.sleep(quantity / streamRate);
                    consumer.acceptAndLoad(getConsumable().getPart(quantity));
                    ((FlowContainerDevice) getDevice()).streamOut(getDevice());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        if (isPlugged()) {
            if (getType().equals(DeviceType.FlowContainer)) {
                int streamRate = ((FlowContainerDevice) getDevice()).streamRate();
                try {
                    TimeUnit.SECONDS.sleep(getConsumable().getQuantity() / streamRate);
                    consumer.acceptAndLoad(getConsumable());
                    ((FlowContainerDevice) getDevice()).streamOut(getDevice());
                    setConsumable(null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
