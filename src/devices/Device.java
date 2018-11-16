package devices;

import machine.Machine;

public interface Device extends Machine{

    String getName();

    void connect(Device device);

    void disconnect(Device device);

    Device[] listConnected();

    void disconnectAll();

    DeviceType getType();
}
