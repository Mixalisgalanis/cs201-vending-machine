package recipes;

import machine.Data;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.Ingredient;
import recipes.dao.DAOFactory;
import recipes.dao.RecipeDAO;
import recipes.step.RecipeStep;
import utilities.Reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeManager {

    private static RecipeManager instance;
    private static boolean allowInstance = true;
    //Class Variables
    private HashMap<String, Recipe> recipes;
    private HashMap<String, Recipe> availableRecipes;
    private DAOFactory factory;
    private RecipeDAO recipeDAO;
    private Reader reader;
    private Data data;


    //Constructor
    public RecipeManager() {
        data = Data.getInstance();
        recipes = new HashMap<>();
        availableRecipes = new HashMap<>();
        try {
            factory = DAOFactory.getDAOFactory("FileSystem");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //Prevents further instantiations
        instance = this;
        allowInstance = false;
    }

    public static RecipeManager getInstance() {
        return instance;
    }

    //Getters
    public HashMap<String, Recipe> getRecipes() {
        return recipes;
    }

    public HashMap<String, Recipe> getAvailableRecipes() {
        return availableRecipes;
    }

    //Other Methods

    /**
     * Finder Method - Finds a Recipe based on it's code
     *
     * @param code of the desirable recipe
     * @return the desirable recipe
     */
    public Recipe getRecipe(int code) {
        return recipes.get(code);
    }

    /**
     * Loads all .rcp files in the ./recipes directory, creates the recipes by the disassemble recipe method and insert it into the HashMap
     */
    public void loadRecipes() {
        recipes = recipeDAO.loadRecipes();
    }

    /**
     * Finds and adds to the available recipes list just the enabled ones (ones which can be executed).
     */
    public void loadEnabledRecipes() {
        availableRecipes.clear();
        for (Recipe recipe : recipes.values()) {
            if (recipe.isAvailable()) availableRecipes.put(recipe.getCode(), recipe);
        }
    }

    /**
     * Enables or Disables recipes based on the recipe requirements (Cost & Quantities)
     */
    public void validateRecipes() {
        int balance = data.getCurrentBalance();
        for (Recipe recipe : recipes.values()) {
            if (balance >= recipe.getPrice()) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (data.getContainers().get(recipe.classNameFinder(ingredient.getName())).getConsumable().getQuantity() >= ingredient.getQuantity())
                        recipe.enable();
                    else {
                        recipe.disable();
                        break;
                    }
                }
            } else recipe.disable();
        }
        this.loadEnabledRecipes();
    }

    /**
     * Executes one of the available recipes step by step
     *
     * @param recipe which is going to be executed
     */
    public void executeRecipe(Recipe recipe) {
        data.setCurrentBalance(data.getCurrentBalance() - recipe.getPrice());
        while (recipe.hasMoreSteps()) {
            recipe.executeStep();
        }
    }

    /**
     * Creates a recipe using Reader Utility Class (asks user through command line)
     *
     * @implNote CONSOLE-ONLY!
     */
    public void createRecipe() {
        reader = new Reader();

        //Basic recipe properties
        String name = reader.readString("Enter Recipe Name: ");
        String code = reader.readString("Enter Recipe Code (Enter '0') to pick code automatically: ");
        if (!code.equals("0")) {
            while (recipeDAO.checkIfExists(code)) {
                code = reader.readString("Recipe with code " + code + " already exists! Pick another one: ");
            }
        } else {
            int testCode = 100;
            while (recipeDAO.checkIfExists(String.valueOf(testCode))) testCode++;
            code = String.valueOf(testCode);
        }
        int price = reader.readInt("Enter " + name + "'s cost: ");
        while (price <= 0) price = reader.readInt("Invalid price amount, try again: ");
        String type = reader.readString("Enter Recipe Type: ");

        //Ingredients
        System.out.println("Available Consumables ----------");
        int i = 0;
        for (Consumable consumable : data.getConsumables().values()) {
            System.out.println((++i) + consumable.getName() + "[" + consumable.getConsumableType() + "]");
        }
        String[] ingredientsData = reader.readString("Please Enter Ingredients using the above options (ex: 'Coffee,40,Water,60')").split(",");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int j = 0; j < ingredientsData.length / 2; j++) {
            try {
                Class<?> clazz = Class.forName("recipes.consumables.ingredients" + data.getConsumables().get(ingredientsData[2 * j]));
                Constructor<?> ctor = clazz.getConstructors()[0];
                Object object = ctor.newInstance(new Object[]{ingredientsData[2 * j], Integer.parseInt(ingredientsData[2 * j + 1])});
                ingredients.add((Ingredient) object);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //Steps
        ArrayList<RecipeStep> steps = new ArrayList<>();

        recipes.put(code, new Recipe(name, code, price, type, ingredients, steps));
        validateRecipes();
        loadEnabledRecipes();
        recipeDAO.storeRecipe(recipes.get(code));
    }
}
