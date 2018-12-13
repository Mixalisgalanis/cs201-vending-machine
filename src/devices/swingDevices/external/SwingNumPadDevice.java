package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.NumPadDevice;

public class SwingNumPadDevice extends SwingLockableExternalDevice implements NumPadDevice {

    @Override
    public int readDigit(String s) {
        return 0;
    }
}
