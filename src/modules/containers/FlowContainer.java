package modules.containers;

import behaviour.Consumer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.FlowContainerDevice;

import java.util.concurrent.TimeUnit;

public class FlowContainer<T extends FlowContainerDevice> extends Container<FlowContainerDevice> {

    private static int instance = 1;

    //Constructors
    public FlowContainer(String name, int capacity, Consumable consumable, FlowContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public FlowContainer(FlowContainerDevice device) {
        super(device);
        setName(getClass().getSimpleName() + (instance++));
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert isPlugged();
        int remainingQuantity = quantity;
        if (remainingQuantity <= getConsumable().getQuantity() && getType().equals(DeviceType.FlowContainer)) {
            int streamRate = getDevice().streamRate();
            while (remainingQuantity > 0) {
                getDevice().streamOut(getDevice());
                remainingQuantity -= streamRate;
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
                try {
                    TimeUnit.MILLISECONDS.sleep(300);

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
        return (T) super.getDevice();
    }
}
