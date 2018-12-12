package modules.dispensers;

import behaviour.Consumer;
import behaviour.Provider;
import modules.Module;
import modules.containers.Container;
import recipes.consumables.Consumable;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;

import java.util.HashMap;

public class ConsumableDispenser extends Module<DispenserDevice> implements Dispenser {

    private final HashMap<String, Container> containers;
    //Class variables
    private boolean plugged;
    private String consumableType;

    //Constructors
    public ConsumableDispenser(String name, String consumableType, DispenserDevice device) {
        super(name, device);
        containers = new HashMap<>();
        plugged = false;
        this.consumableType = consumableType;
    }

    public ConsumableDispenser(DispenserDevice device) {
        super(device);
        setName(nameDecoder(device.getType()));
        setConsumableType(consumableTypeDecoder(device.getType()));
        containers = new HashMap<>();
        plugged = false;
    }

    //Getters & Setters
    public HashMap<String, Container> getContainers() {
        return containers;
    }

    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

    //Other Methods
    @Override
    public Provider prepareContainer(String containerName, Consumer consumer) {
        Container container = getContainer(containerName);
        if (container != null) {
            container.plug(consumer);
        }
        return container;
    }

    @Override
    public void addContainer(Container container) {
        if ((container.getType() == DeviceType.DosingContainer && getType() == DeviceType.DosingDispenser) ||
                (container.getType() == DeviceType.FlowContainer && getType() == DeviceType.FlowDispenser) ||
                (container.getType() == DeviceType.MaterialContainer && getType() == DeviceType.MaterialDispenser)) {
            if (containers.get(container.getName()) == null) {
                containers.put(container.getName(), container);
            } else {
                container.getConsumable().refillPart(containers.get(container.getName()).getCapacity(), container.getConsumable().getQuantity());
            }
        }
    }

    public Container getContainer(String name) {
        return containers.get(name);
    }

    public Container getContainer(Consumable consumable) {
        for (Container container : containers.values()) {
            if (container.consumableNameDecoder().equalsIgnoreCase(consumable.getName())) {
                return container;
            }
        }
        return null;
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
            getDevice().connect(((Module) consumer).getDevice());
            setPlugged(true);
            consumer.setPlugged(true);
        }
    }

    @Override
    public void unPlug(Consumer consumer) {
        if (isPlugged()) {
            getDevice().disconnect(((Module) consumer).getDevice());
            setPlugged(false);
            consumer.setPlugged(false);
        }
    }

    @Override
    public void unPlugAll() {
        getDevice().disconnectAll();
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

    private String nameDecoder(DeviceType deviceType) {
        switch (deviceType) {
            case DosingDispenser:
                return "POWDERS";
            case FlowDispenser:
                return "LIQUIDS";
            case MaterialDispenser:
                return "CUPS";
            default:
                return "?";
        }
    }

    private String consumableTypeDecoder(DeviceType deviceType) {
        switch (deviceType) {
            case DosingDispenser:
                return "Powder";
            case FlowDispenser:
                return "Liquid";
            case MaterialDispenser:
                return "Cup";
            default:
                return "?";
        }
    }

}
