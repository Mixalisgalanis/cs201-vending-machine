package devices.consoleDevices.internal;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;


public class ConsoleProcessorDevice extends ConsoleFlowContainerDevice implements ProcessorDevice {

    public ConsoleProcessorDevice(String name, int capacity) {
        super(name, DeviceType.Processor, capacity);
    }

    @Override
    public void streamIn() {
        System.out.println("Consumable streamed in " + getName().toUpperCase() + ".");
    }

    @Override
    public void operateStart() {
        System.out.println(getName().toUpperCase() + " started " + getProcessingLabel() + ". Please wait...");
    }

    @Override
    public void operateStop() {
        System.out.println(getName().toUpperCase() + " stopped " + getProcessingLabel() + ". Please wait...");
    }

    @Override
    public String getProcessingLabel() {
        return getName().toLowerCase().substring(0, getName().length() - 8) + "ing";
    }
}
