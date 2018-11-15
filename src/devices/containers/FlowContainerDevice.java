package devices.containers;

import devices.Device;

public interface FlowContainerDevice extends ContainerDevice {

    int streamRate();

    void streamOut(Device device);


}
