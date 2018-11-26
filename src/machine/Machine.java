package machine;

import tuc.ece.cs201.vm.hw.device.Device;

public interface Machine {
    Device[] listDevices();

    void addDevice(Device device);

    String getModel();
}
