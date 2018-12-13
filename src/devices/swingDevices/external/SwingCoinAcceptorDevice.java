package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.CoinAcceptorDevice;
import tuc.ece.cs201.vm.hw.device.Device;

import javax.swing.*;
import java.util.List;

public class SwingCoinAcceptorDevice extends JPanel implements CoinAcceptorDevice {

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
    public int acceptCoin(int i) {
        return 0;
    }
}
