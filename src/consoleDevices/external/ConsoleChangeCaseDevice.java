package consoleDevices.external;


import tuc.ece.cs201.vm.hw.device.ChangeCaseDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

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
        System.out.println(coin + "\t");
    }

    @Override
    public void removeChange() {
        System.out.println("Removed change");
        this.change = 0;
    }
}