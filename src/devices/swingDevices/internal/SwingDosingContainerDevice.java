package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

public class SwingDosingContainerDevice extends SwingContainerDevice implements DosingContainerDevice {

    @Override
    public void releaseDose(Device device) {

    }

    @Override
    public int doseSize() {
        return 0;
    }
}
