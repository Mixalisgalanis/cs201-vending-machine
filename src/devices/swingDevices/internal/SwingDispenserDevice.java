package devices.swingDevices.internal;

import tuc.ece.cs201.vm.hw.device.ContainerDevice;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;

import javax.swing.*;
import java.util.List;

public class SwingDispenserDevice extends JPanel implements DispenserDevice {

    @Override
    public void prepareContainer(ContainerDevice containerDevice) {

    }

    @Override
    public void addContainer(ContainerDevice containerDevice) {

    }

    @Override
    public List listContainers() {
        return null;
    }

    @Override
    public void removeContainer(String s) {

    }

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
}
