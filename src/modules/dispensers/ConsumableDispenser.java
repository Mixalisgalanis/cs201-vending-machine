package modules.dispensers;

import behaviour.Consumer;
import behaviour.Provider;
import devices.consoleDevices.internal.ConsoleContainerDevice;
import devices.dispensers.DispenserDevice;
import devices.external.DisplayDevice;
import modules.Module;
import modules.containers.Container;

import java.util.HashMap;

public class ConsumableDispenser extends Module<DispenserDevice> implements Dispenser {

    //Class variables
    private boolean plugged;
    private HashMap<String, Container> containers;
    private String consumableType;

    //Constructor
    public ConsumableDispenser(String name, String consumableType, DispenserDevice device) {
        super(name, device);
        this.containers = new HashMap<>();
        this.plugged = false;
        this.consumableType = consumableType;
    }

    //Getters
    public HashMap<String, Container> getContainers() {
        return containers;
    }

    public String getConsumableType() {
        return consumableType;
    }

    //Other Methods
    @Override
    public Provider prepareContainer(String containerName, Consumer consumer) {
        Container container = containers.get(containerName);
        if (container != null) {
            container.plug(consumer);
        }
        return container;
    }

    @Override
    public void addContainer(Container container) {
        if (nameDecoder(container).equalsIgnoreCase(getName())) {
            if (containers.get(container.getName()) == null) {
                containers.put(container.getName(), container);
            } else {
                container.getConsumable().refillPart(containers.get(container.getName()).getCapacity(), container.getConsumable().getQuantity());
            }
        }
    }

    @Override
    public void removeContainer(String containerName) {
        containers.remove(containerName);
        getDevice().removeContainer(containerName);
    }

    @Override
    public int getCurrentQuantity(String containerName) {
        return containers.get(containerName).getConsumable().getQuantity();
    }

    @Override
    public void plug(Consumer consumer) {
        if (!isPlugged()) {
            setPlugged(true);
            consumer.setPlugged(true);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            setPlugged(false);
            consumer.setPlugged(false);
        }
    }

    @Override
    public void unPlugAll() {
        //TODO figure out what we're supposed to do here!
    }

    @Override
    public boolean isPlugged() {
        return plugged;
    }

    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    private String nameDecoder(Container container) {
        switch (container.getConsumable().getConsumableType()) {
            case "Powder":
                return "Powders";
            case "Liquid":
                return "Liquids";
            default:
                return container.getConsumable().getConsumableType();
        }
    }

}
