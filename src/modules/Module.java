package modules;

import devices.Device;
import devices.DeviceType;

abstract public class Module<T extends Device> {

    //Class Variables
    private String name;
    private T device;

    //Constructor
    public Module(String name) {
        this.name = name;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getDevice() {
        return device;
    }

    //Other Methods
    public DeviceType getType() {
        return device.getType();
    }
}
