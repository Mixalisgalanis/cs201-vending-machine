package devices.consoleDevices.internal;

import devices.DeviceType;
import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;

public class ConsoleContainerDevice extends ConsoleDevice implements ContainerDevice {

    private boolean opened;
    private int capacity;

    public ConsoleContainerDevice(String name, DeviceType deviceType, int capacity) {
        super(name,deviceType);
        this.capacity = capacity;
        opened = false;
    }


    public ConsoleContainerDevice(String name, DeviceType deviceType) {
        super(name, deviceType);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void open() {
        opened = true;
        System.out.println(this.getName() + " is opened.");
    }

    @Override
    public void close() {
        opened = false;
        System.out.println(this.getName() + " is closed.");
    }

    @Override
    public boolean isOpened() {
        return opened;
    }
}
