package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.DosingContainerDevice;

public class ConsoleDosingContainerDevice extends ConsoleContainerDevice implements DosingContainerDevice {
    @Override
    public void releaseDose(Device device) {

    }

    @Override
    public int doseSize() {
        return 0;
    }
}
