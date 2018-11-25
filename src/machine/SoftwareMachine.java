package machine;

import modules.containers.DosingContainer;
import modules.containers.FlowContainer;
import modules.containers.MaterialContainer;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import recipes.Recipe;
import recipes.RecipeManager;
import recipes.consumables.Consumable;

public class SoftwareMachine {

    //Class Variables
    //Constants
    private static final String MAIN_MENU = "=======MAIN MENU=======\nTypes of Users:\n1. Administrator\n2. User\n=======================\nPlease select type: ";
    private static final String ADMIN_SUBMENU = "----Administrator Submenu----\nActions:\n1. Create Recipes\n2. Delete Recipes\n3. Refill Containers\nPlease select action: ";
    private static final String USER_SUBMENU = "----User Submenu----\nActions:\n1. Buy a drink\nPlease select actiion: ";
    private static final String RECIPES_HEADER = "----Available Recipes----\n";
    private static final String RECIPES_FOOTER = "Please select recipe code to execute: ";
    //Singleton
    private static SoftwareMachine instance;
    private static boolean allowInstance = true;
    //Data Required
    private Data data;
    private RecipeManager rm;

    public SoftwareMachine() {
        this.data = (Data.getInstance() != null) ? Data.getInstance() : new Data();
        this.rm = (RecipeManager.getInstance() != null) ? RecipeManager.getInstance() : new RecipeManager();
        insertData();
        //Prevent further instantiation
        instance = this;
        allowInstance = false;
    }

    public static SoftwareMachine getInstance() {
        return instance;
    }

    private void insertData() {
        /*
        Adding Consumables
         */
        //Powder Consumables
        data.addConsumable(new Consumable("Coffee", data.STANDARD_DOSING_CONTAINER_SIZE, "Powder"));
        data.addConsumable(new Consumable("Sugar", data.STANDARD_DOSING_CONTAINER_SIZE, "Powder"));

        //Liquid Consumables
        data.addConsumable(new Consumable("Water", data.STANDARD_FLOW_CONTAINER_SIZE, "Liquid"));

        //Material Consumables
        data.addConsumable(new Consumable("Cup", data.STANDARD_MATERIAL_CONTAINER_SIZE, "Material"));

        /*
        Adding Modules
         */
        //External Modules
        data.addHardwareEntity(new NumPad());
        data.addHardwareEntity(new CoinReader());
        data.addHardwareEntity(new ChangeCase());
        data.addHardwareEntity(new DisplayPanel());
        data.addHardwareEntity(new ProductCase());

        //ConsumableDispensers
        data.addHardwareEntity(new ConsumableDispenser("POWDERS", "Powder"));
        data.addHardwareEntity(new ConsumableDispenser("LIQUIDS", "Liquid"));
        data.addHardwareEntity(new ConsumableDispenser("MATERIALS", "Material"));

        //DosingContainers
        data.addHardwareEntity(new DosingContainer("Coffee", data.STANDARD_DOSING_CONTAINER_SIZE, data.findConsumable("Coffee")));
        data.addHardwareEntity(new DosingContainer("Sugar", data.STANDARD_DOSING_CONTAINER_SIZE, data.findConsumable("Sugar")));
        //LiquidContainers
        data.addHardwareEntity(new FlowContainer("Water", data.STANDARD_FLOW_CONTAINER_SIZE, data.findConsumable("Water")));
        //MaterialContainers
        data.addHardwareEntity(new MaterialContainer("Cup", data.STANDARD_MATERIAL_CONTAINER_SIZE, data.findConsumable("Cup")));

    }

    public void startCycle() {
        rm.loadRecipes();
        rm.validateRecipes();
        rm.loadEnabledRecipes();
        DisplayPanel display = (DisplayPanel) data.getModules().get("DisplayPanel");
        NumPad numPad = (NumPad) data.getModules().get("NumPad");
        CoinReader coinReader = (CoinReader) data.getModules().get("CoinReader");
        ChangeCase changeCase = (ChangeCase) data.getModules().get("ChangeCase");
        ProductCase productCase = (ProductCase) data.getModules().get("ProductCase");

        display.displayMessage("Welcome to Vending Machine v1.0 (alpha)");
        display.displayMessage(MAIN_MENU);
        switch (String.valueOf(numPad.readCode(1))) {
            case "1":
                display.displayMessage(ADMIN_SUBMENU);
                switch (String.valueOf(numPad.readCode(1))) {
                    case "1": //TODO CreateRecipe
                        break;
                    case "2": //TODO DeleteRecipe
                        break;
                    case "3": //TODO RefillContainers
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
                        do{
                            display.displayMessage(RECIPES_FOOTER);
                            int recipeCode = numPad.readCode(3);
                            recipe = rm.getRecipe(String.valueOf(recipeCode));
                            if (recipe == null) display.displayMessage("Recipe not Found!");
                        } while(recipe==null);
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
