package devices.consoleDevices.external;


import devices.consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.LockableDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

public class ConsoleLockableExternalDevice extends ConsoleDevice implements LockableDevice {

    private Boolean locked;

    public ConsoleLockableExternalDevice(String name, DeviceType deviceType) {
        super(name, deviceType);
        this.locked = true;
    }


    @Override
    public void lock() {
        this.locked = true;
        System.out.println(getName() + " is locked.");
    }

    @Override
    public void unLock() {
        this.locked = false;
        System.out.println(getName() + " is unlocked.");

    }

    @Override
    public boolean isLocked() {
        return locked;
    }
}
