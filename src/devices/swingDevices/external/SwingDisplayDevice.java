package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DisplayDevice;

import javax.swing.*;
import java.util.List;

public class SwingDisplayDevice extends JPanel implements DisplayDevice {

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
    public void displayMsg(String s) {

    }

    @Override
    public void clear() {

    }
}
