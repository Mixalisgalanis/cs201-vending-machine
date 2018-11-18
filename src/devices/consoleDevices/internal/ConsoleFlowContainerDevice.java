package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.FlowContainerDevice;

public class ConsoleFlowContainerDevice extends ConsoleContainerDevice implements FlowContainerDevice {
    @Override
    public int streamRate() {
        return 0;
    }

    @Override
    public void streamOut(Device device) {

    }
}
