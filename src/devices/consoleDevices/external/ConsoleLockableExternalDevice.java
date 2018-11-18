package devices.consoleDevices.external;

import behaviour.Lockable;
import devices.consoleDevices.ConsoleDevice;

public class ConsoleLockableExternalDevice extends ConsoleDevice implements Lockable {

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
