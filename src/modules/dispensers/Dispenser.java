package modules.dispensers;

import modules.containers.Container;
import behaviour.Consumer;
import behaviour.Pluggable;
import behaviour.Provider;

public interface Dispenser extends Pluggable{

    Provider prepareContainer(String containerName, Consumer consumer);

    void addContainer(Container container);

    void removeContainer(String containerName);

    int getCurrentQuantity(String containerName);
}
