package devices.consoleDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

public class ConsoleDosingContainerDevice extends ConsoleContainerDevice implements DosingContainerDevice {

    private final int DOSE_SIZE = 5;

    public ConsoleDosingContainerDevice(String name, int capacity) {
        super(name, DeviceType.DosingContainer, capacity);
    }

    @Override
    public int doseSize() {
        return DOSE_SIZE;
    }

    @Override
    public void releaseDose(Device device) {
        System.out.println("[" + getName() + "] - " + "Released " + DOSE_SIZE + "g of powder to " + device.getName());
    }
}
