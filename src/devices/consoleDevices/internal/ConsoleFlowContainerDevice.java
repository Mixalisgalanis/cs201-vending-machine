package devices.consoleDevices.internal;

import devices.Device;
import devices.DeviceType;
import devices.containers.FlowContainerDevice;

public class ConsoleFlowContainerDevice extends ConsoleContainerDevice implements FlowContainerDevice {

    private final int STREAM_RATE_SIZE = 5;

    public ConsoleFlowContainerDevice(String name, int capacity) {
        super(name, DeviceType.FlowContainer, capacity);
    }

    @Override
    public int streamRate() {
        return this.STREAM_RATE_SIZE;
    }

    @Override
    public void streamOut(Device device) {
        System.out.println("Streamed out " + "ml of " + device.getName() + ".");
    }
}
