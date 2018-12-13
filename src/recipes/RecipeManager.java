package recipes;

import machine.SoftwareMachine;
import modules.containers.Container;
import recipes.consumables.Consumable;
import recipes.consumables.ingredients.Ingredient;
import recipes.dao.DAOFactory;
import recipes.dao.RecipeDAO;
import recipes.step.RecipeStep;
import utilities.Reader;
import utilities.StringManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RecipeManager {

    private static RecipeManager instance;
    //Class Variables
    private final HashMap<String, Recipe> recipes;
    private final ArrayList<Recipe> availableRecipes;
    private final SoftwareMachine sm;
    private DAOFactory factory;
    private RecipeDAO recipeDAO;


    //Constructor
    private RecipeManager() {
        sm = SoftwareMachine.getInstance();
        recipes = new HashMap<>();
        availableRecipes = new ArrayList<>();
        try {
            factory = DAOFactory.getDAOFactory("FileSystem");
            recipeDAO = factory.getRecipeDAO();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //Prevents further instantiations
        instance = this;
    }

    public static RecipeManager getInstance() {
        return (instance != null) ? instance : new RecipeManager();
    }

    //Getters
    public HashMap<String, Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<Recipe> getAvailableRecipes() {
        return availableRecipes;
    }

    //Other Methods

    /**
     * Finder Method - Finds a Recipe based on it's code
     *
     * @param code of the desirable recipe
     * @return the desirable recipe
     */
    public Recipe getRecipe(String code) {
        return recipes.get(code);
    }

    /**
     * Loads all .rcp files in the ./recipes directory, creates the recipes by the disassemble recipe method and insert it into the HashMap
     */
    public void loadRecipes() {
        recipes.clear();
        recipes.putAll(recipeDAO.loadRecipes());
    }

    /**
     * Finds and adds to the available recipes list just the enabled ones (ones which can be executed).
     */
    private void loadEnabledRecipes() {
        availableRecipes.clear();
        for (Recipe recipe : recipes.values()) {
            if (recipe.isAvailable()) {
                availableRecipes.add(recipe);
            }
        }
        availableRecipes.sort(Comparator.comparing(Recipe::getCode));
    }

    /**
     * Enables or Disables recipes based on the recipe requirements (Cost & Quantities)
     */
    public void validateRecipes() {
        for (Recipe recipe : recipes.values()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                Container tempContainer = sm.getContainers().get(ingredient.getName() + Container.class.getSimpleName());
                if (tempContainer.getConsumable().getQuantity() >= ingredient.getQuantity()) {
                    recipe.enable();
                } else {
                    recipe.disable();
                    break;
                }
            }
        }
        loadEnabledRecipes();
    }

    /**
     * Executes one of the available recipes step by step
     *
     * @param recipe which is going to be executed
     */
    public void executeRecipe(Recipe recipe) {
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
        //Basic recipe properties
        String name = Reader.readString("Enter Recipe Name: ");
        String code = Reader.readString("Enter Recipe Code (Enter '0') to pick code automatically: ");
        if (!code.equals("0")) {
            while (recipeDAO.checkIfExists(code)) {
                code = Reader.readString("Recipe with code " + code + " already exists! Pick another one: ");
            }
        } else {
            int testCode = 100;
            while (recipeDAO.checkIfExists(String.valueOf(testCode))) {
                testCode++;
            }
            code = String.valueOf(testCode);
        }
        int price = Reader.readInt("Enter " + name + "'s cost: ");
        while (price <= 0) {
            price = Reader.readInt("Invalid price amount, try again: ");
        }
        String type = Reader.readString("Enter Recipe Type: ");

        //Ingredients
        System.out.println(StringManager.generateDashLine("Available Consumables", "-"));
        for (Consumable consumable : sm.getConsumables().values()) {
            System.out.println("[" + consumable.getConsumableType() + "] - " + consumable.getName());
        }
        String[] ingredientsData =
                Reader.readString("Please Select Ingredients using the above options (ex: 'COFFEE,40,WATER,60')").split(
                        ",");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int j = 0; j < ingredientsData.length / 2; j++) {
            try {
                Class<?> clazz = (sm.getConsumables().get(nameDecoder(ingredientsData[2 * j])).getClass());
                Constructor<?> ctor = clazz.getConstructors()[0];
                Object object = ctor.newInstance(ingredientsData[2 * j], Integer.parseInt(ingredientsData[2 * j + 1]));
                ingredients.add((Ingredient) object);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //Steps
        String[] stepsData = Reader.readString("Please Enter Steps (ex: TRANSFER POWDERS MIXER COFFEE 40,): ").split(
                ",");
        ArrayList<RecipeStep> steps = new ArrayList<>();
        for (String currentStep : stepsData) {
            try {
                Class<?> clazz = Class.forName("recipes.step." + nameDecoder(currentStep.substring(0, currentStep.indexOf(" "))) + "Step");
                Constructor<?> ctor = clazz.getConstructors()[1];
                int a = Integer.parseInt(currentStep.substring(currentStep.lastIndexOf(" ") + 1));
                String[] stepData = currentStep.substring(currentStep.indexOf(" ") + 1, currentStep.lastIndexOf(" ")).split(" ");
                Object object = ctor.newInstance(stepData, a); //Creates object from that
                // constructor
                steps.add((RecipeStep) object);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        recipes.put(code, new Recipe(name, code, price, type, ingredients, steps));
        validateRecipes();
        recipeDAO.storeRecipe(recipes.get(code));
    }

    public void removeRecipe(String recipeCode) {
        recipeDAO.deleteRecipe(recipes.get(recipeCode));
    }

    private String nameDecoder(String name) {
        if (name.equalsIgnoreCase("Coffee")) {
            return "Coffee";
        } else if (name.equalsIgnoreCase("Sugar")) {
            return "Sugar";
        } else if (name.equalsIgnoreCase("Water")) {
            return "Water";
        } else if (name.equalsIgnoreCase("Milk")) {
            return "Milk";
        } else if (name.equalsIgnoreCase("Transfer")) {
            return "Transfer";
        } else if (name.equalsIgnoreCase("Operate")) {
            return "Operate";
        }
        return name;
    }
}
