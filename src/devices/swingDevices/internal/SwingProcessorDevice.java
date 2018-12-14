package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;

import java.awt.*;

public class SwingProcessorDevice extends SwingFlowContainerDevice implements ProcessorDevice {

    public SwingProcessorDevice(String name, DeviceType deviceType, int capacity, int x, int y, int width, int height, int border, Color color) {
        super(name, deviceType, capacity, x, y, width, height, border, color);
    }

    @Override
    public void streamIn() {
        //TODO Complete Function
    }

    @Override
    public void operateStart() {
        //TODO Complete Function
    }

    @Override
    public void operateStop() {
        //TODO Complete Function
    }

    @Override
    public String getProcessingLabel() {
        //TODO Complete Function
        return null;
    }
}
