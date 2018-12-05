package devices.consoleDevices;


import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDevice implements Device {

    private final String name;
    private final DeviceType deviceType;
    private boolean connected;
    private static ArrayList<Device> connectedDevices;

    public ConsoleDevice(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
        connected = false;
        connectedDevices = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void connect(Device device) {
        if (!connected) {
            connected = true;
            System.out.println(name + " connected to " + device.getName() + ".");
            device.connect(this);
            connectedDevices.add(this);
        }
    }

    @Override
    public void disconnect(Device device) {
        if (connected) {
            connected = false;
            System.out.println(name + " disconnected from " + device.getName() + ".");
            connectedDevices.remove(this);
            device.disconnect(this);
        }
    }

    @Override
    public void disconnectAll() {
        for (Device device : listConnectedDevices()) {
            disconnect(device);
        }
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
