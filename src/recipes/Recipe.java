package recipes;

import recipes.consumables.ingredients.Ingredient;
import recipes.step.RecipeStep;
import utilities.Reader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    public Recipe(File file) {
        reader = new Reader();
        recipeStepNumber = 0;
        recipeSteps = new ArrayList<>();
        ingredients = new ArrayList<>();
        disassemble(file);
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
    public void disassemble(File file) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file.");
        }

        try {
            /*Basic recipe properties*/
            String tempLine = bufferedReader.readLine();
            this.name = tempLine.substring(tempLine.indexOf(":") + 1,tempLine.length());
            tempLine = bufferedReader.readLine();
            this.cost = Integer.parseInt(tempLine.substring(tempLine.indexOf(":") + 1,tempLine.length()));
            tempLine = bufferedReader.readLine();
            this.type = tempLine.substring(tempLine.indexOf(":") + 1,tempLine.length());

            /*Ingredients*/
            tempLine = bufferedReader.readLine().substring(tempLine.indexOf(":") + 1,tempLine.length());
            String[] allIngredients = tempLine.split(",");
            for (String ingredient : allIngredients) {
                String[] tempIngredient = ingredient.split(":");

                Class<?> clazz = Class.forName(expandName(tempIngredient[0]));
                Constructor<?> ctor = clazz.getConstructor(String.class, Integer.class, String.class);
                Object object = ctor.newInstance(tempIngredient[1], Integer.parseInt(tempIngredient[2]), expandName(tempIngredient[0]));
                this.ingredients.add((Ingredient) object);
            }

            /*Recipe Steps*/
            tempLine = bufferedReader.readLine();
            while (tempLine != null) {

                Class<?> clazz = Class.forName(expandName(tempLine.substring(0, tempLine.indexOf(" "))));
                Constructor<?> ctor = clazz.getConstructor();
                Object object = ctor.newInstance(new Object[]{tempLine.substring(0, tempLine.indexOf(" ")).split(" ")});

                this.recipeSteps.add((RecipeStep) object);

                tempLine = bufferedReader.readLine();
            }


        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    /**
     * Converts a working recipe into a recipe text
     */
    public void assemble() {
        File file = new File("/recipes/" + code + ".rcp");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            System.err.println("Unable to create file.");
            return;
        }
        try {
            /*Basic recipe properties*/
            String tempLine = "NAME: " + getName() + "\n";
            fileWriter.write(tempLine);
            tempLine = "PRICE: " + getCost() + "\n";
            fileWriter.write(tempLine);
            tempLine = "TYPE: " + getType() + "\n";
            fileWriter.write(tempLine);

            /*Ingredients*/
            tempLine = "INGREDIENTS: ";
            for (Ingredient ingredient : ingredients) {
                tempLine = ingredient.describe() + ",";
            }
            tempLine = tempLine.substring(0, tempLine.length() - 1) + "\n";
            fileWriter.write(tempLine);

            /*Recipe Steps*/
            tempLine = "RECIPE STEPS:\n";
            for (RecipeStep step : recipeSteps) {
                tempLine = tempLine.concat(step.describe()) + "\n";
            }
        } catch (IOException e) {
            System.err.println("Unable to write file");
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Unable to save file");
        }
    }

    public void executeStep() {
        getNextStep();

    }

    //Names are a work in progress ._.
    public String expandName(String abbreviatedName) {
        switch (abbreviatedName) {
            case "POW":
                return "Powder";
            case "LIQ":
                return "Liquid";
            case "TRANSFER":
                return "TransferStep";
            case "OPERATE":
                return "OperateStep";
            default:
                return abbreviatedName;
        }
    }
}