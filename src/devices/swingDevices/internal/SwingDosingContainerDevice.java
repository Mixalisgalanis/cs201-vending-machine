package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DosingContainerDevice;

import java.awt.*;

public class SwingDosingContainerDevice extends SwingContainerDevice implements DosingContainerDevice {
    private final int doseSize = 5;

    public SwingDosingContainerDevice(String name, DeviceType deviceType, int capacity, int x, int y, int width, int height, int border, Color color) {
        super(name, deviceType, capacity, x, y, width, height, border, color);
    }

    @Override
    public void releaseDose(Device device) {
        //TODO Complete Function
    }

    @Override
    public int doseSize() {
        return doseSize;
    }
}
