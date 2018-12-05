package recipes.step;

import behaviour.Consumer;
import behaviour.Provider;
import modules.containers.processor.IngredientProcessor;
import modules.dispensers.ConsumableDispenser;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.Ingredient;

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
        source = data[0];
        destination = data[1];
        content = data[2];
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
        if (sm.getDispensers().get(source) != null) {
            ConsumableDispenser dispenser = sm.getDispensers().get(source);
            Consumer consumer = sm.findProcessor(nameDecoder(destination));
            if (destination.equalsIgnoreCase("cup_case")) {
                consumer = sm.getProductCase();
            }

            String containerName = sm.findContainer(nameDecoder(content)).getName();
            dispenser.plug(consumer);
            dispenser.prepareContainer(containerName, consumer);
            dispenser.getContainer(containerName).provide(consumer, quantity);

            Consumable consumable = dispenser.getContainer(containerName).getConsumable();
            if (consumer instanceof IngredientProcessor && consumable instanceof Ingredient) {
                ((IngredientProcessor) consumer).addProcessedIngredients((Ingredient) consumable);
            }
            dispenser.unPlug(consumer);
        } else {
            Provider provider = (Provider) sm.findProcessor(nameDecoder(source));
            Consumer consumer = sm.findProcessor(nameDecoder(destination));
            provider.plug(consumer);
            if (consumer instanceof IngredientProcessor && provider instanceof IngredientProcessor) {
                ((IngredientProcessor) consumer).addProcessedIngredients(((IngredientProcessor) provider).getProcessedIngredient());
            }
            provider.provide(consumer);
            provider.unPlug(consumer);
        }
    }
}
