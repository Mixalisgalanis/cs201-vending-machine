package devices.consoleDevices.external;

import devices.external.NumPadDevice;
import utilities.Reader;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {


    public ConsoleNumPadDevice() {
        super("NumPad");
    }

    @Override
    public int readDigit() { return 0;}


}
