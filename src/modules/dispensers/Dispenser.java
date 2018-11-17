package modules.dispensers;

import behaviour.Consumer;
import behaviour.Pluggable;
import behaviour.Provider;
import modules.containers.Container;

public interface Dispenser extends Pluggable {

    /**
     * Plugs a specific container to a consumer and returns the Provider
     *
     * @param containerName The container name required to find the specific container
     * @param consumer      The consumer to be plugged from the container
     * @return the Provider (Container itself)
     */
    Provider prepareContainer(String containerName, Consumer consumer);

    /**
     * Adds a container in the containers HashMap only if contains the same consumable type with the rest of them
     *
     * @param container to be added in the HashMap
     */
    void addContainer(Container container);

    /**
     * Removes a container from the containers HashMap based on a container name
     *
     * @param containerName The container name required to find the specific container
     */
    void removeContainer(String containerName);

    /**
     * Finds a container within the containers HashMap based on a name and returns it's consumable quantity
     *
     * @param containerName The container name required to find the specific container
     * @return container->consumable->quantity
     */
    int getCurrentQuantity(String containerName);
}
