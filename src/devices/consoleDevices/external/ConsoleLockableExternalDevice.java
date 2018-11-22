package devices.consoleDevices.external;

import behaviour.Lockable;
import devices.consoleDevices.ConsoleDevice;

public class ConsoleLockableExternalDevice extends ConsoleDevice implements Lockable {

    private String name;
    private Boolean locked;

    public ConsoleLockableExternalDevice(String name) {
        this.name = name;
        this.locked = true;
    }

    @Override
    public void lock() {
        this.locked = true;
        System.out.println(this.name + " is locked.");
    }

    @Override
    public void unLock() {
        this.locked = false;
        System.out.println(this.name + " is unlocked.");

    }

    @Override
    public boolean isLocked() {
        return locked;
    }
}
