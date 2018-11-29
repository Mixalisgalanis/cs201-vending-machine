package consoleDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

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
