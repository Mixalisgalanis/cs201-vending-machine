package recipes;

import machine.Data;
import recipes.consumables.ingredients.Ingredient;
import recipes.dao.DAOFactory;

import java.io.File;
import java.util.HashMap;

public class RecipeManager {

    //Class Variables
    private HashMap<Integer, Recipe> recipes;
    private HashMap<Integer, Recipe> availableRecipes;
    private DAOFactory factory;

    private static RecipeManager instance;
    private static boolean allowInstance = true;

    private Data data;


    //Constructor
    public RecipeManager() {
        recipes = new HashMap<>();
        availableRecipes = new HashMap<>();
        factory = new DAOFactory();
        data = Data.getInstance();

        //Prevents other instantiations
        instance = this;
        allowInstance = false;
    }

    //Getters
    public HashMap<Integer, Recipe> getRecipes() {
        return recipes;
    }

    public static RecipeManager getInstance() {
        return instance;
    }

    public HashMap<Integer, Recipe> getAvailableRecipes() {
        return availableRecipes;
    }

    //Other Methods

    /**
     * Loads all .rcp files in the ./recipes directory, creates the recipes by the disassemble recipe method and insert it into the HashMap
     */
    public void loadRecipes() {
        recipes.clear();
        File folder = new File("/recipes/");
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                int recipeCode = Integer.parseInt(file.getName().substring(0, file.getName().indexOf('.')));
                recipes.put(recipeCode, new Recipe(file, recipeCode));
            }
        }
    }

    /**
     * Finder Method - Finds a Recipe based on it's code
     *
     * @param code of the desirable recipe
     * @return the desirable recipe
     */
    public Recipe getRecipe(int code) {
        return recipes.get(code);
    }

    /**
     * Finds and adds to the available recipes list just the enabled ones (ones which can be executed).
     */
    public void loadEnabled() {
        availableRecipes.clear();
        for (Recipe recipe : recipes.values()) {
            if (recipe.isAvailable()) availableRecipes.put(recipe.getCode(), recipe);
        }
    }

    /**
     * Enables or Disables recipes based on the recipe requirements (Cost & Quantities)
     */
    public void validateRecipes() {
        int balance = data.getCurrentBalance();
        for (Recipe recipe : recipes.values()) {
            if (balance >= recipe.getPrice()) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (data.getContainers().get(recipe.classNameFinder(ingredient.getName())).getConsumable().getQuantity() >= ingredient.getQuantity())
                        recipe.enable();
                    else {
                        recipe.disable();
                        break;
                    }
                }
            } else recipe.disable();
        }
        this.loadEnabled();
    }

    /**
     * Executes one of the available recipes step by step
     *
     * @param recipe which is going to be executed
     */
    public void executeRecipe(Recipe recipe) {
        data.setCurrentBalance(data.getCurrentBalance() - recipe.getPrice());
        while (recipe.hasMoreSteps()) {
            recipe.executeStep();
        }
    }

}
