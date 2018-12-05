package consoleDevices;


import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDevice implements Device {

    private String name;
    private DeviceType deviceType;
    private boolean connected;
    private static ArrayList<Device> connectedDevices;

    public ConsoleDevice(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
        this.connected = false;
        connectedDevices = new ArrayList<Device>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void connect(Device device) {
        if (!this.connected) {
            this.connected = true;
            device.connect(this);
            System.out.println(this.name + " connected");
            this.connectedDevices.add(this);
        }
    }

    @Override
    public void disconnect(Device device) {
        if (this.connected) {
            this.connected = false;
            System.out.println(this.name + " disconnected");
            this.connectedDevices.remove(this);
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
