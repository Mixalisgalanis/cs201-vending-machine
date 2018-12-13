package machine;

import behaviour.Consumer;
import modules.Module;
import modules.ModuleFactory;
import modules.containers.Container;
import modules.containers.processor.IngredientProcessor;
import modules.containers.processor.Processor;
import modules.dispensers.ConsumableDispenser;
import modules.dispensers.Dispenser;
import recipes.consumables.Consumable;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.Liquid;
import recipes.consumables.ingredients.Powder;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.HashMap;

public class SoftwareMachine {
    //Container Sizes
    private static final int POWDER_CONTAINER_REGULAR_SIZE = 500;
    private static final int LIQUID_CONTAINER_REGULAR_SIZE = 1000;
    private static final int SMALL_CUP_CONTAINER = 25;
    private static final int BIG_CUP_CONTAINER = 15;
    public static final int PROCESSOR_CONTAINER_SIZE = 500;

    //Class variables
    private static SoftwareMachine instance;
    private final HashMap<String, Module> modules;

    //Constructors
    private SoftwareMachine() {
        modules = new HashMap<>();
        //Prevent further instantiation
        instance = this;
        insertConsumables();
    }

    private SoftwareMachine(HardwareMachine machine) {
        modules = new HashMap<>();
        //Prevents further instantiation
        instance = this;
        probeHardware(machine);
        insertConsumables();
    }

    public static SoftwareMachine getInstance() {
        return (instance != null) ? instance : new SoftwareMachine();
    }

    public static SoftwareMachine getInstance(HardwareMachine machine) {
        return (instance != null) ? instance : new SoftwareMachine(machine);
    }

    /**
     * Inserts all consumables needed
     */
    private void insertConsumables() {
        //Powders
        addConsumable(new Powder("Coffee", POWDER_CONTAINER_REGULAR_SIZE));
        addConsumable(new Powder("Sugar", POWDER_CONTAINER_REGULAR_SIZE));
        //Renames HashMap Key

        //Liquids
        addConsumable(new Liquid("Water", LIQUID_CONTAINER_REGULAR_SIZE));
        addConsumable(new Liquid("Milk", LIQUID_CONTAINER_REGULAR_SIZE));

        //Materials
        addConsumable(new Cup("SmallCup", SMALL_CUP_CONTAINER, "Small"));
        addConsumable(new Cup("BigCup", BIG_CUP_CONTAINER, "Big"));
    }

    //Other Methods
    public Module getModule(String moduleName) {
        return modules.get(moduleName);
    }

    private void probeHardware(HardwareMachine machine) {
        for (Device device : machine.listDevices()) {
            Module module = ModuleFactory.createModule(device);
            if (module != null) {
                modules.put(module.getName(), module);
            }
        }
    }

    public void addConsumable(Consumable consumable) {
        for (ConsumableDispenser dispenser : getDispensers().values()) {
            if (dispenser.getConsumableType().equals(consumable.getConsumableType())) {
                Container container = dispenser.getContainer(consumable);
                container.setName(consumable.getName() + Container.class.getSimpleName());
                container.setConsumable(consumable);
                return;
            }
        }
    }

    public void refillContainers() {
        for (Container container : getContainers().values()) {
            container.getConsumable().refill(container.getCapacity());
        }
    }


    //Data Gathering
    public HashMap<String, ConsumableDispenser> getDispensers() {
        HashMap<String, ConsumableDispenser> dispensers = new HashMap<>();
        for (Module module : modules.values()) {
            if (module.getType() == DeviceType.DosingDispenser ||
                    module.getType() == DeviceType.FlowDispenser ||
                    module.getType() == DeviceType.MaterialDispenser) {
                dispensers.put(module.getName(), (ConsumableDispenser) module);
            }
        }
        return dispensers;
    }

    public HashMap<String, IngredientProcessor> getProcessors() {
        HashMap<String, IngredientProcessor> processors = new HashMap<>();
        for (Module module : modules.values()) {
            if (module.getType() == DeviceType.Processor) {
                processors.put(module.getName(), (IngredientProcessor) module);
            }
        }
        return processors;
    }

    public HashMap<String, Container> getContainers() {
        HashMap<String, Container> containers = new HashMap<>();
        for (ConsumableDispenser dispenser : getDispensers().values()) {
            for (Container container : dispenser.getContainers().values()) {
                containers.put(container.getName(), container);
            }
        }
        return containers;
    }

    public HashMap<String, Consumable> getConsumables() {
        HashMap<String, Consumable> consumables = new HashMap<>();
        for (Container container : getContainers().values()) {
            consumables.put(container.getConsumable().getName(), container.getConsumable());
        }
        /*
        TO BE TESTED - LAMDA EXPRESSION
        getContainers().values().forEach((Container container) -> consumables.put(container.getConsumable().getName()
                , container.getConsumable()));*/
        return consumables;
    }

    public Consumer getProductCase() {
        for (Module module : modules.values()) {
            if (module.getType().equals(DeviceType.ProductCase)) {
                return (Consumer) module;
            }
        }
        return null;
    }


    //Finder Methods
    public Container findContainer(String name) {
        for (Container container : getContainers().values()) {
            if (container.getConsumable() != null && container.getConsumable().getName().equalsIgnoreCase(name)) {
                return container;
            }
        }
        return null;
    }

    public Processor findProcessor(String name) {
        for (IngredientProcessor processor : getProcessors().values()) {
            if (processor.getDevice().getName().equalsIgnoreCase(name + "Device")) {
                return processor;
            }
        }
        return null;
    }

    public Dispenser findDispenser(String containerName) {
        for (ConsumableDispenser dispenser : getDispensers().values()) {
            if (dispenser.getContainers().containsKey(containerName)) {
                return dispenser;
            }
        }
        return null;
    }
}