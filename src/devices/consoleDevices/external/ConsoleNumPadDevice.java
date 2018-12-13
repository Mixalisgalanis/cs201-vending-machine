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
<<<<<<< HEAD:src/consoleDevices/external/ConsoleNumPadDevice.java
    public int readDigit(String s) {        //s : current code
        int digit;
        digit = reader.readInt(s);
        return digit;
=======
    public int readDigit(String s) {
        return Reader.readInt(s);
>>>>>>> remodel:src/devices/consoleDevices/external/ConsoleNumPadDevice.java
    }
}
