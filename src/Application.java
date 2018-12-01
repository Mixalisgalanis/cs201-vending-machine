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

import java.util.HashMap;

public class Application {

    private static final boolean GUI_ENABLED = false; //Change this to switch between Graphical & Console Implementation
    //Class variables
    private static ConsoleMachine console;
    private static SwingMachine gui;
    private static SoftwareMachine machine;
    private static RecipeManager rm;

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
        DisplayPanel display = (DisplayPanel) machine.getModule(DisplayPanel.class.getSimpleName());
        NumPad numPad = (NumPad) machine.getModule(NumPad.class.getSimpleName());
        CoinReader coinReader = (CoinReader) machine.getModule(CoinReader.class.getSimpleName());
        ChangeCase changeCase = (ChangeCase) machine.getModule(ChangeCase.class.getSimpleName());
        ProductCase productCase = (ProductCase) machine.getModule(ProductCase.class.getSimpleName());

        //Display Welcome Message and Main Menu
        display.displayMessage("Welcome to Vending Machine v1.0 (alpha)");


        //NEW IMPLEMENTATION OF MENU
        new Menu();
        int EXIT_SELECTION = -1;
        int selection = 0;
        while (selection != EXIT_SELECTION) {
            display.displayMessage(Menu.getMenu());
            switch (Menu.calculateActionCode(selection)) {
                case "100": //MAIN MENU
                    selection = numPad.readCode(1);
                    break;

                case "110": //ADMIN MAIN MENU
                    selection = numPad.readCode(1);
                    break;

                case "120": //USER MAIN MENU
                    selection = numPad.readCode(1);
                    break;

                case "111": //Create Recipes
                    rm.createRecipe();
                    selection = EXIT_SELECTION;
                    break;

                case "112": //Delete Recipes
                    //Display Recipes Header
                    display.displayMessage("----Available Recipes----\n");
                    for (Recipe recipe : rm.getRecipes().values()) {
                        display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + ")");
                    }
                    //Display Recipes Footer
                    display.displayMessage("Enter recipe code to delete: ");
                    int recipeCode = numPad.readCode(3);
                    rm.removeRecipe(String.valueOf(recipeCode));
                    selection = EXIT_SELECTION;
                    break;

                case "121": //Buy a Drink
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

                        recipeCode = numPad.readCode(3);
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
                    selection = EXIT_SELECTION;
                    break;
                default: //Code not recognised
                    display.displayMessage("Action not found!");
            }
        }
    }


    private static class Menu {

        //Class variables
        static final String INITIAL_CODE = "100";
        static HashMap<String, String> actionCodes = new HashMap<>();
        //Current state
        static String currentActionCode = INITIAL_CODE;

        //Constructor
        Menu() {
            insertActionCodes();
        }

        //Getter
        static String getMenu() {
            return actionCodes.get(currentActionCode);
        }

        //Other Methods
        static void insertActionCodes() {
            actionCodes.put("100", "=======MAIN MENU=======\nTypes of Users:\n1. Administrator\n2. " +
                    "User\n=======================\nPlease select type: "); //MAIN MENU
            actionCodes.put("110", "----Administrator Submenu----\nActions:\n1. Create Recipes\n2. Delete Recipes\n3." +
                    " Refill Containers\nPlease select action: "); //ADMIN MAIN MENU
            actionCodes.put("120", "----User Submenu----\nActions:\n" +
                    "1. Buy a drink\nPlease select action: "); //USER MAIN MENU
            actionCodes.put("111", "You have chosen to Create a Recipe!"); //Create Recipe (Admin)
            actionCodes.put("112", "You have chosen to Delete a Recipe!"); //Delete Recipe (Admin)
            actionCodes.put("121", "You have chosen to Buy a Drink!"); //Buy a Drink (User)
        }

        static String calculateActionCode(int selection) {
            String prefix = currentActionCode.substring(0, currentActionCode.indexOf('0'));
            return prefix + selection + currentActionCode.substring(currentActionCode.indexOf('0') + 1);
        }
    }
}
