package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.ContainerDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDispenserDevice extends ConsoleDevice implements DispenserDevice {

    private final List<ContainerDevice> containerDevices;

    public ConsoleDispenserDevice(String name, DeviceType deviceType) {
        super(name, deviceType);
        containerDevices = new ArrayList<>();
    }

    @Override
    public void prepareContainer(ContainerDevice containerDevice) {
        System.out.println("Prepared " + containerDevice.getName());
    }

    @Override
    public List<ContainerDevice> listContainers() {
        return containerDevices;
    }


    @Override
    public void addContainer(ContainerDevice containerDevice) {
        containerDevices.add(containerDevice);
//        System.out.println("Added " + containerDevice.getName());
    }

    @Override
    public void removeContainer(String name) {
        for (int i = 0; i < containerDevices.size(); i++) {
            if (containerDevices.get(i).getName().equalsIgnoreCase(name)) {
                containerDevices.remove(containerDevices.get(i));
                System.out.println("Deleted " + name);
                return;
            }
        }
    }
}

