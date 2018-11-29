package recipes.consumables.ingredients;

import java.util.HashMap;

public class ProcessedIngredient extends Ingredient {

    //Class variables
    private HashMap<String, Ingredient> ingredients;

    //Constructor
    public ProcessedIngredient(int quantity, String ingredientType) {
        super(quantity, ingredientType);
        ingredients = new HashMap<>();
    }

    public ProcessedIngredient(String name, int quantity, String ingredientType) {
        super(name, quantity, ingredientType);
        ingredients = new HashMap<>();
    }

    //Other Methods
    @Override
    public ProcessedIngredient getPart(int quantity) {
        if (quantity > 0 && getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return new ProcessedIngredient(generateName(), quantity, getConsumableType());
        }
        return null;
    }

    public void addIngredients(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
    }

    public String generateName() {
        String name = "";
        for (Ingredient ingredient : ingredients.values()) {
            name = name.concat(ingredient.getName() + "\t");
        }
        return name;
    }

}
