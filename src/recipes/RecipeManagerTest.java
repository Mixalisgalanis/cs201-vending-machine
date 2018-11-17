package recipes;

import machine.Data;
import machine.SoftwareMachine;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RecipeManagerTest {

    private RecipeManager recipeManager;
    private boolean loaded = false;
    private SoftwareMachine sm = new SoftwareMachine();
    private Data data = Data.getInstance();

    @Before
    public void setUp() throws Exception {
        recipeManager = new RecipeManager();
        this.loadRecipes();

    }

    @org.junit.Test
    public void loadRecipes() {
        if (!loaded) {
            recipeManager.loadRecipes();
            System.out.println("Number of recipes loaded: " + recipeManager.getRecipes().size());
            loaded = true;
        }
    }


    @Test
    public void getRecipes() {
        recipeManager.getRecipes();
    }

    @org.junit.Test
    public void validateRecipes() {
        //Abundant Materials and affordable (Recipe Cost: 180)
        recipeManager.validateRecipes(data.getContainers(), 200);
        ArrayList<Recipe> availableRecipes = recipeManager.getEnabled();
        System.out.println("Available Recipes: " + availableRecipes.size() + "/" + recipeManager.getRecipes().size());

        //Abundant Materials but not affordable (Recipe Cost: 180)
        recipeManager.validateRecipes(data.getContainers(), 100);
        availableRecipes.clear();
        availableRecipes = recipeManager.getEnabled();
        System.out.println("Available Recipes: " + availableRecipes.size() + "/" + recipeManager.getRecipes().size());
    }

    @org.junit.Test
    public void executeRecipe() {
    }
}
