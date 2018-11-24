package devices.dispensers;

import devices.Device;
import devices.containers.ContainerDevice;

import java.util.ArrayList;

public interface DispenserDevice<T extends ContainerDevice> extends Device {

    ArrayList<T> listContainers();

    void prepareContainer(T containerDevice);

    void addContainer(T containerDevice);

    void removeContainer(String name);
}
