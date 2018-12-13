package machine.console;

import devices.consoleDevices.external.*;
import devices.consoleDevices.internal.*;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsoleMachine implements HardwareMachine {
    //Container Sizes
    private static final int POWDER_CONTAINER_REGULAR_SIZE = 500;
    private static final int LIQUID_CONTAINER_REGULAR_SIZE = 1000;
    private static final int SMALL_CUP_CONTAINER = 25;
    private static final int BIG_CUP_CONTAINER = 15;
    private static final int PROCESSOR_CONTAINER_SIZE = 500;

    //Class variables
    private final HashMap<String, Device> devices;
    private static ConsoleMachine instance;

    //Constructor
    private ConsoleMachine() {
        devices = new HashMap<>();
        instance = this;
        insertDevices();
    }

    //Methods

    /**
     * Inserts all required Physical Console Devices
     */
    private void insertDevices() {
        //Dispensers
        DispenserDevice dosingDispenserDevice = new ConsoleDispenserDevice("POWDERS", DeviceType.DosingDispenser);
        DispenserDevice flowDispenserDevice = new ConsoleDispenserDevice("LIQUIDS", DeviceType.FlowDispenser);
        DispenserDevice materialDispenserDevice = new ConsoleDispenserDevice("CUPS", DeviceType.MaterialDispenser);

        //Containers
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("CoffeeContainerDevice", POWDER_CONTAINER_REGULAR_SIZE));
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("SugarContainerDevice", POWDER_CONTAINER_REGULAR_SIZE));

        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("WaterContainerDevice", LIQUID_CONTAINER_REGULAR_SIZE));
        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("MilkContainerDevice", LIQUID_CONTAINER_REGULAR_SIZE));

        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("SmallCupContainerDevice", SMALL_CUP_CONTAINER));
        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("BigCupContainerDevice", BIG_CUP_CONTAINER));

        //Processors
        ProcessorDevice boiler = (new ConsoleProcessorDevice("BoilerDevice", PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice cooler = (new ConsoleProcessorDevice("CoolerDevice", PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice blender = (new ConsoleProcessorDevice("BlenderDevice", PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice buffer = (new ConsoleProcessorDevice("BufferDevice", PROCESSOR_CONTAINER_SIZE));

        //External
        NumPadDevice numPadDevice = new ConsoleNumPadDevice();
        CoinAcceptorDevice coinAcceptorDevice = new ConsoleCoinAcceptorDevice();
        DisplayDevice displayDevice = new ConsoleDisplayDevice();
        ChangeCaseDevice changeCaseDevice = new ConsoleChangeCaseDevice();
        ProductCaseDevice productCaseDevice = new ConsoleProductCaseDevice();


        //Adding all these Devices
        //Dispensers
        addDevice(dosingDispenserDevice);
        addDevice(materialDispenserDevice);
        addDevice(flowDispenserDevice);
        //Processors
        addDevice(boiler);
        addDevice(cooler);
        addDevice(blender);
        addDevice(buffer);
        //External Devices
        addDevice(numPadDevice);
        addDevice(coinAcceptorDevice);
        addDevice(displayDevice);
        addDevice(changeCaseDevice);
        addDevice(productCaseDevice);
    }


    @Override
    public List<Device> listDevices() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public String getModel() {
        return null;
    }

    public static ConsoleMachine getInstance() {
        return (instance != null) ? instance : new ConsoleMachine();
    }

    public void addDevice(Device device) {
        devices.put(device.getName(), device);
    }
}
