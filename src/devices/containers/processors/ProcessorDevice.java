package devices.containers.processors;

import devices.containers.FlowContainerDevice;

public interface ProcessorDevice extends FlowContainerDevice {

    void streamln();

    void operateStart();

    void operateStop();

    String getProcessingLabel();
}
