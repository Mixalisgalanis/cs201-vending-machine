package recipes;

import org.junit.Before;

public class RecipeManagerTest {

    private RecipeManager recipeManager;

    @Before
    public void setUp() throws Exception {
        recipeManager = new RecipeManager();
    }

    @org.junit.Test
    public void loadRecipes() {
        recipeManager.loadRecipes();
        System.out.println("Number of recipes loaded: " + recipeManager.getRecipes().size());
    }

    @org.junit.Test
    public void validateRecipes() {
    }

    @org.junit.Test
    public void executeRecipe() {
    }
}
