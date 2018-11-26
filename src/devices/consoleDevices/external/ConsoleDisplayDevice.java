package devices.consoleDevices.external;

import devices.consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DisplayDevice;

public class ConsoleDisplayDevice extends ConsoleDevice implements DisplayDevice {


    public ConsoleDisplayDevice() {
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
