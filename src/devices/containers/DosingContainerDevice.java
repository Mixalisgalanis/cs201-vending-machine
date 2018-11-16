package devices.containers;

import devices.Device;

public interface DosingContainerDevice extends ContainerDevice {

    void releaseDose(Device device);

    int doseSize();
}
