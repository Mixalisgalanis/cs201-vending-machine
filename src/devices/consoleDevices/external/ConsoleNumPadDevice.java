package devices.consoleDevices.external;

import devices.external.NumPadDevice;
import utilities.Reader;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {

    private Reader reader;

    public ConsoleNumPadDevice() {
        super("NumPad");
        reader = new Reader();
    }

    @Override
    public int readDigit() {
        int digit;
        digit = reader.readInt("Insert digit:");
        return digit;
    }


}
