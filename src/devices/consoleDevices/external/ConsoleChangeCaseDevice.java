package devices.consoleDevices.external;

import devices.DeviceType;
import devices.external.ChangeCaseDevice;

public class ConsoleChangeCaseDevice extends ConsoleLockableExternalDevice implements ChangeCaseDevice {

    private int change;

    public ConsoleChangeCaseDevice() {
        super("ChangeCase", DeviceType.ChangeCase);
        this.change = 0;
    }


    public void setChange(int change) {
        this.change = change;
    }

    @Override
    public void giveChange(int coin) {
        System.out.println(coin);
    }

    @Override
    public void removeChange() {
        System.out.println("Removed change");
        this.change = 0;
    }
}
