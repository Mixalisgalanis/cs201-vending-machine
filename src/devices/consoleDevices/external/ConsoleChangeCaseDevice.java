package devices.consoleDevices.external;

import devices.external.ChangeCaseDevice;

public class ConsoleChangeCaseDevice extends ConsoleLockableExternalDevice implements ChangeCaseDevice {

    private int change;

    public ConsoleChangeCaseDevice() {
        super("ChangeCase");
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
        this.change = 0;
    }
}
