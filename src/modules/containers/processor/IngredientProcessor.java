package modules.containers.processor;

import behaviour.Consumer;
import modules.Module;
import modules.containers.FlowContainer;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.Ingredient;
import recipes.consumables.ingredients.ProcessedIngredient;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;

import java.util.concurrent.TimeUnit;

public class IngredientProcessor extends FlowContainer<ProcessorDevice> implements Processor {

    //class variables
    private boolean loaded;
    private boolean processed;
    private boolean plugged;

    //Constructors
    public IngredientProcessor(String name, int capacity, Consumable consumable, ProcessorDevice device) {
        super(name, capacity, consumable, device);
        loaded = false;
        processed = false;
        plugged = false;
    }

    public IngredientProcessor(ProcessorDevice device) {
        super(device);
        loaded = false;
        processed = false;
        plugged = false;
    }

    //Other Methods
    @Override
    public void process(int duration) {
        assert loaded;
        getDevice().operateStart();
        try {
            TimeUnit.SECONDS.sleep(duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDevice().operateStop();
        processed = true;
    }

    @Override
    public void acceptAndLoad(Consumable consumable) {
        assert plugged;
        assert consumable != null;

        if (getConsumable() == null) {
            if (consumable.getQuantity() <= getCapacity()) {
                setConsumable(new ProcessedIngredient(generateEffect(consumable.getName()), consumable.getQuantity(),
                        consumable.getConsumableType()));
                if (consumable instanceof ProcessedIngredient) {
                    for (Ingredient ingredient : ((ProcessedIngredient) consumable).getIngredients().values()) {
                        ((ProcessedIngredient) getConsumable()).addIngredient(ingredient);
                    }
                } else {
                    ((ProcessedIngredient) getConsumable()).addIngredient((Ingredient) consumable);
                }
                getDevice().streamIn();
                loaded = true;
            }
        } else {
            if (consumable.getQuantity() + getConsumable().getQuantity() <= getCapacity()) {
                getConsumable().setQuantity(getConsumable().getQuantity() + consumable.getQuantity());
                getConsumable().setName(generateEffect(consumable.getName()));
                if (consumable instanceof ProcessedIngredient) {
                    for (Ingredient ingredient : ((ProcessedIngredient) consumable).getIngredients().values()) {
                        ((ProcessedIngredient) getConsumable()).addIngredient(ingredient);
                    }
                } else {
                    ((ProcessedIngredient) getConsumable()).addIngredient((Ingredient) consumable);
                }
                getDevice().streamIn();
                loaded = true;
            } else if (!getConsumable().getName().equals(consumable.getName())) {
                loaded = false;
            }
        }
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        assert processed;
        assert plugged;
        assert consumer != null;
        assert quantity > 0;

        int remainingQuantity = quantity;
        if (remainingQuantity <= getConsumable().getQuantity()) {
            int streamRate = getDevice().streamRate();
            while (remainingQuantity > 0) {
                remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        assert processed;
        assert plugged;
        assert consumer != null;

        int remainingQuantity = getConsumable().getQuantity();
        int streamRate = getDevice().streamRate();
        while (remainingQuantity > 0) {
            remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
        }
    }

    @Override
    public void plug(Consumer consumer) {
        assert consumer != null;
        if (!isPlugged()) {
            getDevice().connect(((Module) consumer).getDevice());
            plugged = true;
            consumer.plug(this);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        assert consumer != null;
        if (isPlugged()) {
            getDevice().disconnect(((Module) consumer).getDevice());
            plugged = false;
            consumer.unPlug(this);
        }
    }

    @Override
    public void unPlugAll() {
        getDevice().disconnectAll();
    }

    @Override
    public boolean isPlugged() {
        return plugged;
    }

    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    public String generateEffect(String newConsumableName) {
        return getName().toLowerCase().substring(0, getName().length() - 2) + "d (" + newConsumableName + ")";
    }
}
