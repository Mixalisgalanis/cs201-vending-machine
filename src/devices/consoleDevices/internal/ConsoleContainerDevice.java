package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;

public class ConsoleContainerDevice extends ConsoleDevice implements ContainerDevice {

    private String name;
    private boolean open;
    private int capacity;

    public ConsoleContainerDevice(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        open = false;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void open() {
        open = true;
        System.out.println(this.name + "is open");
    }

    @Override
    public void close() {
        open = false;
        System.out.println(this.name + "is closed");
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
