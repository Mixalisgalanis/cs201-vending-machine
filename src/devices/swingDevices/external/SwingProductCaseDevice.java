package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

import javax.swing.*;
import java.util.List;

public class SwingProductCaseDevice extends JPanel implements ProductCaseDevice {

    @Override
    public void lock() {

    }

    @Override
    public void unLock() {

    }

    @Override
    public boolean isLocked() {
        return false;
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
    public void loadIngredient(String s) {

    }

    @Override
    public void putMaterial(String s) {

    }
}
