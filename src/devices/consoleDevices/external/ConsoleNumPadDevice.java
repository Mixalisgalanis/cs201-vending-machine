package devices.consoleDevices.external;

import devices.external.NumPadDevice;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {

    @Override
    public int readDigit() {
        return 0;
    }

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
