package machine;


import modules.Module;
import modules.ModuleFactory;
import modules.containers.Container;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import java.util.HashMap;

public class SoftwareMachine {

    //Class variables
    private static SoftwareMachine instance;
    private HashMap<String, Module> modules;

    //Constructor
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

    public void refillContainers() {
        for (Module module : modules.values()) {
            if (module instanceof Container) {
                ((Container) module).getConsumable().refill(((Container) module).getCapacity());
            }
        }
    }

}
