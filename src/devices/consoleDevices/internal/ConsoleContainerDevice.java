package devices.consoleDevices.internal;

import devices.consoleDevices.ConsoleDevice;
import devices.containers.ContainerDevice;

public class ConsoleContainerDevice extends ConsoleDevice implements ContainerDevice {
    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
