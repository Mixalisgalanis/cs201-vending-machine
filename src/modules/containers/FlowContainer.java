package modules.containers;

import behaviour.Consumer;
import modules.Module;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.FlowContainerDevice;

import java.util.concurrent.TimeUnit;

public class FlowContainer<T extends FlowContainerDevice> extends Container<FlowContainerDevice> {


    //Constructors
    public FlowContainer(String name, int capacity, Consumable consumable, FlowContainerDevice device) {
        super(name, capacity, consumable, device);
    }

    public FlowContainer(FlowContainerDevice device) {
        super(device);
        setName((device.getName().contains("Device")) ? device.getName().substring(0, device.getName().indexOf("Device"))
                : device.getName());
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert isPlugged();
        assert consumer != null;
        int remainingQuantity = quantity;
        if (remainingQuantity <= getConsumable().getQuantity() && getType().equals(DeviceType.FlowContainer)) {
            int streamRate = getDevice().streamRate();
            getDevice().open();
            while (remainingQuantity > 0) {
                remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
            }
            getDevice().close();
        }
    }

    @Override
    public void provide(Consumer consumer) {
        assert isPlugged();
        assert consumer != null;
        assert getType().equals(DeviceType.FlowContainer);
        int remainingQuantity = getConsumable().getQuantity();
        if (getType().equals(DeviceType.FlowContainer)) {
            int streamRate = getDevice().streamRate();
            getDevice().open();
            while (remainingQuantity > 0) {
                remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
            }
            getDevice().close();
        }

    }

    protected int streamOut(Consumer consumer, int currentQuantity, int streamRate) {
        getDevice().streamOut(((Module) consumer).getDevice());
        int remainingQuantity = currentQuantity - streamRate;
        consumer.acceptAndLoad(getConsumable().getPart(streamRate));
        try {
            TimeUnit.MILLISECONDS.sleep(50);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return remainingQuantity;
    }

    @Override
    public T getDevice() {
        return (T) super.getDevice();
    }
}
