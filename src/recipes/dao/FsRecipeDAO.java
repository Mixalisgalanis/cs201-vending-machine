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
            if (file.getName().contains(RECIPES_FILE_SUFFIX)) {
                String recipeCode = file.getName().substring(0, file.getName().indexOf(" - "));
                try {
                    String data = new Scanner(file).useDelimiter("\\A").next();
                    recipes.put(recipeCode, new Recipe(recipeCode, data));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
    public void deleteRecipe(Recipe recipe) {
        for (File file : folder.listFiles()) {
            String filePath = recipe.getCode() + " - " + recipe.getName() + RECIPES_FILE_SUFFIX;
            if (file.getName().equals(filePath)) {
                if (!file.delete()) {
                    System.out.println("Could not delete " + recipe.getName() + " because file is in use!");
                } else {
                    System.out.println(recipe.getName() + " removed successfully!");
                }
            }
        }
    }


    @Override
    public boolean checkIfExists(String code) {
        for (File file : folder.listFiles()) {
            if (file.getPath().contains(code)) {
                return true;
            }
        }
        return false;
    }
}
