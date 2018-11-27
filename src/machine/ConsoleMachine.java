package machine;

import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import java.util.HashMap;
import java.util.List;

public class ConsoleMachine implements HardwareMachine {

    //Class variables
    private HashMap<String, Device> devices;

    //Constructor
    public ConsoleMachine() {
        devices = new HashMap<>();
    }

    //Methods
    @Override
    public List<Device> listDevices() {
        return (List<Device>) devices.values();
    }

    @Override
    public String getModel() {
        return null;
    }


    public void addDevice(Device device) {
        this.devices.put(device.getName(), device);
    }
}
