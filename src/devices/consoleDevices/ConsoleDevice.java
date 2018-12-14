package devices.consoleDevices;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDevice implements Device {

    private final String name;
    private final DeviceType deviceType;
    private static ArrayList<Device> connectedDevices;
    private boolean connected;

    public ConsoleDevice(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
        connectedDevices = new ArrayList<>();
        connected = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void connect(Device device) {
        if (!connected) {
            connected = true;
            System.out.println("[" + name.toUpperCase() + " connected to " + device.getName().toUpperCase() + ".]");
            device.connect(this);
            connectedDevices.add(this);
        }
    }

    @Override
    public void disconnect(Device device) {
        if (connected) {
            connected = false;
            System.out.println("[" + name.toUpperCase() + " disconnected from " + device.getName().toUpperCase() + ".]");
            device.disconnect(this);
            connectedDevices.remove(this);
        }
    }

    @Override
    public void disconnectAll() {
        connectedDevices.clear();
    }

    @Override
    public List<Device> listConnectedDevices() {
        return connectedDevices;
    }

    @Override
    public DeviceType getType() {
        return deviceType;
    }
}
