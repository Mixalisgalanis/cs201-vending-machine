package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.FlowContainerDevice;

public class SwingFlowContainerDevice extends SwingContainerDevice implements FlowContainerDevice {

    @Override
    public void streamOut(Device device) {

    }

    @Override
    public int streamRate() {
        return 0;
    }
}
