import consoleDevices.internal.ConsoleDispenserDevice;
import machine.ConsoleMachine;
import machine.SoftwareMachine;
import machine.SwingMachine;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import tuc.ece.cs201.vm.hw.device.DispenserDevice;

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

    public static void main(String args[]){
        console = new ConsoleMachine();
        gui = new SwingMachine();
        insertConsoleDevices();
        insertGuiDevices();

        rm = RecipeManager.getInstance();
        SoftwareMachine machine = SoftwareMachine.getInstance(console);
        startCycleOf(machine);
    }

    public static void insertConsoleDevices(){
        //Dispensers
        DispenserDevice dispenserDevice = new ConsoleDispenserDevice("POWDERS");


        console.addDevice(dispenserDevice);
    }

    public static void insertGuiDevices(){
        //Dispensers
        DispenserDevice dispenserDevice = new SwingDispenserDevice("POWDERS");


        gui.addDevice(dispenserDevice);
    }

    public static void startCycleOf(SoftwareMachine machine) {
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
