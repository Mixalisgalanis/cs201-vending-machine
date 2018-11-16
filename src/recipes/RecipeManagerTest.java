package recipes;

import org.junit.Before;
import org.junit.Test;

public class RecipeManagerTest {

    private RecipeManager recipeManager;

    @Before
    public void setUp() throws Exception {
        recipeManager = new RecipeManager();
        this.loadRecipes();
    }

    @org.junit.Test
    public void loadRecipes() {
        recipeManager.loadRecipes();
        System.out.println("Number of recipes loaded: " + recipeManager.getRecipes().size());
    }


    @Test
    public void getRecipes() {
        recipeManager.getRecipes();
    }

    @org.junit.Test
    public void validateRecipes() {
    }

    @org.junit.Test
    public void executeRecipe() {
    }
}
