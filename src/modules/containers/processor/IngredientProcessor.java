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
        getConsumable().setName(getProcessingEffectName());
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

        //There isn't any consumable yet
        if (getConsumable() == null) {
            if (consumable.getQuantity() <= getCapacity()) { //There is enough space
                setConsumable(new ProcessedIngredient(consumable.getQuantity(), consumable.getConsumableType()));
                insertConsumable(consumable);
            }
        } else { //There is already a consumable
            if (consumable.getQuantity() + getConsumable().getQuantity() <= getCapacity()) { //There isn't enough space
                getConsumable().setQuantity(getConsumable().getQuantity() + consumable.getQuantity());
                insertConsumable(consumable);
            }
        }
    }

    private void insertConsumable(Consumable consumable) {
        if (consumable instanceof ProcessedIngredient) {//Consumable input is a list of ingredients
            for (Ingredient ingredient : ((ProcessedIngredient) consumable).getIngredients().values()) {
                ((ProcessedIngredient) getConsumable()).addIngredient(ingredient);
            }
        } else { //Consumable input is a single consumable
            ((ProcessedIngredient) getConsumable()).addIngredient((Ingredient) consumable);
        }
        getConsumable().setName(getIngredientName(consumable));
        getConsumable().setConsumableType("Liquid");
        getDevice().streamIn();
        loaded = true;
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
            getDevice().open();
            while (remainingQuantity > 0) {
                remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
            }
            getDevice().close();
        }
    }

    @Override
    public void provide(Consumer consumer) {
        assert processed;
        assert plugged;
        assert consumer != null;

        int remainingQuantity = getConsumable().getQuantity();
        int streamRate = getDevice().streamRate();
        getDevice().open();
        while (remainingQuantity > 0) {
            remainingQuantity = streamOut(consumer, remainingQuantity, streamRate);
        }
        setConsumable(null);
        processed = false;
        loaded = false;
        getDevice().close();
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

    private String getIngredientName(Consumable consumable) {
        String SEPARATOR = ", ";
        String result = (getConsumable().getName().equals("") ? "" : getConsumable().getName() + SEPARATOR);
        if (!getConsumable().getName().contains(consumable.getName())) {
            result = result.concat(consumable.getName() + SEPARATOR);
        }
        return result.substring(0, result.length() - SEPARATOR.length());
    }

    private String getProcessingEffectName() {
        return getName().toLowerCase().substring(0, getName().length() - 1) + "d (" + getConsumable().getName() + ")";
    }
}
