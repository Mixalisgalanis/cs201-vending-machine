import machine.SoftwareMachine;
import machine.console.ConsoleMachine;
import machine.swing.SwingMachine;
import modules.containers.Container;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.product.Product;
import tuc.ece.cs201.vm.hw.HardwareMachine;
import utilities.StringManager;

import java.util.HashMap;

/**
 * Console Implementation of the Project
 */
class Console {
    public static void main(String[] args) {
        HardwareMachine machine = ConsoleMachine.getInstance();
        new Application(machine);
    }
}

/**
 * Swing Implementation of the Project
 */
class Swing {
    public static void main(String[] args) {
        HardwareMachine machine = SwingMachine.getInstance();
        new Application(machine);
    }
}

/**
 * ******Project Authors:******
 * Mixalis Galanis (2016030036)
 * Ioanna Marinou (2016030143)
 * Nikos Piliouras (2016030148)
 * ****************************
 * This Class contains the main run of the Project. A Software Machine is created based on a template hardware
 * Machine. Hardware Devices are added here and appropriate modules are created based on the physical existing devices.
 * Console and Graphics Implementation can be easily switched by modifying the GUI_ENABLED variable. This Class also
 * uses a sophisticated Menu System which contains all the required Menus to navigate the user to a specific action.
 * Note: This Projects makes heavy use of assertions. To enable Assertion Check in case of debugging you need to edit
 * run configuration and add "-ea" argument to VM Options.
 * **************************************
 * State of Project:
 * Console Implementation: 100% completed
 * Graphics Implementation: 25% completed
 * **************************************
 */
class Application {

    //Class Variables
    private static SoftwareMachine sm;
    private static RecipeManager rm;

    //Constructor
    Application(HardwareMachine machine) {
        //Machine Preparation
        sm = SoftwareMachine.getInstance(machine);
        rm = RecipeManager.getInstance();

        //Start Cycle
        assert machine != null;
        startCycleOf(sm);
    }

    private void startCycleOf(SoftwareMachine machine) {
        assert machine != null;
        //Loading external modules
        DisplayPanel display = (DisplayPanel) machine.getModule(DisplayPanel.class.getSimpleName());
        assert display != null;
        NumPad numPad = (NumPad) machine.getModule(NumPad.class.getSimpleName());
        assert numPad != null;
        CoinReader coinReader = (CoinReader) machine.getModule(CoinReader.class.getSimpleName());
        assert coinReader != null;
        ChangeCase changeCase = (ChangeCase) machine.getModule(ChangeCase.class.getSimpleName());
        assert changeCase != null;
        ProductCase productCase = (ProductCase) machine.getModule(ProductCase.class.getSimpleName());
        assert productCase != null;

        //Loading Recipes
        rm.loadRecipes();
        rm.validateRecipes();

        //Should pass generalCheck
        generalCheck();

        //Displaying Menus and taking actions using a Menu System
        new Menu();
        int EXIT_SELECTION = -1;
        int RESET_SELECTION = -2;
        int selection = 0;
        while (selection != EXIT_SELECTION) {
            String actionCode = Menu.calculateActionCode(selection);
            display.displayMessage(Menu.getMenu());
            switch (actionCode) {
                case Menu.AC_WELCOME_MESSAGE:
                    selection = 1;
                    break;
                case Menu.AC_MAIN_MENU:
                    selection = numPad.readCode(1);
                    break;

                case Menu.AC_ADMIN_SUBMENU:
                    selection = numPad.readCode(1);
                    break;

                case Menu.AC_USER_SUBMENU:
                    selection = numPad.readCode(1);
                    break;

                case Menu.AC_ADMIN_CREATE_RECIPE:
                    rm.createRecipe();
                    selection = RESET_SELECTION;
                    break;

                case Menu.AC_ADMIN_DELETE_RECIPE:
                    //Display Recipes Header
                    display.displayMessage(StringManager.generateDashLine("Available Recipes", "-"));
                    for (Recipe recipe : rm.getRecipes().values()) {
                        display.displayMessage("[" + recipe.getCode() + "] - " + recipe.getName() + " (" + recipe.getPrice() + ")");
                    }
                    //Display Recipes Footer
                    display.displayMessage(StringManager.generateDashLine("", "-") + "\nEnter recipe code to delete: ");
                    int recipeCode = numPad.readCode(3);
                    rm.removeRecipe(String.valueOf(recipeCode));
                    selection = RESET_SELECTION;
                    break;

                case Menu.AC_ADMIN_CHECK_CONTAINER_LEVELS:
                    display.displayMessage(StringManager.generateDashLine("Available Containers", "-"));
                    for (Container container : machine.getContainers().values()) {
                        display.displayMessage("[" + container.getConsumable().getName() + "] " + container.getName() +
                                " - " + container.getConsumable().getQuantity() + "/" + container.getCapacity());
                    }
                    display.displayMessage(StringManager.generateDashLine("", "-"));
                    selection = RESET_SELECTION;
                    break;

                case Menu.AC_ADMIN_REFILL_CONTAINERS:
                    machine.refillContainers();
                    display.displayMessage("Containers Refilled Successfully!");
                    selection = RESET_SELECTION;
                    break;

                case Menu.AC_USER_BUY_DRINK:
                    //Display available recipes
                    display.displayMessage(StringManager.generateDashLine("Available Recipes", "-"));
                    for (Recipe recipe : rm.getAvailableRecipes()) {
                        display.displayMessage("[" + recipe.getCode() + "]: " + recipe.getName() + " (" + recipe.getPrice() + "c)");
                    }
                    display.displayMessage(StringManager.generateDashLine("", "-"));
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
                    display.displayMessage(StringManager.generateDashLine("", "-") + "\nExecuting " +
                            "Recipe! This may take a while. . .\n" + StringManager.generateDashLine("", "-"));
                    rm.executeRecipe(recipe);
                    productCase.prepareProduct(recipe.getName(), recipe.getCupSize());
                    Product product = productCase.getProduct();
                    selection = RESET_SELECTION;
                    break;
                default: //Action Code not recognised
                    selection = 0;
            }
        }
    }


