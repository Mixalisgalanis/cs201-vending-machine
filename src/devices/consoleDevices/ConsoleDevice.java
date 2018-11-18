package devices.consoleDevices;

import devices.Device;
import devices.DeviceType;

public class ConsoleDevice implements Device {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void connect(Device device) {

    }

    @Override
    public void disconnect(Device device) {

    }

    @Override
    public Device[] listConnected() {
        return new Device[0];
    }

    @Override
    public void disconnectAll() {

    }

    @Override
    public DeviceType getType() {
        return null;
    }
}
