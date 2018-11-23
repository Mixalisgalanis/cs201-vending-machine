package devices.consoleDevices.internal;

import devices.containers.processors.ProcessorDevice;

public class ConsoleProcessorDevice extends ConsoleFlowContainerDevice implements ProcessorDevice {

    public ConsoleProcessorDevice(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void streamln() {
        System.out.println(getName() + " streamed in.");
    }

    @Override
    public void operateStart() {
        System.out.println(getName() + " started processing the ingredients.");
    }

    @Override
    public void operateStop() {
        System.out.println(getName() + " stopped processing the ingredients.");
    }

    @Override
    public String getProcessingLabel() {
        switch(getName()){
            case "boiler": return "boiled";
            case "blender": return "blended";
            case "cooler": return "cooled";
            case "buffer": return "buffed";
            default: return "not found";
        }
    }
}
