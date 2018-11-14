package modules.containers.processor;

import behaviour.Consumer;
import modules.containers.FlowContainer;
import recipes.consumables.Consumable;

import java.util.concurrent.TimeUnit;

public class IngredientProcessor extends FlowContainer implements Processor {

    private boolean loaded;
    private boolean processed;
    private boolean plugged;


    public IngredientProcessor(String name, int capacity, Consumable consumable) {
        super(name, capacity, consumable);
        this.loaded = false;
        this.processed = false;
    }

    @Override
    public void process(int duration) {
        if (loaded) {
            try {
                TimeUnit.SECONDS.sleep(duration);
                processed = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        processed = false;
    }

    @Override
    public void acceptAndLoad(Consumable consumable) {
        if (getConsumable() == null){
            if (consumable.getQuantity()<=getCapacity()) {
                setConsumable(consumable);
                loaded = true;
            }
        } else{
            if (!getConsumable().getName().equals(consumable.getName())){
                loaded = false;
            } else if (consumable.getQuantity() + getConsumable().getQuantity() <= getCapacity()){
                loaded = true;
                getConsumable().setQuantity(getConsumable().getQuantity() + consumable.getQuantity());
            }
        }
    }

    @Override
    public void provide(Consumer consumer, int quantity) {
        if (processed){
            if (quantity <= getConsumable().getQuantity()){
                consumer.acceptAndLoad(getConsumable());
                getConsumable().setQuantity(getConsumable().getQuantity() - quantity);
            }
        }
    }

    @Override
    public void provide(Consumer consumer) {
        if (processed){
            consumer.acceptAndLoad(getConsumable());
            setConsumable(null);
        }
    }

    @Override
    public void plug(Consumer consumer) {
    }

    @Override
    public void unPlug(Consumer consumer) {

    }

    @Override
    public void unPlugAll() {

    }

    @Override
    public boolean isPlugged() {
        return false;
    }

}
