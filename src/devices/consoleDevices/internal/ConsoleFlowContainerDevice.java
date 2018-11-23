package devices.consoleDevices.internal;

import devices.Device;
import devices.containers.FlowContainerDevice;

public class ConsoleFlowContainerDevice extends ConsoleContainerDevice implements FlowContainerDevice {

    int streamRate;
    public ConsoleFlowContainerDevice(String name, int capacity) {
        super(name, capacity);
        this.streamRate = 5;
    }

    @Override
    public int streamRate() {
        return this.streamRate;
    }

    @Override
    public void streamOut(Device device) {
        System.out.println(device.getName() + " streamed out.");
    }
}
