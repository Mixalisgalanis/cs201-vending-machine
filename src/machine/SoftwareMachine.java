package machine;


import modules.Module;
import modules.ModuleFactory;
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

    private SoftwareMachine(HardwareMachine machine){
        modules = new HashMap<>();
        //Prevents further instantiation
        instance = this;
        probeHardware(machine);
    }

    //Other Methods
    public static SoftwareMachine getInstance() {
        return (instance != null) ? instance : new SoftwareMachine();
    }

    private void probeHardware(HardwareMachine machine){
        for (Device device : machine.listDevices()){
            modules.put(device.getName(), ModuleFactory.createModule(device));
        }
    }

}
