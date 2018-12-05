package modules.containers.processor;

import behaviour.Consumer;
import modules.Module;
import modules.containers.FlowContainer;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.Ingredient;
import recipes.consumables.ingredients.ProcessedIngredient;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;

import java.util.concurrent.TimeUnit;

public class IngredientProcessor<T extends ProcessorDevice> extends FlowContainer<ProcessorDevice> implements Processor {

    //class variables
    private boolean loaded;
    private boolean processed;
    private boolean plugged;
    private ProcessedIngredient processedIngredient;

    //Constructors
    public IngredientProcessor(String name, int capacity, Consumable consumable, ProcessorDevice device) {
        super(name, capacity, consumable, device);
        this.loaded = false;
        this.processed = false;
        this.plugged = false;
        processedIngredient = new ProcessedIngredient(getDevice().getProcessingLabel());
    }

    public IngredientProcessor(ProcessorDevice device) {
        super(device);
        processedIngredient = new ProcessedIngredient(getDevice().getProcessingLabel());
    }

    //Other Methods
    public ProcessedIngredient getProcessedIngredient(){return this.processedIngredient;}

    public void addProcessedIngredients(Ingredient p){
        this.processedIngredient.addIngredients(p);
    }

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
                    getDevice().streamIn();
                    loaded = true;
                  }
            } else {
                if (consumable.getQuantity() + getConsumable().getQuantity() <= getCapacity()) {
                    loaded = true;
                    getDevice().streamIn();
                    getConsumable().setQuantity(getConsumable().getQuantity() + consumable.getQuantity());
                   } else if (!getConsumable().getName().equals(consumable.getName())) {
                    loaded = false;
                }
            }
        }
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (processed && plugged) {
            if (quantity <= getConsumable().getQuantity()) {
                getDevice().streamOut(getDevice());
                consumer.acceptAndLoad(getConsumable().getPart(quantity));
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        if (processed && plugged) {
            if (consumer instanceof IngredientProcessor) ((IngredientProcessor)consumer).addProcessedIngredients(this.processedIngredient);
            getDevice().streamOut(getDevice());
            consumer.acceptAndLoad(getConsumable());
            setConsumable(null);
        }
    }

    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {
            getDevice().connect(((Module)consumer).getDevice());
            this.plugged = true;
            consumer.plug(this);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            getDevice().disconnect(((Module)consumer).getDevice());
            this.plugged = false;
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

    public String generateEffect() {
        return getName().toLowerCase().substring(0, getName().length() - 2) + "d " + getConsumable().getName();
    }
}
