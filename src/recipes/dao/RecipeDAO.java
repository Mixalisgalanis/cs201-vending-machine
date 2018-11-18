package recipes.dao;

import recipes.Recipe;

import java.util.HashMap;

public interface RecipeDAO {

    HashMap<String, Recipe> loadRecipes();

    void storeRecipe(Recipe recipe);

    void deleteRecipe(String code);

    boolean checkIfExists(String code);
}
