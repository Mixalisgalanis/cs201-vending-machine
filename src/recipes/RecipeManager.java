package recipes;

import modules.containers.Container;
import recipes.consumables.ingredients.Ingredient;
import recipes.dao.DAOFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeManager {

    //Class Variables
    private HashMap<Integer, Recipe> recipes;
    private DAOFactory factory;

    //Constructor
    public RecipeManager() {
        recipes = new HashMap<>();
        factory = new DAOFactory();
    }

    //Getters
    public HashMap<Integer, Recipe> getRecipes() {
        return recipes;
    }

    //Other Methods

    /**
     * Loads all .rcp files in the ./recipes directory, creates the recipes by the disassemble recipe method and insert it into the HashMap
     */
    public void loadRecipes() {
        File folder = new File("recipes");
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
     * Finds and creates a list with just the available recipes (ones which can be executed)
     *
     * @return a list with the available recipes
     */
    public ArrayList<Recipe> getEnabled() {
        ArrayList<Recipe> availableRecipes = new ArrayList<>();
        for (Recipe recipe : recipes.values()) {
            if (recipe.isAvailable()) availableRecipes.add(recipe);
        }
        return availableRecipes;
    }

    /**
     * Enables or Disables recipes based on the recipe requirements (Cost & Quantities)
     */
    public void validateRecipes(HashMap<String, Container> containers, int moneyReceived) {
        for (Recipe recipe : recipes.values()) {
            if (moneyReceived >= recipe.getPrice()) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (containers.get(recipe.classNameFinder(ingredient.getName())).getConsumable().getQuantity() >= ingredient.getQuantity())
                        recipe.enable();
                    else {
                        recipe.disable();
                        break;
                    }
                }
            } else recipe.disable();
        }
    }

    /**
     * Executes one of the available recipes step by step
     *
     * @param recipe which is going to be executed
     */
    public void executeRecipe(Recipe recipe) {
        while (recipe.hasMoreSteps()) {
            recipe.executeStep();
        }
    }

}
