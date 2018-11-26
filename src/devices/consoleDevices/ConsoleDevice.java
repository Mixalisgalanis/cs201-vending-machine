package devices.consoleDevices;


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
        this.connected = false;
        if (this.connected) {
            this.disconnect(this);
            System.out.println(this.name + "disconnected");
        }

    }

    @Override
    public void disconnectAll() {
        List<Device> devices = listConnectedDevices();
        for (int i = 0; i < devices.size(); i++) {
            disconnect(devices.get(i));
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
