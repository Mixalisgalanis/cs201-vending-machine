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
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.*;

import java.util.HashMap;

public class Application {

    private static final boolean GUI_ENABLED = false; //Change this to switch between Graphical & Console Implementation
    //Class variables
    private static HardwareMachine machine;
    private static SoftwareMachine sm;
    private static RecipeManager rm;

    //Constants
    public static void main(String[] args) {
        //Machine Preparation
        machine = (GUI_ENABLED) ? new SwingMachine() : new ConsoleMachine();
        insertDevices();
        sm = SoftwareMachine.getInstance(machine);
        rm = RecipeManager.getInstance();
        insertConsumables();

        //Start Cycle
        startCycleOf(sm);
    }

    private static void insertDevices() {
        if (GUI_ENABLED) {
            insertGuiDevices();
        } else {
            insertConsoleDevices();
        }
    }

    private static void insertConsoleDevices() {
        ConsoleMachine console = (ConsoleMachine) machine; //Machine is console

        //Dispensers
        DispenserDevice dosingDispenserDevice = new ConsoleDispenserDevice("POWDERS", DeviceType.DosingDispenser);
        DispenserDevice flowDispenserDevice = new ConsoleDispenserDevice("LIQUIDS", DeviceType.FlowDispenser);
        DispenserDevice materialDispenserDevice = new ConsoleDispenserDevice("MATERIALS", DeviceType.MaterialDispenser);

        //Containers
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("CoffeeContainerDevice", console.POWDER_CONTAINER_REGULAR_SIZE));
        dosingDispenserDevice.addContainer(new ConsoleDosingContainerDevice("SugarContainerDevice", console.POWDER_CONTAINER_REGULAR_SIZE));

        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("WaterContainerDevice", console.LIQUID_CONTAINER_REGULAR_SIZE));
        flowDispenserDevice.addContainer(new ConsoleFlowContainerDevice("MilkContainerDevice", console.LIQUID_CONTAINER_REGULAR_SIZE));

        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("SmallCupContainerDevice", console.SMALL_CUP_CONTAINER));
        materialDispenserDevice.addContainer(new ConsoleMaterialContainerDevice("BigCupContainerDevice", console.BIG_CUP_CONTAINER));

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

    private static void insertConsumables() {
        ConsoleMachine console = (ConsoleMachine) machine; //Machine is console

        //Powders
        sm.addConsumable(new Powder("Coffee", console.POWDER_CONTAINER_REGULAR_SIZE));
        sm.addConsumable(new Powder("Sugar", console.POWDER_CONTAINER_REGULAR_SIZE));

        //Liquids
        sm.addConsumable(new Liquid("Water", console.LIQUID_CONTAINER_REGULAR_SIZE));
        sm.addConsumable(new Liquid("Milk", console.LIQUID_CONTAINER_REGULAR_SIZE));

        //Materials
        sm.addConsumable(new Cup("SmallCup", console.SMALL_CUP_CONTAINER, "Small"));
        sm.addConsumable(new Cup("BigCup", console.BIG_CUP_CONTAINER, "Big"));
    }

    private static void insertGuiDevices() {
        //Same as insertConsoleDevices()
    }

    private static void startCycleOf(SoftwareMachine machine) {
        //Loading external modules
        DisplayPanel display = (DisplayPanel) machine.getModule(DisplayPanel.class.getSimpleName());
        NumPad numPad = (NumPad) machine.getModule(NumPad.class.getSimpleName());
        CoinReader coinReader = (CoinReader) machine.getModule(CoinReader.class.getSimpleName());
        ChangeCase changeCase = (ChangeCase) machine.getModule(ChangeCase.class.getSimpleName());
        ProductCase productCase = (ProductCase) machine.getModule(ProductCase.class.getSimpleName());

        //Loading Recipes
        rm.loadRecipes();
        rm.validateRecipes();

        //New Menu Implementation
        new Menu();
        int EXIT_SELECTION = -1;
        int selection = 0;
        while (selection != EXIT_SELECTION) {
            String actionCode = Menu.calculateActionCode(selection);
            display.displayMessage(Menu.getMenu());
            switch (actionCode) {
                case "000": //Welcome Message
                    selection = 1;
                    break;
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

                case "113": //Refill Containers
                    machine.refillContainers();
                    selection = EXIT_SELECTION;
                    break;

                case "121": //Buy a Drink
                    display.displayMessage("--------Available Recipes--------\n");
                    for (Recipe recipe : rm.getAvailableRecipes().values()) {
                        display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + "c)");
                    }
                    Recipe recipe;
                    //Select Recipe
                    do {
                        display.displayMessage("Please select recipe code to execute (Insert Digits one by one): ");
                        recipeCode = numPad.readCode(3);
                        recipe = rm.getRecipe(String.valueOf(recipeCode));
                        if (recipe == null) {
                            display.displayMessage("Recipe not Found!");
                        }
                    } while (recipe == null);
                    //Receive Money and check if there is any change to return
                    int change = coinReader.receiveMoney(recipe.getPrice());
                    display.displayMessage("You have filled " + ((change == 0) ? "exactly " : "") + "the required " +
                            "amount!" + ((change == 0) ? "" : ("\nYou may now take your change (" + change + "c)" +
                            ":")));
                    changeCase.setChange(change);
                    //Execute Recipe
                    rm.executeRecipe(recipe);
                    productCase.prepareProduct(recipe);
                    selection = EXIT_SELECTION;
                    break;
                default: //Action Code not recognised
                    display.displayMessage("Action not found!");
            }
        }
    }


    private static class Menu {

        //Class variables
        static final String INITIAL_CODE = "000";
        static HashMap<String, String> actionCodes = new HashMap<>();
        //Current state
        static String currentActionCode = INITIAL_CODE;

        //Constructor
        Menu() {
            insertActionCodes();
        }

        //Getters
        static String getMenu() {
            return actionCodes.get(currentActionCode);
        }

        //Other Methods
        static void insertActionCodes() {
            actionCodes.put("000", "=======================================\nWelcome to Vending Machine v1.0 (alpha)" +
                    "\n=======================================\n"); //WELCOME MESSAGE
            actionCodes.put("100", "==========MAIN MENU==========\n1. Administrator\n2. " +
                    "User\n=============================\nPlease select user type: "); //MAIN MENU
            actionCodes.put("110", "--------Administrator Submenu--------\n1. Create Recipes\n2. Delete " +
                    "Recipes\n3." +
                    " Refill Containers\n-------------------------------------\nPlease select action: "); //ADMIN MAIN
            // MENU
            actionCodes.put("120", "---------User Submenu--------\n" +
                    "1. Buy a drink\n-----------------------------\nPlease select action: "); //USER MAIN MENU
            actionCodes.put("111", "You have chosen to Create a Recipe!"); //Create Recipe (Admin)
            actionCodes.put("112", "You have chosen to Delete a Recipe!"); //Delete Recipe (Admin)
            actionCodes.put("113", "You have chosen to Refill Containers!"); //Refill Containers (Admin)
            actionCodes.put("121", "You have chosen to Buy a Drink!"); //Buy a Drink (User)
        }

        static String calculateActionCode(int selection) {
            String prefix = currentActionCode.substring(0, currentActionCode.indexOf('0'));
            currentActionCode = prefix + selection + currentActionCode.substring(currentActionCode.indexOf('0') + 1);
            return currentActionCode;
        }
    }
}
