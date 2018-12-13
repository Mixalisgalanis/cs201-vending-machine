package machine.console;

import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsoleMachine implements HardwareMachine {

    //Class variables
    private final HashMap<String, Device> devices;
    private static ConsoleMachine instance;

    //Constructor
    private ConsoleMachine() {
        devices = new HashMap<>();
        instance = this;
    }

    //Methods
    @Override
    public List<Device> listDevices() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public String getModel() {
        return null;
    }

    public static ConsoleMachine getInstance() {
        return (instance != null) ? instance : new ConsoleMachine();
    }

    public void addDevice(Device device) {
        devices.put(device.getName(), device);
    }
}
