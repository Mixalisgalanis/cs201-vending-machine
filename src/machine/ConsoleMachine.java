package machine;

import tuc.ece.cs201.vm.hw.device.Device;

import java.util.HashMap;

public class ConsoleMachine implements Machine {

    //Class variables
    private HashMap<String, Device> devices;

    //Constructor
    public ConsoleMachine() {
        devices = new HashMap<>();
    }

    //Methods
    @Override
    public Device[] listDevices() {
        return (Device[]) devices.values().toArray();
    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public void addDevice(Device device) {
        this.devices.put(device.getName(), device);
    }
}
