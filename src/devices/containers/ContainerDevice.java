package devices.containers;

import devices.Device;

public interface ContainerDevice extends Device {

    int getCapacity();

    void open();

    void close();

    boolean isOpen();
}
