package devices.swingDevices.internal;

import devices.swingDevices.SwingDevice;
import tuc.ece.cs201.vm.hw.device.ContainerDevice;

public class SwingContainerDevice extends SwingDevice implements ContainerDevice {

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
