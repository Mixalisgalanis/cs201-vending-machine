package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

public class ConsoleProductCaseDevice extends ConsoleLockableExternalDevice implements ProductCaseDevice {

    public ConsoleProductCaseDevice() {
        super("ProductCaseDevice", DeviceType.ProductCase);
    }


    @Override
    public void putMaterial(String type) {
        System.out.println(type + " inserted.");
    }

    @Override
    public void loadIngredient(String consumableName) {
        System.out.println("[" + consumableName + "] streamed in " + getName().toUpperCase());
    }
}
