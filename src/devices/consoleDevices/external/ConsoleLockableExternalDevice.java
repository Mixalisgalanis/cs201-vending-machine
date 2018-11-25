package devices.consoleDevices.external;

import behaviour.Lockable;
import devices.DeviceType;
import devices.consoleDevices.ConsoleDevice;

public class ConsoleLockableExternalDevice extends ConsoleDevice implements Lockable {

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
