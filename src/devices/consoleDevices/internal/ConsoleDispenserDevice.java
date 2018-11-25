package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;
import devices.dispensers.DispenserDevice;

import java.util.ArrayList;


public class ConsoleDispenserDevice extends ConsoleDevice implements DispenserDevice {

    private ArrayList<ContainerDevice> containerDevices;

    public ConsoleDispenserDevice() {
        this.containerDevices = new ArrayList<ContainerDevice>();
    }

    @Override
    public ArrayList<ContainerDevice> listContainers() {
        return containerDevices;
    }

    @Override
    public void prepareContainer(ContainerDevice containerDevice) {
        System.out.println("Prepared " + containerDevice.getName());
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
