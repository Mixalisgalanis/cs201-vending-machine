package consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;
import utilities.Reader;

public class ConsoleNumPadDevice extends ConsoleLockableExternalDevice implements NumPadDevice {

    private Reader reader;

    public ConsoleNumPadDevice() {
        super("NumPad", DeviceType.NumPad);
        this.reader = new Reader();
    }

    @Override
    public int readDigit(String s) {        //s : current code
        int digit;
        digit = reader.readInt(s);
        return digit;
    }
}
