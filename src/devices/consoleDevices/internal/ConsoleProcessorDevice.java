package devices.consoleDevices.internal;

import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;


public class ConsoleProcessorDevice extends ConsoleFlowContainerDevice implements ProcessorDevice {

    public ConsoleProcessorDevice(String name, int capacity) {
        super(name, DeviceType.Processor, capacity);
    }

    @Override
    public void streamIn() {
        System.out.println("[" + getName() + "] - " + "Consumable streamed in.");
    }

    @Override
    public void operateStart() {
        System.out.println("[" + getName() + "] - " + "Started operating.");
    }

    @Override
    public void operateStop() {
        System.out.println("[" + getName() + "] - " + "Stopped operating.");
    }

    @Override
    public String getProcessingLabel() {
        return getName().substring(0, getName().length() - 7) + "d";
    }
}