package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.MaterialContainerDevice;

public class ConsoleMaterialContainerDevice extends ConsoleContainerDevice implements MaterialContainerDevice {

    public ConsoleMaterialContainerDevice(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void releaseMaterial(Device device) {
        System.out.println("Transferred from Material Container to " + device.getName());
    }
}