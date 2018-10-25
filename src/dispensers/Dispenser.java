package dispensers;

import containers.Container;
import interfaces.Consumer;
import interfaces.Provider;

public interface Dispenser {

    Provider prepareContainer(String containerName, Consumer consumer);

    void addContainer(Container container);

    Container removeContainer(String containerName);

    int getCurrentQuantity(String containerName);
}
