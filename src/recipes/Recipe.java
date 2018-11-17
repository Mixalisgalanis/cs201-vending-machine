package recipes;

import recipes.consumables.ingredients.Ingredient;
import recipes.step.RecipeStep;
import utilities.Reader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Recipe {

    //Class variables

    //Basic variable types
    private String name;
    private int code;
    private int price;
    private String type;
    private boolean available;

    //Ingredients
    private ArrayList<Ingredient> ingredients;

    //Steps
    private ArrayList<RecipeStep> recipeSteps;
    private int currentRecipeStepNumber;

    //Utilities
    private Reader reader;

    //Constructors
    public Recipe(String name, int price, int cost, String type) { //Standard Constructor
        this.name = name;
        this.code = price;
        this.price = cost;
        this.type = type;
        this.available = false;

        reader = new Reader();
        currentRecipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    /**
     * In this modified constructor, the recipe gets constructed step-by-step by the disassemble method.
     *
     * @param file in disk to disassemble.
     * @param code required for the code basic variable type.
     */
    public Recipe(File file, int code) {
        reader = new Reader();
        currentRecipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
        this.code = code;
        disassemble(file);
    }


    //Getter & Setters
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public RecipeStep getCurrentRecipeStep() {
        return recipeSteps.get(currentRecipeStepNumber);
    }
    //Other Methods

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
    public boolean hasMoreSteps() {
        return (currentRecipeStepNumber < recipeSteps.size() - 1);
    }

    /**
     * Makes this particular recipe available
     */
    public void enable() {
        this.available = true;
    }

    /**
     * Makes this particular recipe not available.
     */
    public void disable() {
        this.available = false;
    }

    /**
     * Converts a recipe text into a working recipe by extracting text information.
     *
     * @param file the required .rcp file to extract information from.
     */
    public void disassemble(File file) {
        //Required Readers to access and read from the file
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        //Extracting information from file
        try {
            //Basic recipe properties
            String tempLine = bufferedReader.readLine();            //Proceeds to next line
            this.name = tempLine.substring(tempLine.indexOf(":") + 2, tempLine.length()); //Extracts name
            tempLine = bufferedReader.readLine();                   //Proceeds to next line
            this.price = Integer.parseInt(tempLine.substring(tempLine.indexOf(":") + 2, tempLine.length())); //Extracts price
            tempLine = bufferedReader.readLine();                   //Proceeds to next line
            this.type = tempLine.substring(tempLine.indexOf(":") + 2, tempLine.length()); //Extracts type

            //Ingredients - ex: "INGREDIENTS: POW:COFFEE:40,POW:SUGAR:80,LIQ:WATER:100"
            tempLine = bufferedReader.readLine();
            String ingredientsLine = tempLine.substring(tempLine.indexOf(":") + 2, tempLine.length()); //ex: "POW:COFFEE:40,POW:SUGAR:80,LIQ:WATER:100"
            String[] allIngredients = ingredientsLine.split(",");      //ex: allIngredients[0] == POW:COFFEE:40
            for (String ingredient : allIngredients) {
                String[] tempIngredient = ingredient.split(":");//ex: tempIngredient[0] == POW

                //Creating Liquid or Powder Object based on the ingredient name in file
                String className = classNameFinder(tempIngredient[0]);   //Gets the class name based on the ingredient name
                Class<?> clazz = Class.forName("recipes.consumables.ingredients." + className);          //Finds the class based on the class name
                Constructor<?> ctor = clazz.getConstructors()[0]; //Calls class constructor with given parameters
                Object object = ctor.newInstance(toCamelCase(tempIngredient[1]), Integer.parseInt(tempIngredient[2])); //Creates object from that constructor
                this.ingredients.add((Ingredient) object);          //Adds the object (Ingredient) on the ingredients list
            }

            //Recipe Steps
            //Proceeds to next line recursively until the end of the file
            tempLine = bufferedReader.readLine();
            tempLine = bufferedReader.readLine();
            do {
                //Creating TransferStep or OperateStep based on the step name in file
                Class<?> clazz = Class.forName("recipes.step." + classNameFinder(tempLine.substring(0, tempLine.indexOf(" ")))); //Gets the class name based on the step name
                Constructor<?> ctor = clazz.getConstructors()[1];                //Finds the class based on the class name
                //Needed to collect all strings in an array in order to achieve equal constructor structure between the two Step Types
                String[] data = tempLine.substring(tempLine.indexOf(" ") + 1, tempLine.lastIndexOf(" ")).split(" ");
                int a = Integer.parseInt(tempLine.substring(tempLine.lastIndexOf(" ") + 1, tempLine.length()));
                Object object = ctor.newInstance(new Object[]{data, a}); //Creates object from that constructor

                this.recipeSteps.add((RecipeStep) object); //Adds the object (Step) on the ingredients list
                tempLine = bufferedReader.readLine();
            } while (tempLine != null);
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a working recipe into a recipe text by building an .rcp file.
     */
    public void assemble() {
        File file = new File("/recipes/" + code + ".rcp"); //Creates rcp recipe file named based on recipe code
        FileWriter fileWriter; //Required Writers to access and read from the file
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            //Basic recipe properties
            String tempLine = "NAME: " + getName() + "\n";  //Creating name line
            fileWriter.write(tempLine);                     //Writes this line
            tempLine = "PRICE: " + getPrice() + "\n";       //Creating price line
            fileWriter.write(tempLine);                     //Writes this line
            tempLine = "TYPE: " + getType() + "\n";         //Creating type line
            fileWriter.write(tempLine);                     //Writes this line

            //Ingredients
            //Building step by step the ingredients line
            tempLine = "INGREDIENTS: ";
            for (Ingredient ingredient : ingredients) {     //For every existing ingredient
                tempLine = ingredient.describe() + ",";
            }
            tempLine = tempLine.substring(0, tempLine.length() - 1) + "\n";
            fileWriter.write(tempLine);                     //Writes this line

            //Recipe Steps
            tempLine = "RECIPE STEPS:\n";
            for (RecipeStep step : recipeSteps) {           //For every existing ingredient
                tempLine = tempLine.concat(step.describe()) + "\n";
            }

            //Applying the changes to file and closing the stream
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Executes Step Internally depending on the type of step. This is accomplished with method overloading.
     */
    public void executeStep() {
        getNextStep().executeStep();
    }

    //Utilities

    /**
     * Finds the class name based on the data it receives
     *
     * @param data the extracted text from the file
     * @return the class name found
     */
    public String classNameFinder(String data) {
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

    /**
     * Converts a String to Camel Case Format.
     *
     * @param input String to be converted.
     * @return the Camel Case String.
     */
    public String toCamelCase(String input) {
        String temp = input.toLowerCase();
        return (temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()));
    }
}