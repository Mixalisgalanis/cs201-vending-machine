package modules.containers.processor;

import behaviour.Consumer;
import modules.containers.FlowContainer;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;

import java.util.concurrent.TimeUnit;

public class IngredientProcessor<T extends ProcessorDevice> extends FlowContainer<ProcessorDevice> implements Processor {

    //class variables
    private boolean loaded;
    private boolean processed;
    private boolean plugged;

    //Constructors
    public IngredientProcessor(String name, int capacity, Consumable consumable, ProcessorDevice device) {
        super(name, capacity, consumable, device);
        this.loaded = false;
        this.processed = false;
        this.plugged = false;
    }

    public IngredientProcessor(ProcessorDevice device) {
        super(device);
    }

    //Other Methods
    @Override
    public void process(int duration) {
        if (loaded) {
            getDevice().operateStart();
            try {
                TimeUnit.SECONDS.sleep(duration);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            processed = true;
            getDevice().operateStop();
        } else
            processed = false;
    }

    @Override
    public void acceptAndLoad(Consumable consumable) {
        if (plugged) {
            if (getConsumable() == null) {
                if (consumable.getQuantity() <= getCapacity()) {
                    setConsumable(consumable);
                    loaded = true;
                }
            } else {
                if (!getConsumable().getName().equals(consumable.getName())) {
                    loaded = false;
                } else if (consumable.getQuantity() + getConsumable().getQuantity() <= getCapacity()) {
                    loaded = true;
                    getConsumable().setQuantity(getConsumable().getQuantity() + consumable.getQuantity());
                }
            }
        }
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (processed && plugged) {
            if (quantity <= getConsumable().getQuantity()) {
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        if (processed && plugged) {
            consumer.acceptAndLoad(getConsumable());
            setConsumable(null);
        }
    }

    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {
            this.plugged = true;
            consumer.plug(this);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            this.plugged = false;
            consumer.unPlug(this);
        }
    }

    @Override
    public void unPlugAll() {

    }

    @Override
    public boolean isPlugged() {
        return plugged;
    }

    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    public String generateEffect() {
        return getName().toLowerCase().substring(0, getName().length() - 2) + "d " + getConsumable().getName();
    }
}
