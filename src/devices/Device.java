package devices;

public interface Device {

    String getName();

    void connect(Device device);

    void disconnect(Device device);

    Device[] listConnected();

    void disconnectAll();

    DeviceType getType();
}
