package devices.consoleDevices;

import devices.Device;
import devices.DeviceType;

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
    public Device[] listConnected() {
        //TODO
        return new Device[0];
    }

    @Override
    public void disconnectAll() {
        Device[] devices = listConnected();
        for (int i =0; i<devices.length; i++){
            disconnect(devices[i]);
        }
    }

    @Override
    public DeviceType getType() {
        return deviceType;
    }
}
