package modules;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

abstract public class Module<T extends Device> {

    //Class Variables
    private String name;
    private T device;

    //Constructor
    public Module(T device) {
        this.device = device;
    }

    public Module(String name, T device) {
        this.name = name;
        this.device = device;
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

    public void setDevice(T device) {
        this.device = device;
    }

    public DeviceType getType() {
        return device.getType();
    }
}
