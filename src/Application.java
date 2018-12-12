import devices.consoleDevices.external.*;
import devices.consoleDevices.internal.*;
import machine.ConsoleMachine;
import machine.SoftwareMachine;
import machine.SwingMachine;
import modules.containers.Container;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.Liquid;
import recipes.consumables.ingredients.Powder;
import recipes.product.Product;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.*;

import java.util.HashMap;

public class Application {
    //Menu Action Codes
    private static final String AC_WELCOME_MESSAGE = "000";
    private static final String AC_MAIN_MENU = "100";
    private static final String AC_ADMIN_SUBMENU = "110";
    private static final String AC_USER_SUBMENU = "120";
    private static final String AC_ADMIN_CREATE_RECIPE = "111";
    private static final String AC_ADMIN_DELETE_RECIPE = "112";
    private static final String AC_ADMIN_REFILL_CONTAINERS = "113";
    private static final String AC_USER_BUY_DRINK = "121";
    //Class variables
    private static final boolean GUI_ENABLED = false; //<----Change to switch between Graphical & Console User Interface
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
        assert machine != null;
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
        DispenserDevice materialDispenserDevice = new ConsoleDispenserDevice("CUPS", DeviceType.MaterialDispenser);

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

    private static void insertGuiDevices() {
        SwingMachine swing = (SwingMachine) machine;
        //TODO Insert GUI Devices

    }

    private static void insertConsumables() {
        ConsoleMachine console = (ConsoleMachine) machine; //Machine is console //TODO Remove Console Dependency

        //Powders
        sm.addConsumable(new Powder("Coffee", console.POWDER_CONTAINER_REGULAR_SIZE));
        sm.addConsumable(new Powder("Sugar", console.POWDER_CONTAINER_REGULAR_SIZE));
        //Renames HashMap Key

        //Liquids
        sm.addConsumable(new Liquid("Water", console.LIQUID_CONTAINER_REGULAR_SIZE));
        sm.addConsumable(new Liquid("Milk", console.LIQUID_CONTAINER_REGULAR_SIZE));

        //Materials
        sm.addConsumable(new Cup("SmallCup", console.SMALL_CUP_CONTAINER, "Small"));
        sm.addConsumable(new Cup("BigCup", console.BIG_CUP_CONTAINER, "Big"));

    }

