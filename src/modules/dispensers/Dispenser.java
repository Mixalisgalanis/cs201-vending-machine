package modules.dispensers;

import modules.containers.Container;
import interfaces.Consumer;
import interfaces.Pluggable;
import interfaces.Provider;

public interface Dispenser extends Pluggable{

    Provider prepareContainer(String containerName, Consumer consumer);

    void addContainer(Container container);

    Container removeContainer(String containerName);

    int getCurrentQuantity(String containerName);
}
