package consoleDevices;


import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.util.List;

public class ConsoleDevice implements Device {

    private String name;
    private DeviceType deviceType;
    private boolean connected;

    public ConsoleDevice(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
        this.connected = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void connect(Device device) {
        if (!this.connected) {
            this.connected = true;
            this.connect(this);
            System.out.println(this.name + "connected");
        }
    }

    @Override
    public void disconnect(Device device) {
        if (this.connected) {
            this.connected = false;
            System.out.println(this.name + "disconnected");
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
        //TODO
        return null;
    }

    @Override
    public DeviceType getType() {
        return deviceType;
    }
}
