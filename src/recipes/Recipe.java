package recipes;

import recipes.consumables.ingredients.Ingredient;
import recipes.step.RecipeStep;
import recipes.step.TransferStep;
import utilities.StringManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Recipe {

    //Class variables

    private final String code;
    private final String NEW_LINE_SEPARATOR = "\r\n";
    //Ingredients
    private final ArrayList<Ingredient> ingredients;
    //Steps
    private final ArrayList<RecipeStep> recipeSteps;
    //Basic variable types
    private String name;
    private int price;
    private String type;
    private boolean available;
    private int currentRecipeStepNumber;

    //Constructors
    public Recipe(String name, String code, int price, String type) { //Standard Constructor
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        available = false;

        currentRecipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    /**
     * In this modified constructor, the recipe gets constructed step-by-step by the disassemble method.
     *
     * @param code required for the code basic variable type.
     * @param data in disk to disassemble.
     */
    public Recipe(String code, String data) {
        currentRecipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
        this.code = code;
        disassemble(data);
    }

    public Recipe(String name, String code, int price, String type, ArrayList<Ingredient> ingredients, ArrayList<RecipeStep> recipeSteps) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        available = false;
        currentRecipeStepNumber = 0;
    }

    //Getter & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    //Other Methods
    public String getCupSize() {
        return ((TransferStep) recipeSteps.get(recipeSteps.size() - 2)).getContent();
    }

    /**
     * Gets the next step from the recipe steps list.
     *
     * @return the next step from the recipe steps list.
     */
    public RecipeStep getNextStep() {
        return (recipeSteps.get(currentRecipeStepNumber++));
    }

    /**
     * Checks whether there are more steps in the recipe steps list.
     *
     * @return true if there are more steps, false if there aren't.
     */
    boolean hasMoreSteps() {
        return (currentRecipeStepNumber <= recipeSteps.size() - 1);
    }

    /**
     * Makes this particular recipe available
     */
    void enable() {
        available = true;
    }

    /**
     * Makes this particular recipe not available.
     */
    void disable() {
        available = false;
    }

    /**
     * Converts a recipe text into a working recipe by extracting text information.
     *
     * @param data the required data to categorize information from.
     */
    public void disassemble(String data) {
        //Extracting information from file
        try {
            //Basic recipe properties
            String tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            name = tempLine.substring(tempLine.indexOf(":") + 2); //Extracts name
            tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            price = Integer.parseInt(tempLine.substring(tempLine.indexOf(":") + 2)); //Extracts price
            tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            type = tempLine.substring(tempLine.indexOf(":") + 2); //Extracts type

            //Ingredients - ex: "INGREDIENTS: POW:COFFEE:40,POW:SUGAR:80,LIQ:WATER:100"
            tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            String ingredientsLine = tempLine.substring(tempLine.indexOf(":") + 2); //ex: "POW:COFFEE:40,POW:SUGAR:80,LIQ:WATER:100"
            String[] allIngredients = ingredientsLine.split(",");      //ex: allIngredients[0] == POW:COFFEE:40
            for (String ingredient : allIngredients) {
                String[] tempIngredient = ingredient.split(":");//ex: tempIngredient[0] == POW

                //Creating Liquid or Powder Object based on the ingredient name in file
                String className = classNameFinder(tempIngredient[0]);   //Gets the class name based on the ingredient name
                Class<?> clazz = Class.forName("recipes.consumables.ingredients." + className);          //Finds the class based on the class name
                Constructor<?> ctor = clazz.getConstructors()[0]; //Calls class constructor with given parameters
                Object object = ctor.newInstance(StringManager.toCamelCase(tempIngredient[1]),
                        Integer.parseInt(tempIngredient[2])); //Creates object from that constructor
                ingredients.add((Ingredient) object);          //Adds the object (Ingredient) on the ingredients list
            }

            //Recipe Steps
            //Proceeds to next line recursively until the end of the file
            tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            tempLine = data.substring(0, data.indexOf(NEW_LINE_SEPARATOR));          //Proceeds to next line
            data = data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length());
            do {
                //Creating TransferStep or OperateStep based on the step name in file
                Class<?> clazz = Class.forName("recipes.step." + classNameFinder(tempLine.substring(0,
                        tempLine.indexOf(" ")))); //Gets the class name based on the step name
                Constructor<?> ctor = clazz.getConstructors()[1];                //Finds the class based on the class name
                //Needed to collect all strings in an array in order to achieve equal constructor structure between the two Step Types
                String[] stepData = tempLine.substring(tempLine.indexOf(" ") + 1, tempLine.lastIndexOf(" ")).split(" ");
                int a = Integer.parseInt(tempLine.substring(tempLine.lastIndexOf(" ") + 1));
                Object object = ctor.newInstance(stepData, a); //Creates object from that constructor

                recipeSteps.add((RecipeStep) object); //Adds the object (Step) on the ingredients list
                tempLine = (!(data).equals("")) ? data.substring(0, data.indexOf(NEW_LINE_SEPARATOR)) : ""; //Proceeds to next line
                data = (!(data).equals("")) ? data.substring(data.indexOf(NEW_LINE_SEPARATOR) + NEW_LINE_SEPARATOR.length()) : "";
            } while (!tempLine.equals(""));
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a working recipe into a recipe text by building an .rcp file.
     */
    public String assemble() {
        String data = "";

        //Basic recipe properties
        data += "NAME: " + getName() + "\n";  //Creating name line
        data += "PRICE: " + getPrice() + "\n";       //Creating price line
        data += "TYPE: " + getType() + "\n";         //Creating type line

        //Ingredients
        //Building step by step the ingredients line
        data += "INGREDIENTS: ";
        for (int i = 0; i < ingredients.size(); i++) {     //For every existing ingredient
            Ingredient ingredient = ingredients.get(i);
            data = data.concat(ingredient.describe());
            data = data.concat((i < ingredients.size() - 1) ? "," : "");
        }

        //Recipe Steps
        data += "RECIPE STEPS:\n";
        for (RecipeStep step : recipeSteps) {           //For every existing ingredient
            data = data.concat(step.describe() + "\n");
        }

        return data;
    }

    /**
     * Executes Step Internally depending on the type of step. This is accomplished with method overloading.
     */
    void executeStep() {
        getNextStep().executeStep();
    }

    //Utilities

    /**
     * Finds the class name based on the data it receives
     *
     * @param data the extracted text from the file
     * @return the class name found
     */
    private String classNameFinder(String data) {
        switch (data) {
            case "POW":
                return "Powder";
            case "LIQ":
                return "Liquid";
            case "TRANSFER":
                return "TransferStep";
            case "OPERATE":
                return "OperateStep";
            default:
                return data;
        }
    }
}