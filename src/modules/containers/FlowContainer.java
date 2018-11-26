package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.FlowContainerDevice;

import java.util.concurrent.TimeUnit;

public class FlowContainer<T extends FlowContainerDevice> extends Container<FlowContainerDevice> {

    private final int MULTIPLIER = 10;

    //Constructor
    public FlowContainer(String name, int capacity, Consumable consumable, FlowContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (isPlugged()) {
            if (quantity <= getConsumable().getQuantity() && getType().equals(DeviceType.FlowContainer)) {
                int streamRate = getDevice().streamRate();
                try {
                    TimeUnit.SECONDS.sleep(quantity / (MULTIPLIER * streamRate));
                    consumer.acceptAndLoad(getConsumable().getPart(quantity));
                    getDevice().streamOut(getDevice());
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
                int streamRate = getDevice().streamRate();
                try {
                    TimeUnit.SECONDS.sleep(getConsumable().getQuantity() / streamRate);
                    consumer.acceptAndLoad(getConsumable());
                    getDevice().streamOut(getDevice());
                    setConsumable(null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public T getDevice() {
        T device = (T)super.getDevice();
        return device;
    }
}
