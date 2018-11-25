package devices.consoleDevices.internal;

import devices.Device;
import devices.DeviceType;
import devices.containers.DosingContainerDevice;

public class ConsoleDosingContainerDevice extends ConsoleContainerDevice implements DosingContainerDevice {

    private final int DOSE_SIZE = 5;

    public ConsoleDosingContainerDevice(String name, int capacity) {
        super(name, DeviceType.DosingContainer, capacity);
    }

    @Override
    public void releaseDose(Device device) {
        System.out.println("Released " + DOSE_SIZE + "g of " + device.getName() + ".");
        //TODO Insert Timer
    }

    @Override
    public int doseSize() {
        return DOSE_SIZE;
    }
}
