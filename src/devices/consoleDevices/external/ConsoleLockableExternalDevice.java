package devices.consoleDevices.external;


import devices.consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.LockableDevice;

public class ConsoleLockableExternalDevice extends ConsoleDevice implements LockableDevice {

    private Boolean locked;

    public ConsoleLockableExternalDevice(String name, DeviceType deviceType) {
        super(name, deviceType);
        locked = true;
    }


    @Override
    public void lock() {
        locked = true;
        System.out.println("[" + getName().toUpperCase() + " locked further access.]");
    }

    @Override
    public void unLock() {
        locked = false;
        System.out.println("[" + getName().toUpperCase() + " unlocked access.]");

    }

    @Override
    public boolean isLocked() {
        return locked;
    }
}
