package recipes.consumables.ingredients;

import java.util.HashMap;

public class ProcessedIngredient extends Ingredient {

    //Class variables
    private final HashMap<String, Ingredient> ingredients;

    //Constructor
    public ProcessedIngredient(String name, int quantity, String ingredientType) {
        super(name, quantity, ingredientType);
        ingredients = new HashMap<>();
    }

    //Other Methods
    @Override
    public ProcessedIngredient getPart(int quantity) {
        assert (quantity > 0 && getQuantity() >= quantity);
        setQuantity(getQuantity() - quantity);
        return new ProcessedIngredient(getName(), quantity, getConsumableType());
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
        setQuantity(getQuantity() + ingredient.getQuantity());
    }
}
