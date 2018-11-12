package recipes;

import recipes.dao.DAOFactory;

import java.io.File;
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
        File folder = new File("/recipes");
        if (folder.isDirectory()){
            for (File file: folder.listFiles()){
                recipes.put(Integer.parseInt(file.getName().substring(0,file.getName().indexOf('.'))), new Recipe(file));
            }
        }
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
