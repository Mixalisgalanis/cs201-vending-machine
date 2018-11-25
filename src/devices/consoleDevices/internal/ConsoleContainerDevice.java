package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;

public class ConsoleContainerDevice extends ConsoleDevice implements ContainerDevice {

    private String name;
    private boolean opened;
    private int capacity;

    public ConsoleContainerDevice(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        opened = false;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void open() {
        opened = true;
        System.out.println(this.name + " is opened.");
    }

    @Override
    public void close() {
        opened = false;
        System.out.println(this.name + " is closed.");
    }

    @Override
    public boolean isOpened() {
        return opened;
    }
}
