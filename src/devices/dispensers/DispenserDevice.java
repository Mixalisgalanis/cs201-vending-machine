package devices.dispensers;

import devices.Device;

public interface DispenserDevice extends Device{

    T[] listContainers();

    void prepareContainer(T containerDevice);

    void addContainer(T containerDevice);

    void removeContainer(String name);
}
