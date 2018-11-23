package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.DosingContainerDevice;

public class ConsoleDosingContainerDevice extends ConsoleContainerDevice implements DosingContainerDevice {

    int dose;

    public ConsoleDosingContainerDevice(String name, int capacity) {
        super(name, capacity);
        this.dose = 5;
    }

    @Override
    public void releaseDose(Device device) {
        System.out.println(device.getName() + " released dose.");
    }

    @Override
    public int doseSize() {
        return dose;
    }
}
