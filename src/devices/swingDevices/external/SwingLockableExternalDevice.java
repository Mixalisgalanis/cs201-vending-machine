package devices.swingDevices.external;

import devices.swingDevices.SwingDevice;
import tuc.ece.cs201.vm.hw.device.LockableDevice;

public class SwingLockableExternalDevice extends SwingDevice implements LockableDevice {

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
}
