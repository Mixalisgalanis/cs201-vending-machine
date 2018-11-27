package modules;

import modules.containers.DosingContainer;
import modules.containers.FlowContainer;
import modules.containers.MaterialContainer;
import modules.containers.processor.IngredientProcessor;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import tuc.ece.cs201.vm.hw.device.*;

public class ModuleFactory {

    public static Module createModule(Device device) {

        switch (device.getType()) {
            case FlowContainer:
                return new FlowContainer(, , , (FlowContainerDevice) device);
            case DosingContainer:
                return new DosingContainer(, , , (DosingContainerDevice) device);
            case MaterialContainer:
                return new MaterialContainer(, , , (MaterialContainerDevice) device);
            case Processor:
                return new IngredientProcessor(, , , (ProcessorDevice) device);
            case FlowDispenser:
                ConsumableDispenser dispenser = ConsumableDispenser(, , (DispenserDevice) device);
                for (Device containerDevice : device.listConnectedDevices()) {
                    dispenser.addContainer((FlowContainer) createModule(containerDevice));
                }
                return dispenser;
            case DosingDispenser:
                dispenser = ConsumableDispenser(, , (DispenserDevice) device);
                for (Device containerDevice : device.listConnectedDevices()) {
                    dispenser.addContainer((DosingContainer) createModule(containerDevice));
                }
                return dispenser;
            case MaterialDispenser:
                dispenser = ConsumableDispenser(, , (DispenserDevice) device);
                for (Device containerDevice : device.listConnectedDevices()) {
                    dispenser.addContainer((MaterialContainer) createModule(containerDevice));
                }
                return dispenser;
            case ProductCase:
                return new ProductCase((ProductCaseDevice) device);
            case Display:
                return new DisplayPanel((DisplayDevice) device);
            case NumPad:
                return new NumPad((NumPadDevice) device);
            case CoinReader:
                return new CoinReader((CoinAcceptorDevice) device);
            case ChangeCase:
                return new ChangeCase((ChangeCaseDevice) device);
        }
        return null;
    }
}
