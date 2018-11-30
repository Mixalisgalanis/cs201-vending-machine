package machine;


import modules.Module;
import modules.ModuleFactory;
import modules.containers.Container;
import modules.dispensers.ConsumableDispenser;
import modules.dispensers.Dispenser;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareMachine {

    //Class variables
    private static SoftwareMachine instance;
    private HashMap<String, Module> modules;

    //Constructors
    private SoftwareMachine() {
        modules = new HashMap<>();
        //Prevent further instantiation
        instance = this;
    }

    private SoftwareMachine(HardwareMachine machine) {
        modules = new HashMap<>();
        //Prevents further instantiation
        instance = this;
        probeHardware(machine);
    }

    //Other Methods
    public Module getModule(String moduleName) {
        return modules.get(moduleName);
    }

    public static SoftwareMachine getInstance() {
        return (instance != null) ? instance : new SoftwareMachine();
    }

    public static SoftwareMachine getInstance(HardwareMachine machine) {
        return (instance != null) ? instance : new SoftwareMachine(machine);
    }

    private void probeHardware(HardwareMachine machine) {
        for (Device device : machine.listDevices()) {
            modules.put(device.getType().toString(), ModuleFactory.createModule(device));
        }
    }

    public void addConsumable(Consumable consumable){
        ArrayList<ConsumableDispenser> dispensers = getDispensers();
        for (ConsumableDispenser dispenser : dispensers){
            if (dispenser.getConsumableType().equals(consumable.getConsumableType())){
                Container container = dispenser.getNextAvailableContainer();
                container.setName(consumable.getName() + Container.class.getSimpleName());
                container.setConsumable(consumable);
            }
        }
    }

    public void refillContainers() {
        for (Module module : modules.values()) {
            if (module instanceof Container) {
                ((Container) module).getConsumable().refill(((Container) module).getCapacity());
            }
        }
    }

    public ArrayList<ConsumableDispenser> getDispensers(){
        ArrayList<ConsumableDispenser> dispensers = new ArrayList<>();
        for (Module module : modules.values()){
            if (module.getType() == DeviceType.DosingDispenser ||
                    module.getType() == DeviceType.FlowDispenser ||
                    module.getType() == DeviceType.MaterialDispenser)
                dispensers.add((ConsumableDispenser) module);
        }
        return dispensers;
    }

    public HashMap<String,Container> getContainers(){
        HashMap<String,Container> containers = new HashMap<>();
        for (ConsumableDispenser dispenser : getDispensers()){
            for (Container container: dispenser.getContainers().values()) {
                containers.put(container.getName(), container);
            }
        }
        return containers;
    }

    public HashMap<String,Consumable> getConsumables(){
        HashMap<String,Consumable> consumables = new HashMap<>();
        for (ConsumableDispenser dispenser : getDispensers()){
            for (Container container: dispenser.getContainers().values()) {
                consumables.put(container.getConsumable().getName(), container.getConsumable());
            }
        }
        return consumables;
    }

    public Container getContainer (String name){
        for ( Module module: modules.values()){
            if ((module.getType() == DeviceType.DosingContainer||
                    module.getType() == DeviceType.FlowContainer||
                    module.getType() == DeviceType.MaterialContainer)&&
                    ((Container) module).getConsumable().getName().equalsIgnoreCase(name)){
                    return (Container)module;
            }
            if (module.getName().equalsIgnoreCase(name) &&
                    (module.getType() == DeviceType.Processor||
                    module.getType() == DeviceType.ProductCase)){
                return (Container)module;
            }
        }
        return  null;
    }

    public Dispenser findDispenser (String name){
        for ( Module module: modules.values()){
            if (module.getType() == DeviceType.DosingDispenser ||
                    module.getType() == DeviceType.FlowDispenser ||
                    module.getType() == DeviceType.MaterialDispenser)
                if (((ConsumableDispenser) module).getContainers().containsKey(name)) return (Dispenser) module;
        }
        return null;
    }
}