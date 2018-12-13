package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;
import utilities.Reader;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {

    private final Reader reader;

    public ConsoleNumPadDevice() {
        super("NumPadDevice", DeviceType.NumPad);
        reader = new Reader();
    }

    @Override
    public int readDigit(String s) {
        int digit = -1;
        while (digit <= 0 || digit >= 9) {
            digit = Reader.readInt(s);
            if (digit <= 0 || digit >= 9) {
                System.out.println("Invalid " + getName().toUpperCase() + " input! Please try again: ");
            }
        }
        return digit;
    }
}
