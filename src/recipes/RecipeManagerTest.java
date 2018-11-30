package recipes;

import org.junit.Before;
import org.junit.Test;

public class RecipeManagerTest {

    private RecipeManager rm;
    private boolean loaded = false;

    @Before
    public void setUp() {
       // this.rm = (RecipeManager.getInstance() != null) ? RecipeManager.getInstance() : new RecipeManager();
        //this.loadRecipes();

    }

    @org.junit.Test
    public void loadRecipes() {
        if (!loaded) {
            rm.loadRecipes();
            System.out.println("Number of recipes loaded: " + rm.getRecipes().size());
            loaded = true;
        }
    }

    @org.junit.Test
    public void validateRecipes() {
        rm.validateRecipes();
        System.out.println("Available Recipes: " + rm.getAvailableRecipes().size() + "/" + rm.getRecipes().size());
    }

    @org.junit.Test
    public void executeRecipe() {
    }

    @Test
    public void createRecipe() {
        rm.createRecipe();
    }
}
