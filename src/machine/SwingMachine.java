package machine;

import tuc.ece.cs201.vm.hw.device.Device;

import java.util.HashMap;

public class SwingMachine implements Machine{

    //Class variables
    private HashMap<String, Device> devices;


    //Constructor
    public SwingMachine() {
        devices = new HashMap<>();
    }


    //Other Methods
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
