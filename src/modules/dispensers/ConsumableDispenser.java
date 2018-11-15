package modules.dispensers;

import behaviour.Consumer;
import behaviour.Provider;
import modules.Module;
import modules.containers.Container;

import java.util.HashMap;

public class ConsumableDispenser extends Module implements Dispenser {

    private HashMap<String, Container> containers;

    public ConsumableDispenser(String name) {
        super(name);
        this.containers = containers;
    }

    @Override
    public Provider prepareContainer(String containerName, Consumer consumer) {
        return null;
    }

    @Override
    public void addContainer(Container container) {

    }

    @Override
    public void removeContainer(String containerName) {
        containers.remove(containerName);
    }

    @Override
    public int getCurrentQuantity(String containerName) {
        return containers.get(containerName).getConsumable().getQuantity();
    }

    @Override
    public void plug(Consumer consumer) {

    }

    @Override
    public void unPlug(Consumer consumer) {

    }

    @Override
    public void unPlugAll() {

    }

    @Override
    public boolean isPlugged() {
        return false;
    }
}
