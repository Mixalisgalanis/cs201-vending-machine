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

/**
 * This Singleton class contains every list of every component to be ever added. It can be accessed from any class using
 * Data.getInstance() method. All modules,containers,consumableDispensers,ingredientProcessors,consumables,dispensers,
 * consumers,providers are stored and managed here
 */
public class Data {

    /*
    Class variables
     */
    private static Data instance;
    private static boolean allowInstance = true;
    public final int STANDARD_DOSING_CONTAINER_SIZE = 500;
    public final int XL_DOSING_CONTAINER_SIZE = 1000;
    public final int STANDARD_FLOW_CONTAINER_SIZE = 1000;
    public final int XL_FLOW_CONTAINER_SIZE = 2000;
    public final int STANDARD_MATERIAL_CONTAINER_SIZE = 20;
    public final int XL_MATERIAL_CONTAINER_SIZE = 40;
    //Data Classes Collections
    private HashMap<String, Module> modules;
    private HashMap<String, Container> containers;
    private HashMap<String, ConsumableDispenser> consumableDispensers;
    private HashMap<String, IngredientProcessor> ingredientProcessors;
    private HashMap<String, Consumable> consumables;
    //Data Interfaces Collections
    private HashMap<String, Dispenser> dispensers;
    private HashMap<String, Consumer> consumers;
    private HashMap<String, Provider> providers;


    /*
    Constructor
     */

    /**
     * Initializes all lists and prevents this class from being instantiated again.
     */
    public Data() {
        modules = new HashMap<>();
        containers = new HashMap<>();
        consumableDispensers = new HashMap<>();
        ingredientProcessors = new HashMap<>();
        consumables = new HashMap<>();
        dispensers = new HashMap<>();
        consumers = new HashMap<>();
        providers = new HashMap<>();

        //Prevents other instantiations
        instance = this;
        allowInstance = false;
    }

    /*
    Getters
     */
    public static Data getInstance() {
        return instance;
    }

    public HashMap<String, Module> getModules() {
        return modules;
    }

    public HashMap<String, Container> getContainers() {
        return containers;
    }

    public HashMap<String, Consumable> getConsumables() {
        return consumables;
    }

    /*
    Other Methods
     */

    /**
     * This is the main insertion method for hardware objects. Depending on the type of the module being added, it may
     * also get added automatically in other behavioural interfaces collections like dispensers, consumers or providers.
     *
     * @param module that is being added
     */
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
            findConsumableDispenserByContainer((Container) module).addContainer((Container) module);
        } else if (module instanceof ProductCase) {
            consumers.put(module.getName(), (ProductCase) module);
        }
        modules.put(module.getName(), module);
    }

    /**
     * Main removal method for hardware objects. Works similar to addHardwareEntity method. The module is found by a name
     * and removed from any other possible lists.
     *
     * @param moduleName required to find Module
     * @see public void addHardwareEntity(Module module)
     */
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
            } else if (module instanceof ProductCase) {
                consumers.remove(module.getName());
            }
            modules.remove(module.getName());
        }
    }

    /**
     * Insertion method for Consumables.
     *
     * @param consumable to be added in consumables HashMap.
     */
    public void addConsumable(Consumable consumable) {
        consumables.put(consumable.getName(), consumable);
    }

    /**
     * Removal method for Consumables. It also removes the Consumable from any containers that it may be added.
     *
     * @param consumableName required to find Consumable and remove it.
     */
    public void removeConsumable(String consumableName) {
        consumables.remove(consumableName);
        for (Container container : containers.values()) {
            if (container.getConsumable().getName().equals(consumableName)) container.setConsumable(null);
        }
    }


    //Simple Finder Methods - Usually they get results from HashMap.get(key) method

    /**
     * Finds a Consumable (Class) based on it's name.
     *
     * @param consumableName required to find a Consumable.
     * @return the Consumable if found. Returns null if not found.
     */
    public Consumable findConsumable(String consumableName) {
        return consumables.get(consumableName);
    }

    /**
     * Finds a ConsumableDispenser (Class) based on it's name.
     *
     * @param consumableDispenserName required to find a ConsumableDispenser.
     * @return the ConsumableDispenser if found. Returns null if not found.
     */
    public ConsumableDispenser findConsumableDispenser(String consumableDispenserName) {
        return consumableDispensers.get(consumableDispenserName);
    }

    /**
     * Finds a IngredientProcessor (Class) based on it's name.
     *
     * @param ingredientProcessorName required to find an IngredientProcessor.
     * @return the IngredientProcessor if found. Returns null if not found.
     */
    public IngredientProcessor findIngredientProcessor(String ingredientProcessorName) {
        return ingredientProcessors.get(ingredientProcessorName);
    }

    /**
     * Finds a Provider (Interface) based on it's name.
     *
     * @param providerName required to find a Provider.
     * @return the Provider if found. Returns null if not found.
     */
    public Provider findProvider(String providerName) {
        return providers.get(providerName);
    }

    /**
     * Finds a Consumer (Interface) based on it's name.
     *
     * @param consumerName required to find a Consumer.
     * @return the Consumer if found. Returns null if not found.
     */
    public Consumer findConsumer(String consumerName) {
        return consumers.get(consumerName);
    }

    /**
     * Finds a Dispenser (Interface) based on it's name.
     *
     * @param dispenserName required to find a Dispenser.
     * @return the Dispenser if found. Returns null if not found.
     */
    public Dispenser findDispenser(String dispenserName) {
        return dispensers.get(dispenserName);
    }

    //More Complicated Finder Methods

    /**
     * Finds a Container based on a dispenser name and a consumable - ex: "POWDERS" + Coffee (object) -> CoffeeContainer (object).
     * This is used in Recipe Steps.
     *
     * @param dispenserName the name of the ConsumableDispenser.
     * @param consumable    the consumable object.
     * @return the Container if found. Returns null if not found.
     */
    public Container findContainerByConsumable(String dispenserName, Consumable consumable) {
        ConsumableDispenser consumableDispenser = findConsumableDispenser(dispenserName);
        for (Container container : consumableDispenser.getContainers().values()) {
            if (container.getConsumable().getName().equalsIgnoreCase(consumable.getName())) return container;
        }
        return null;
    }

    /**
     * Finds a Consumable Dispenser based on a container name - ex: "WaterContainer" -> LIQUIDS (object)
     *
     * @param container required to find Consumable Dispenser
     * @return the Consumable Dispenser if found. Returns null if not found.
     */
    public ConsumableDispenser findConsumableDispenserByContainer(Container container) {
        for (ConsumableDispenser consumableDispenser : consumableDispensers.values()) {
            if (consumableDispenser.getConsumableType().equals(container.getConsumable().getConsumableType()))
                return consumableDispenser;
        }
        return null;
    }
}
