package recipes.dao;

import recipes.Recipe;

import java.util.HashMap;

public interface RecipeDAO {

    HashMap<String, Recipe> loadRecipes();

    void storeRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    boolean checkIfExists(String code);
}
