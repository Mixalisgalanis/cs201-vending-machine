package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.ContainerDevice;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import javax.swing.*;
import java.util.List;

public class SwingContainerDevice extends JPanel implements ContainerDevice {


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void connect(Device device) {

    }

    @Override
    public void disconnect(Device device) {

    }

    @Override
    public void disconnectAll() {

    }

    @Override
    public List<Device> listConnectedDevices() {
        return null;
    }

    @Override
    public DeviceType getType() {
        return null;
    }

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
