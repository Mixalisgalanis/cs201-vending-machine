package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.ProductCaseDevice;

public class ConsoleProductCaseDevice extends ConsoleLockableExternalDevice implements ProductCaseDevice {

    public ConsoleProductCaseDevice() {
        super("ProductCase", DeviceType.ProductCase);
    }


    @Override
    public void putMaterial(String type) {
        System.out.println("Inserted: " + type);
        //TODO insert this function in product builder
    }

    @Override
    public void loadIngredient(String type) {
        System.out.println("Loading: " + type);
    }
}
