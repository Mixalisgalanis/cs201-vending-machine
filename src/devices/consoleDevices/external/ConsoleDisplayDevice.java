package devices.consoleDevices.external;

import devices.consoleDevices.ConsoleDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DisplayDevice;

public class ConsoleDisplayDevice extends ConsoleDevice implements DisplayDevice {
    //Constructor
    public ConsoleDisplayDevice() {
        super("DisplayDevice", DeviceType.Display);
    }

    //Implemented Methods
    @Override
    public void displayMsg(String message) {
        System.out.println(message);
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        /*for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }*/
    }
}
