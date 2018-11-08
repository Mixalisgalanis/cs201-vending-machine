package recipes;

import recipes.consumables.ingredients.Ingredient;
import recipes.step.OperateStep;
import recipes.step.RecipeStep;
import recipes.step.TransferStep;
import utilities.Reader;

import java.util.ArrayList;

public class Recipe {

    /*Class variables*/
    //Basic variable types
    private String name;
    private int code;
    private int cost;
    private String type;
    private boolean available;


    //Ingredients
    private ArrayList<Ingredient> ingredients;

    //Steps
    private ArrayList<RecipeStep> recipeSteps;
    private RecipeStep recipeStep;
    private int recipeStepNumber;

    //Utilities
    private Reader reader;

    /*Constructor*/
    public Recipe(String name, int code, int cost, String type) {
        this.name = name;
        this.code = code;
        this.cost = cost;
        this.type = type;
        this.available = false;

        reader = new Reader();
        recipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    /*Getter & Setters*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public RecipeStep getRecipeStep() {
        return recipeStep;
    }

    //Other Methods
    public RecipeStep getNextStep() {
        return (recipeSteps.get(recipeStepNumber++));
    }

    public boolean hasMoreSteps() {
        return (recipeStepNumber < recipeSteps.size() - 1);
    }

    public void enable() {
        this.available = true;
    }

    public void disable() {
        this.available = false;
    }

    /**
     * Converts a recipe text into a working recipe
     */
    public String decrypt() {

    }

    /**
     * Converts a working recipe into a recipe text
     */
    public void encrypt(String data) {

    }

    public void executeStep() {
        getNextStep();

    }

}
