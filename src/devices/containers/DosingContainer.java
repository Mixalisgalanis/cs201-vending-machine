package devices.containers;

import devices.Device;

public interface DosingContainer extends ContainerDevice {

    void releaseDose(Device device);

    int doseSize();
}
