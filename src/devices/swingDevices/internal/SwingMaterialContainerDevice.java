package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.MaterialContainerDevice;

import java.awt.*;

public class SwingMaterialContainerDevice extends SwingContainerDevice implements MaterialContainerDevice {

    public SwingMaterialContainerDevice(String name, DeviceType deviceType, int capacity, int x, int y, int width, int height, int border, Color color) {
        super(name, deviceType, capacity, x, y, width, height, border, color);
    }

    @Override
    public void releaseMaterial(Device device) {
        //TODO Complete Function
    }
}
