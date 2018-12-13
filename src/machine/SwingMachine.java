package machine;

import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwingMachine implements HardwareMachine {

    //Class variables
    private final HashMap<String, Device> devices;


    //Constructor
    public SwingMachine() {
        devices = new HashMap<>();
    }


    //Other Methods
    @Override
    public List<Device> listDevices() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public String getModel() {
        return null;
    }


    public void addDevice(Device device) {
        devices.put(device.getName(), device);
    }
}
