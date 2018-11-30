import consoleDevices.external.*;
import consoleDevices.internal.*;
import machine.ConsoleMachine;
import machine.SoftwareMachine;
import machine.SwingMachine;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.Liquid;
import recipes.consumables.ingredients.Powder;
import tuc.ece.cs201.vm.hw.device.*;

public class Application {

    //Class variables
    private static ConsoleMachine console;
    private static SwingMachine gui;
    private static SoftwareMachine machine;
    private static RecipeManager rm;
    private static final boolean GUI_ENABLED = false; //Change this to switch between Graphical & Console Implementation

    //Constants
    public static void main(String[] args) {
        if (GUI_ENABLED) {
            gui = new SwingMachine();
            insertGuiDevices();
        } else {
            console = new ConsoleMachine();
            insertConsoleDevices();
        }

        rm = RecipeManager.getInstance();
        machine = SoftwareMachine.getInstance((GUI_ENABLED) ? gui : console);
        insertConsumables();
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

        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("WaterContainerDevice", DeviceType.FlowContainer, console.LIQUID_CONTAINER_REGULAR_SIZE));
        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("MilkContainerDevice", DeviceType.FlowContainer, console.LIQUID_CONTAINER_REGULAR_SIZE));

        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("SmallCupContainerDevice", console.CUP_CONTAINER_REGULAR_SIZE));
        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("BigCupContainerDevice", console.CUP_CONTAINER_REGULAR_SIZE));

        //Processors
        ProcessorDevice boiler = (new ConsoleProcessorDevice("BoilerDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice cooler = (new ConsoleProcessorDevice("CoolerDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice blender = (new ConsoleProcessorDevice("BlenderDevice", console.PROCESSOR_CONTAINER_SIZE));
        ProcessorDevice buffer = (new ConsoleProcessorDevice("BufferDevice", console.PROCESSOR_CONTAINER_SIZE));

        //External
        NumPadDevice numPadDevice = new ConsoleNumPadDevice();
        CoinAcceptorDevice coinAcceptorDevice = new ConsoleCoinAcceptorDevice();
        DisplayDevice displayDevice = new ConsoleDisplayDevice();
        ChangeCaseDevice changeCaseDevice = new ConsoleChangeCaseDevice();
        ProductCaseDevice productCaseDevice = new ConsoleProductCaseDevice();


        //Adding all these Devices
        console.addDevice(dosingDispenserDevice);
        console.addDevice(materialDispenserDevice);
        console.addDevice(flowDispenserDevice);

        console.addDevice(boiler);
        console.addDevice(cooler);
        console.addDevice(blender);
        console.addDevice(buffer);

        console.addDevice(numPadDevice);
        console.addDevice(coinAcceptorDevice);
        console.addDevice(displayDevice);
        console.addDevice(changeCaseDevice);
        console.addDevice(productCaseDevice);

    }

    private static void insertGuiDevices() {
        //Same as insertConsoleDevices()
    }

    private static void insertConsumables() {
        //Powders
        machine.addConsumable(new Powder("Coffee", console.POWDER_CONTAINER_REGULAR_SIZE));
        machine.addConsumable(new Powder("Chocolate", console.POWDER_CONTAINER_REGULAR_SIZE));
        machine.addConsumable(new Powder("Sugar", console.POWDER_CONTAINER_REGULAR_SIZE));

        //Liquids
        machine.addConsumable(new Liquid("Water", console.LIQUID_CONTAINER_REGULAR_SIZE));
        machine.addConsumable(new Liquid("Milk", console.LIQUID_CONTAINER_REGULAR_SIZE));

        //Materials
        machine.addConsumable(new Cup("Cup", console.CUP_CONTAINER_REGULAR_SIZE, "Normal"));
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

        //Display Welcome Message and Main Menu
        display.displayMessage("Welcome to Vending Machine v1.0 (alpha)");
        display.displayMessage("=======MAIN MENU=======\nTypes of Users:\n1. Administrator\n2. User\n=======================\nPlease select type: ");
        switch (String.valueOf(numPad.readCode(1))) {
            case "1":
                //Display Admin Submenu
                display.displayMessage("----Administrator Submenu----\nActions:\n1. Create Recipes\n2. Delete Recipes\n3. Refill Containers\nPlease select action: ");
                switch (String.valueOf(numPad.readCode(1))) {
                    case "1":
                        rm.createRecipe();
                        break;
                    case "2": {
                        //Display Recipes Header
                        display.displayMessage("----Available Recipes----\n");
                        for (Recipe recipe : rm.getRecipes().values()) {
                            display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + ")");
                        }
                        //Display Recipes Footer
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
                //Display User Sub Menu
                display.displayMessage("----User Submenu----\nActions:\n1. Buy a drink\nPlease select actiion: ");
                switch (String.valueOf(numPad.readCode(1))) {
                    case "1": {
                        //Display Recipes Header
                        display.displayMessage("----Available Recipes----\n");
                        for (Recipe recipe : rm.getAvailableRecipes().values()) {
                            display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + ")");
                        }
                        Recipe recipe;
                        //Select Recipe
                        do {
                            //Display Recipes Footer
                            display.displayMessage("Please select recipe code to execute: ");

                            int recipeCode = numPad.readCode(3);
                            recipe = rm.getRecipe(String.valueOf(recipeCode));
                            if (recipe == null) {
                                display.displayMessage("Recipe not Found!");
                            }
                        } while (recipe == null);
                        //Receive Money and check if there is any change to return
                        int change = coinReader.receiveMoney(recipe.getPrice());
                        coinReader.clearMoney();
                        changeCase.setChange(change);
                        changeCase.removeChange();
                        //Execute Recipe
                        rm.executeRecipe(recipe);
                        productCase.prepareProduct(recipe);
                    }
                    break;
                }
                break;
        }

    }
}
