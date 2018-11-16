package machine;

import behaviour.Consumer;
import behaviour.Provider;
import modules.Module;
import modules.containers.Container;
import modules.containers.processor.IngredientProcessor;
import modules.dispensers.ConsumableDispenser;
import modules.dispensers.Dispenser;
import modules.external.ProductCase;
import recipes.consumables.Consumable;

import java.util.HashMap;

public class Data {

    //Class Variables
    private static Data instance;
    private static boolean allowInstance = true;
    public final int STANDARD_DOSING_CONTAINER_SIZE = 500;
    public final int XL_DOSING_CONTAINER_SIZE = 1000;
    public final int STANDARD_FLOW_CONTAINER_SIZE = 1000;
    public final int XL_FLOW_CONTAINER_SIZE = 2000;
    public final int STANDARD_MATERIAL_CONTAINER_SIZE = 20;
    public final int XL_MATERIAL_CONTAINER_SIZE = 40;
    private HashMap<String, Module> modules;
    private HashMap<String, Container> containers;
    private HashMap<String, ConsumableDispenser> consumableDispensers;
    private HashMap<String, IngredientProcessor> ingredientProcessors;
    private HashMap<String, Consumable> consumables;

    private HashMap<String, Dispenser> dispensers;
    private HashMap<String, Consumer> consumers;
    private HashMap<String, Provider> providers;

    //Constructor
    public Data() {
        modules = new HashMap<>();
        containers = new HashMap<>();
        consumableDispensers = new HashMap<>();
        ingredientProcessors = new HashMap<>();
        instance = this;
        allowInstance = false;
    }

    //Getters & Setters
    public static Data getInstance() {
        return instance;
    }

    public HashMap<String, Module> getModules() {
        return modules;
    }

    public HashMap<String, Container> getContainers() {
        return containers;
    }

    public HashMap<String, ConsumableDispenser> getConsumableDispensers() {
        return consumableDispensers;
    }

    public HashMap<String, IngredientProcessor> getIngredientProcessors() {
        return ingredientProcessors;
    }

    //Other Methods

    public void addHardwareEntity(Module module) {
        if (module instanceof IngredientProcessor) {
            ingredientProcessors.put(module.getName(), (IngredientProcessor) module);
            providers.put(module.getName(), (IngredientProcessor) module);
            consumers.put(module.getName(), (IngredientProcessor) module);
        } else if (module instanceof ConsumableDispenser) {
            consumableDispensers.put(module.getName(), (ConsumableDispenser) module);
            dispensers.put(module.getName(), (ConsumableDispenser) module);
        } else if (module instanceof Container) {
            containers.put(module.getName(), (Container) module);
            providers.put(module.getName(), (Container) module);
            findConsumableDispenserByContainer(module.getName()).addContainer((Container) module);
        } else if (module instanceof ProductCase) {
            consumers.put(module.getName(), (ProductCase) module);
        }
        modules.put(module.getName(), module);
    }

    public void removeHardwareEntity(String moduleName) {
        Module module = modules.get(moduleName);
        if (module != null) {
            if (module instanceof IngredientProcessor) {
                ingredientProcessors.remove(module.getName());
                providers.remove(module.getName());
                consumers.remove(module.getName());
            } else if (module instanceof ConsumableDispenser) {
                consumableDispensers.remove(module.getName());
                dispensers.remove(module.getName());
            } else if (module instanceof Container) {
                containers.remove(module.getName());
                providers.remove(module.getName());
            } else if (module instanceof ProductCase){
                consumers.remove(module.getName());
            }
                modules.remove(module.getName());
        }
    }


    public void addConsumable(Consumable consumable) {
        consumables.put(consumable.getName(), consumable);
    }

    public void removeConsumable(String consumableName) {
        consumables.remove(consumableName);
    }


    //Finder Methods
    public Consumable findConsumable(String consumableName) {
        return consumables.get(consumableName);
    }

    public Provider findProvider(String providerName) {
        return providers.get(providerName);
    }

    public Consumer findConsumer(String consumerName) {
        return consumers.get(consumerName);
    }

    public Dispenser findDispenser(String dispenserName){
        return dispensers.get(dispenserName);
    }

    public ConsumableDispenser findConsumableDispenser(String consumableDispenserName){
        return consumableDispensers.get(consumableDispenserName);
    }

    public IngredientProcessor findIngredientProcessor(String ingredientProcessorName){
        return ingredientProcessors.get(ingredientProcessorName);
    }

    public Container findContainerByConsumable(String dispenserName, Consumable consumable){
        ConsumableDispenser consumableDispenser = findConsumableDispenser(dispenserName);
        for (Container container : consumableDispenser.getContainers().values()){
            if (container.getConsumable().getName().equalsIgnoreCase(consumable.getName())) return container;
        }
        return null;
    }

    public ConsumableDispenser findConsumableDispenserByContainer(String containerName){
        //TODO complete
        return null;
    }
}
