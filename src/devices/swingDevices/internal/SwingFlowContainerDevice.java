package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.FlowContainerDevice;

import java.awt.*;

public class SwingFlowContainerDevice extends SwingContainerDevice implements FlowContainerDevice {
    private final int streamRate = 5;

    public SwingFlowContainerDevice(String name, DeviceType deviceType, int capacity, int x, int y, int width, int height, int border, Color color) {
        super(name, deviceType, capacity, x, y, width, height, border, color);
    }

    @Override
    public void streamOut(Device device) {
        //TODO Complete Function
    }

    @Override
    public int streamRate() {
        return streamRate;
    }
}
