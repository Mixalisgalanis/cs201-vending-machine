package recipes.dao;

import recipes.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FsRecipeDAO implements RecipeDAO {

    private static final String RECIPES_FOLDER = "recipes";
    private static final String RECIPES_FILE_SUFFIX = ".rcp";
    private final File folder = new File(RECIPES_FOLDER);

    @Override
    public HashMap<String, Recipe> loadRecipes() {
        HashMap<String, Recipe> recipes = new HashMap<>();
        for (File file : folder.listFiles()) {
            String recipeCode = file.getName().substring(0, file.getName().indexOf(" - "));
            try {
                String data = new Scanner(file).useDelimiter("\\A").next();
                recipes.put(recipeCode, new Recipe(recipeCode, data));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return recipes;
    }

    @Override
    public void storeRecipe(Recipe recipe) {
        File file = new File(RECIPES_FOLDER + "/" + recipe.getCode() + RECIPES_FILE_SUFFIX);
        FileWriter fileWriter; //Required Writers to access and read from the file
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(recipe.assemble());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipe(String code) {
        File file = new File(RECIPES_FOLDER + "/" + code + RECIPES_FILE_SUFFIX);
        file.delete();
    }


    @Override
    public boolean checkIfExists(String code) {
        for (File file : folder.listFiles()) {
            String recipeCode = file.getName().substring(0, file.getName().indexOf('.'));
            if (recipeCode.equalsIgnoreCase(code + RECIPES_FILE_SUFFIX)) {
                return true;
            }
        }
        return false;
    }
}
