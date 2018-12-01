package consoleDevices.external;


import tuc.ece.cs201.vm.hw.device.ChangeCaseDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

public class ConsoleChangeCaseDevice extends ConsoleLockableExternalDevice implements ChangeCaseDevice {

    public ConsoleChangeCaseDevice() {
        super("ChangeCase", DeviceType.ChangeCase);
    }

    @Override
    public void giveChange(int coin) {
        System.out.println("Exported " + coin + "c.");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeChange() {
        System.out.println("Assuming change is taken. Clearing change. . .");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Change Cleared!");
    }
}