    private static void startCycleOf(SoftwareMachine machine) {
        //Loading external modules
        DisplayPanel display = (DisplayPanel) machine.getModule(DisplayPanel.class.getSimpleName());
        NumPad numPad = (NumPad) machine.getModule(NumPad.class.getSimpleName());
        CoinReader coinReader = (CoinReader) machine.getModule(CoinReader.class.getSimpleName());
        ChangeCase changeCase = (ChangeCase) machine.getModule(ChangeCase.class.getSimpleName());
        ProductCase productCase = (ProductCase) machine.getModule(ProductCase.class.getSimpleName());
        assert display != null;
        assert numPad != null;
        assert coinReader != null;
        assert changeCase != null;
        assert productCase != null;

        //Loading Recipes
        rm.loadRecipes();
        rm.validateRecipes();

        //Should pass generalCheck
        generalCheck();

        //Displaying Menus and taking actions
        new Menu();
        int EXIT_SELECTION = -1;
        int selection = 0;
        while (selection != EXIT_SELECTION) {
            String actionCode = Menu.calculateActionCode(selection);
            display.displayMessage(Menu.getMenu());
            switch (actionCode) {
                case AC_WELCOME_MESSAGE:
                    selection = 1;
                    break;
                case AC_MAIN_MENU:
                    selection = numPad.readCode(1);
                    break;

                case AC_ADMIN_SUBMENU:
                    selection = numPad.readCode(1);
                    break;

                case AC_USER_SUBMENU:
                    selection = numPad.readCode(1);
                    break;

                case AC_ADMIN_CREATE_RECIPE:
                    rm.createRecipe();
                    selection = EXIT_SELECTION;
                    break;

                case AC_ADMIN_DELETE_RECIPE:
                    //Display Recipes Header
                    display.displayMessage(Menu.generateDashLine("Available Recipes", "-"));
                    for (Recipe recipe : rm.getRecipes().values()) {
                        display.displayMessage("[" + recipe.getCode() + "] - " + recipe.getName() + " (" + recipe.getPrice() + ")");
                    }
                    //Display Recipes Footer
                    display.displayMessage(Menu.generateDashLine("", "-") + "\nEnter recipe code to delete: ");
                    int recipeCode = numPad.readCode(3);
                    rm.removeRecipe(String.valueOf(recipeCode));
                    display.displayMessage("Recipe removed successfully!");
                    selection = EXIT_SELECTION;
                    break;

                case AC_ADMIN_REFILL_CONTAINERS:
                    machine.refillContainers();
                    selection = EXIT_SELECTION;
                    break;

                case AC_USER_BUY_DRINK:
                    //Display available recipes
                    display.displayMessage(Menu.generateDashLine("Available Recipes", "-"));
                    for (Recipe recipe : rm.getAvailableRecipes()) {
                        display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + "c)");
                    }
                    display.displayMessage(Menu.generateDashLine("", "-"));
                    Recipe recipe;

                    //Select Recipe
                    do {
                        display.displayMessage("Please enter desired recipe code (one digit at a time): ");
                        recipeCode = numPad.readCode(3);
                        recipe = rm.getRecipe(String.valueOf(recipeCode));
                        if (recipe == null) {
                            display.displayMessage("Recipe not found!");
                        }
                    } while (recipe == null);
                    display.displayMessage("You have selected " + recipe.getName() + "!");

                    //Receive Money and check if there is any change to return
                    int change = coinReader.receiveMoney(recipe.getPrice());
                    display.displayMessage("You have filled " + ((change == 0) ? "exactly " : "") + "the required " +
                            "amount!" + ((change == 0) ? "" : ("\nYou may now take your change (" + change + "c)" + ":")));
                    changeCase.setChange(change);
                    //Execute Recipe
                    display.clearScreen();
                    display.displayMessage(Menu.generateDashLine("", "-") + "\nExecuting " +
                            "Recipe! This may take a while. . .\n" + Menu.generateDashLine("", "-"));
                    rm.executeRecipe(recipe);
                    productCase.prepareProduct(recipe.getName(), recipe.getCupSize());
                    Product product = productCase.getProduct();
                    selection = EXIT_SELECTION;
                    break;
                default: //Action Code not recognised
                    display.displayMessage("Action not found!");
            }
        }
    }

    /**
     * Makes sure dispensers have containers and containers have consumables.
     * To enable Assertion Check you need to edit run configuration and add "-ea" argument to VM Options
     */
    private static void generalCheck() {
        assert (sm.getContainers() != null);
        assert (sm.getProcessors() != null);
        assert (sm.getConsumables() != null);
        assert (sm.getDispensers() != null);
        for (ConsumableDispenser dispenser : sm.getDispensers().values()) {
            assert (dispenser.getContainers() != null);
            for (Container container : dispenser.getContainers().values()) {
                assert (container.getConsumable() != null);
            }
        }
    }

    private static class Menu {

        //Class variables
        static final int TOTAL_CHAR = 40;
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
            actionCodes.put(AC_WELCOME_MESSAGE, generateDashLine("", "=") + "\nWelcome to Vending Machine v1.0 " +
                    "(alpha)\n" + generateDashLine("", "="));
            actionCodes.put(AC_MAIN_MENU, generateDashLine("MAIN MENU", "=") + "\n1. Administrator\n" +
                    "2. User\n" + generateDashLine("", "=") + "\nPlease select user type: ");
            actionCodes.put(AC_ADMIN_SUBMENU, generateDashLine("Administrator Submenu", "-") + "\n1. Create " +
                    "Recipes\n2. Delete Recipes\n3. Refill Containers\n" + generateDashLine("", "-") + "\nPlease " +
                    "select action: ");
            actionCodes.put(AC_USER_SUBMENU, generateDashLine("User Submenu", "-") + "\n1. Buy a Drink\n" +
                    generateDashLine("", "-") + "\nPlease select action:");
            actionCodes.put(AC_ADMIN_CREATE_RECIPE, "You have chosen to Create a Recipe!");
            actionCodes.put(AC_ADMIN_DELETE_RECIPE, "You have chosen to Delete a Recipe!");
            actionCodes.put(AC_ADMIN_REFILL_CONTAINERS, "You have chosen to Refill Containers!");
            actionCodes.put(AC_USER_BUY_DRINK, "You have chosen to Buy a Drink!");
        }

        static String calculateActionCode(int selection) {
            String prefix = currentActionCode.substring(0, currentActionCode.indexOf('0'));
            currentActionCode = prefix + selection + currentActionCode.substring(currentActionCode.indexOf('0') + 1);
            return currentActionCode;
        }

        static String generateDashLine(String message, String lineType) {
            String result = "";
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < (TOTAL_CHAR - message.length() - i) / 2; j++) {
                    result = result.concat(lineType);
                }
                if (i == 0) {
                    result = result.concat(message);
                }
            }
            return result;
        }
    }
}
