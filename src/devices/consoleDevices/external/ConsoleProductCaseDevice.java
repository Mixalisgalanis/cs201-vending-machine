package devices.consoleDevices.external;

import devices.external.ProductCaseDevice;

public class ConsoleProductCaseDevice extends ConsoleLockableExternalDevice implements ProductCaseDevice {

    public ConsoleProductCaseDevice() {
        super("ProductCase");
    }

    @Override
    public void getProduct() {
        System.out.println("Your drink is ready");
    }

    @Override
    public void putMaterial(String type) {
        System.out.println("Inserted: " + type);
        //TODO insert this function in product builder
    }

    @Override
    public void loadIngredient(String type) {
        System.out.println("Loading: "+ type);
    }
}
