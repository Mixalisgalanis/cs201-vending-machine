package recipes.dao;

import recipes.Recipe;

public interface RecipeDAO {

    Recipe[] loadRecipes();
    Recipe loadRecipe(int code);
    void storeRecipe(Recipe r);
    void deleteRecipe(int code);
}
