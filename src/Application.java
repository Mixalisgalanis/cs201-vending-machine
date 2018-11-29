import consoleDevices.internal.*;
import machine.ConsoleMachine;
import machine.SoftwareMachine;
import machine.SwingMachine;
import modules.containers.processor.IngredientProcessor;
import modules.containers.processor.Processor;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.Liquid;
import recipes.consumables.ingredients.Powder;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;
import tuc.ece.cs201.vm.hw.device.ProcessorDevice;

public class Application {
    //Class variables
    private static ConsoleMachine console;
    private static SwingMachine gui;
    private static RecipeManager rm;
    //Constants
   /* private static final String MAIN_MENU = "=======MAIN MENU=======\nTypes of Users:\n1. Administrator\n2. User\n=======================\nPlease select type: ";
    private static final String ADMIN_SUBMENU = "----Administrator Submenu----\nActions:\n1. Create Recipes\n2. Delete Recipes\n3. Refill Containers\nPlease select action: ";
    private static final String USER_SUBMENU = "----User Submenu----\nActions:\n1. Buy a drink\nPlease select actiion: ";
    private static final String RECIPES_HEADER = "----Available Recipes----\n";
    private static final String RECIPES_FOOTER = "Please select recipe code to execute: ";*/



    public static void main(String args[]) {
        console = new ConsoleMachine();
        gui = new SwingMachine();
        insertConsoleDevices();
        insertGuiDevices();

        rm = RecipeManager.getInstance();
        SoftwareMachine machine = SoftwareMachine.getInstance(console);
        startCycleOf(machine);
    }

    private static void insertConsoleDevices() {
        //Dispensers
        DispenserDevice dosingDispenserDevice = new ConsoleDispenserDevice("POWDERS", DeviceType.DosingDispenser);
        DispenserDevice flowDispenserDevice = new ConsoleDispenserDevice("LIQUIDS", DeviceType.FlowDispenser);
        DispenserDevice materialDispenserDevice = new ConsoleDispenserDevice("MATERIALS", DeviceType.MaterialDispenser);

        //Containers
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("CoffeeContainerDevice", console.POWDER_CONTAINER_REGULAR_SIZE));
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("SugarContainerDevice", console.POWDER_CONTAINER_REGULAR_SIZE));

        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("WaterContainerDevice", console.LIQUID_CONTAINER_REGULAR_SIZE));
        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("MilkContainerDevice", console.LIQUID_CONTAINER_REGULAR_SIZE));

        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("SmallCupContainerDevice", console.CUP_CONTAINER_REGULAR_SIZE));
        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("BigCupContainerDevice", console.CUP_CONTAINER_REGULAR_SIZE));

        //Processors
        ProcessorDevice boiler = (new ConsoleProcessorDevice("BoilerDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice cooler = (new ConsoleProcessorDevice("CoolerDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice blender = (new ConsoleProcessorDevice("BlenderDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice buffer = (new ConsoleProcessorDevice("BufferDevice", console.PROCESSOR_CONTAINER_SIZE));

        //Consumables
        Powder coffe = new Powder("Coffee",console.POWDER_CONTAINER_REGULAR_SIZE);
        Powder chocolate = new Powder("Chocolate",console.POWDER_CONTAINER_REGULAR_SIZE);
        Powder sugar = new Powder("Sugar",console.POWDER_CONTAINER_REGULAR_SIZE);

        Liquid water = new Liquid("Water",console.LIQUID_CONTAINER_REGULAR_SIZE);
        Liquid milk = new Liquid("Milk",console.LIQUID_CONTAINER_REGULAR_SIZE);

        Cup regularCup = new Cup("Cup", console.CUP_CONTAINER_REGULAR_SIZE,"Normal");

        //Inserting Consumables into Containers


        //Adding all these Devices
        console.addDevice(dosingDispenserDevice);
        console.addDevice(materialDispenserDevice);
        console.addDevice(flowDispenserDevice);

        console.addDevice(boiler);
        console.addDevice(cooler);
        console.addDevice(blender);
        console.addDevice(buffer);



    }

    private static void insertGuiDevices() {
        //Same as insertConsoleDevices()
    }

    private static void startCycleOf(SoftwareMachine machine) {
        rm.loadRecipes();
        rm.validateRecipes();
        rm.loadEnabledRecipes();
        DisplayPanel display = (DisplayPanel) machine.getModule("DisplayPanel");
        NumPad numPad = (NumPad) machine.getModule("NumPad");
        CoinReader coinReader = (CoinReader) machine.getModule("CoinReader");
        ChangeCase changeCase = (ChangeCase) machine.getModule("ChangeCase");
        ProductCase productCase = (ProductCase) machine.getModule("ProductCase");

        display.displayMessage("Welcome to Vending Machine v1.0 (alpha)");
        display.displayMessage(MAIN_MENU);
        switch (String.valueOf(numPad.readCode(1))) {
            case "1":
                display.displayMessage(ADMIN_SUBMENU);
                switch (String.valueOf(numPad.readCode(1))) {
                    case "1":
                        rm.createRecipe();
                        break;
                    case "2": {
                        display.displayMessage(RECIPES_HEADER);
                        for (Recipe recipe : rm.getRecipes().values()) {
                            display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + ")");
                        }
                        display.displayMessage("Enter recipe code to delete: ");
                        int recipeCode = numPad.readCode(3);
                        rm.removeRecipe(String.valueOf(recipeCode));
                        break;
                    }
                    case "3":
                        machine.refillContainers();
                        break;
                }
                break;
            case "2":
                display.displayMessage(USER_SUBMENU);
                switch (String.valueOf(numPad.readCode(1))) {
                    case "1": {
                        display.displayMessage(RECIPES_HEADER);
                        for (Recipe recipe : rm.getAvailableRecipes().values()) {
                            display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + ")");
                        }
                        Recipe recipe;
                        do {
                            display.displayMessage(RECIPES_FOOTER);
                            int recipeCode = numPad.readCode(3);
                            recipe = rm.getRecipe(String.valueOf(recipeCode));
                            if (recipe == null) display.displayMessage("Recipe not Found!");
                        } while (recipe == null);
                        int change = coinReader.receiveMoney(recipe.getPrice());
                        coinReader.clearMoney();
                        changeCase.setChange(change);
                        changeCase.removeChange();
                        rm.executeRecipe(recipe);
                        productCase.prepareProduct(recipe);
                    }
                    break;
                }
                break;
        }

    }
}
