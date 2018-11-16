package recipes.step;

import behaviour.Consumer;
import behaviour.Provider;
import modules.dispensers.Dispenser;

public class TransferStep extends RecipeStep {

    //Class variables
    private String source;
    private String destination;
    private String content;
    private int quantity;

    //Constructor
    public TransferStep(String source, String destination, String content, int quantity) {
        this.source = source;
        this.destination = destination;
        this.content = content;
        this.quantity = quantity;
    }

    public TransferStep(String[] data, int quantity) {
        this.source = data[0];
        this.destination = data[1];
        this.content = data[2];
        this.quantity = quantity;
    }

    //Getters & Setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Other Methods
    /**
     * Creates a string which describes this step - ex: "TRANSFER POWDERS BLENDER COFFEE 40"
     *
     * @return the String created
     */
    @Override
    public String describe() {
        return "TRANSFER " + getSource() + " " + getDestination() + " " + getContent() + " " + getQuantity();
    }

    @Override
    public void executeStep() {
        if (data.findDispenser(source) != null){
            Dispenser dispenser = data.findDispenser(source);
            Consumer consumer = data.findConsumer(destination);

            dispenser.plug(consumer);
            dispenser.prepareContainer(data.findContainerByConsumable(source,data.findConsumable(content)).getName(),consumer);
            dispenser.unPlug(consumer);
        } else{
            Provider provider = data.findProvider(source);
            Consumer consumer = data.findConsumer(destination);

            provider.plug(consumer);
            provider.provide(consumer,quantity);
            provider.unPlug(consumer);
        }
    }
}
