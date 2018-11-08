package recipes;

import recipes.dao.DAOFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeManager {

    //Class Variables
    private HashMap<Integer, Recipe> recipes;
    private DAOFactory factory;

    //Getters
    public HashMap<Integer, Recipe> getRecipes() {
        return recipes;
    }

    //Other Methods
    public void loadRecipes() {
    }


    public Recipe getRecipe(int code) {
        return recipes.get(code);
    }

    public ArrayList<Recipe> getEnabled() {
        ArrayList<Recipe> availableRecipes = new ArrayList<>();
        for (Recipe recipe : recipes.values()){
            if(recipe.isAvailable()) availableRecipes.add(recipe);
        }
        return availableRecipes;
    }

    public void validateRecipes() {

    }

    public void executeRecipe(Recipe recipe) {
        while (recipe.hasMoreSteps()){
            recipe.executeStep();
        }
    }

}
