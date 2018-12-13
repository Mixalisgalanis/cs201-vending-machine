package machine;

import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import java.util.HashMap;
import java.util.List;

public class SwingMachine implements HardwareMachine {

    public final int POWDER_CONTAINER_REGULAR_SIZE = 500;
    public final int LIQUID_CONTAINER_REGULAR_SIZE = 1000;
    public final int CUP_CONTAINER_REGULAR_SIZE = 20;

    //Class variables
    private HashMap<String, Device> devices;


    //Constructor
    public SwingMachine() {
        devices = new HashMap<>();
    }


    //Other Methods
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
