package modules.dispensers;

import behaviour.Consumer;
import behaviour.Pluggable;
import behaviour.Provider;
import modules.containers.Container;

public interface Dispenser extends Pluggable {

    Provider prepareContainer(String containerName, Consumer consumer);

    void addContainer(Container container);

    void removeContainer(String containerName);

    int getCurrentQuantity(String containerName);
}
