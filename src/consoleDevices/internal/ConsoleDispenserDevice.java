package consoleDevices.internal;

import consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.ContainerDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;

import java.util.ArrayList;


public class ConsoleDispenserDevice extends ConsoleDevice implements DispenserDevice {

    private ArrayList<ContainerDevice> containerDevices;

    public ConsoleDispenserDevice(String name) {
        super(name, DeviceType.Dispenser);
        this.containerDevices = new ArrayList<>();
    }

    @Override
    public void prepareContainer(ContainerDevice containerDevice) {
        System.out.println("Prepared " + containerDevice.getName());
    }

    @Override
    public ArrayList<ContainerDevice> listContainers() {
        return containerDevices;
    }


    @Override
    public void addContainer(ContainerDevice containerDevice) {
        this.containerDevices.add(containerDevice);
        System.out.println("Added " + containerDevice.getName());
    }

    @Override
    public void removeContainer(String name) {
        for (int i = 0; i < containerDevices.size(); i++) {
            if (containerDevices.get(i).getName().equalsIgnoreCase(name)) {
                this.containerDevices.remove(containerDevices.get(i));
                System.out.println("Deleted " + name);
                return;
            }
        }
    }
}