    /**
     * Makes sure dispensers have containers and containers have consumables.
     * To enable Assertion Check you need to edit run configuration and add "-ea" argument to VM Options.
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

    /**
     * This class represents a Menu System which is based on action codes. Every action code represent a specific menu.
     * The selection is acquired through the NumPad and an action code is generated in this class. If the action code
     * is recognized (by the switch-case above), appropriate actions are taken.
     */
    private static class Menu {
        //Menu Action Codes
        private static final String AC_WELCOME_MESSAGE = "000";
        private static final String AC_MAIN_MENU = "100";
        private static final String AC_ADMIN_SUBMENU = "110";
        private static final String AC_USER_SUBMENU = "120";
        private static final String AC_ADMIN_CREATE_RECIPE = "111";
        private static final String AC_ADMIN_DELETE_RECIPE = "112";
        private static final String AC_ADMIN_CHECK_CONTAINER_LEVELS = "113";
        private static final String AC_ADMIN_REFILL_CONTAINERS = "114";
        private static final String AC_USER_BUY_DRINK = "121";

        //Class variables
        static String INITIAL_CODE = "000";
        static HashMap<String, String> actionCodes = new HashMap<>();
        //Current state
        static String currentActionCode = INITIAL_CODE;
        static String previousActionCode = INITIAL_CODE;

        //Constructor

        /**
         * Inserts all required Menus (Hash Map with action code (keys) and Menu String (values)
         */
        Menu() {
            actionCodes.put(AC_WELCOME_MESSAGE, StringManager.generateDashLine("", "=") + "\nWelcome to Vending Machine v1.0 " +
                    "(Beta)\n" + StringManager.generateDashLine("", "="));
            actionCodes.put(AC_MAIN_MENU, "\n" + StringManager.generateDashLine("MAIN MENU", "=") +
                    "\n1. Administrator\n2. User\n" + StringManager.generateDashLine("", "=") +
                    "\nPlease select user type: ");
            actionCodes.put(AC_ADMIN_SUBMENU, "\n" +
                    StringManager.generateDashLine("Administrator Submenu", "-") + "\n0. Back (<----)" +
                    "\n1. Create " + "Recipes\n2. Delete Recipes\n3. Check Container Levels\n4. Refill Containers\n"
                    + StringManager.generateDashLine("", "-") + "\nPlease select action: ");
            actionCodes.put(AC_USER_SUBMENU, "\n" + StringManager.generateDashLine("User Submenu", "-") +
                    "\n0. Back (<----)\n1. Buy a Drink\n" + StringManager.generateDashLine("", "-") + "\nPlease select action:");
            actionCodes.put(AC_ADMIN_CREATE_RECIPE, "You have chosen to Create a Recipe!\n");
            actionCodes.put(AC_ADMIN_DELETE_RECIPE, "You have chosen to Delete a Recipe!\n");
            actionCodes.put(AC_ADMIN_CHECK_CONTAINER_LEVELS, "You have chosen to check remaining Container Levels!\n");
            actionCodes.put(AC_ADMIN_REFILL_CONTAINERS, "You have chosen to Refill Containers!\n");
            actionCodes.put(AC_USER_BUY_DRINK, "You have chosen to Buy a Drink!\n");
        }

        /**
         * Searches and finds a menu based on an action code. If no Menus are found, an action not found message is
         * returned.
         *
         * @return a String of the Menu to display
         */
        static String getMenu() {
            return (actionCodes.get(currentActionCode) == null) ? "Action not found. Please try again!\n" :
                    actionCodes.get(currentActionCode);
        }

        /**
         * Generates an action code based on a selection. (EX: "100" (with selection '2') -> "120").
         * Having selection equal to 0, simulates the Menu system going back 1 Menu.
         * Having selection equal to -2, simulated the Menu system going back to Main Menu.
         *
         * @param selection NumPad Input
         * @return the new action code generated
         */
        static String calculateActionCode(int selection) {
            switch (selection) {
                case 0:
                    currentActionCode = previousActionCode;
                    break;
                case -2:
                    currentActionCode = AC_MAIN_MENU;
                    previousActionCode = INITIAL_CODE;
                    break;
                default:
                    previousActionCode = currentActionCode;
                    String prefix = currentActionCode.substring(0, currentActionCode.indexOf('0'));
                    String selectionString = String.valueOf(selection);
                    String suffix = "";
                    for (int i = 0; i < INITIAL_CODE.length() - (prefix.length() + selectionString.length()); i++) {
                        suffix = suffix.concat("0");
                    }
                    currentActionCode = prefix + selectionString + suffix;
            }
            return currentActionCode;
        }
    }
}