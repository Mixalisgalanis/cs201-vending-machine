package recipes.consumables.ingredients;

import java.util.HashMap;

public class ProcessedIngredient extends Ingredient {

    //Class variables
    private final HashMap<String, Ingredient> ingredients;

    //Constructor
    public ProcessedIngredient(String name) {
        super(name, 0);
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
        setQuantity(getQuantity() + ingredient.getQuantity());
    }

    public String generateName() {
        String name = super.getName() + "<";
        for (Ingredient ingredient : ingredients.values()) {
            name += ingredient.getName() + ",";
        }

        name = name.substring(0, name.length() - 1);
        name += ">";
        return name;
    }
}
