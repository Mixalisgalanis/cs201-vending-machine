package devices.consoleDevices.external;

import devices.DeviceType;
import devices.consoleDevices.ConsoleDevice;
import devices.external.DisplayDevice;

public class ConsoleDisplayDevice extends ConsoleDevice implements DisplayDevice {


    public ConsoleDisplayDevice(String name, DeviceType deviceType) {
        super("DisplayDevice", DeviceType.Display);
    }

    @Override
    public void displayMsg(String message) {
        System.out.println(message);
    }

    @Override
    public void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }
    }
}
