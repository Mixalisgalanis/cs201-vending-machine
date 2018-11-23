package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.MaterialContainerDevice;

public class ConsoleMaterialContainerDevice extends ConsoleContainerDevice implements MaterialContainerDevice {

    public ConsoleMaterialContainerDevice(int capacity) {
        super("Material container", capacity);
    }

    @Override
    public void releaseMaterial(Device device) {
        System.out.println("Tranfered from Material Container to " + device.getName());
    }
}
