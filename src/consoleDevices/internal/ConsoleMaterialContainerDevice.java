package consoleDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.MaterialContainerDevice;

public class ConsoleMaterialContainerDevice extends ConsoleContainerDevice implements MaterialContainerDevice {

    public ConsoleMaterialContainerDevice(String name, int capacity) {
        super(name, DeviceType.MaterialContainer, capacity);
    }

    @Override
    public void releaseMaterial(Device device) {
        System.out.println("Transferred from Material Container to " + device.getName());
    }
}
