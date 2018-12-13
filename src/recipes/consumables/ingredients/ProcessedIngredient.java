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

    public ProcessedIngredient(int quantity, String ingredientType) {
        super("", quantity, ingredientType);
        ingredients = new HashMap<>();
    }

    //Other Methods
    @Override
    public ProcessedIngredient getPart(int quantity) {
        assert (quantity > 0 && getQuantity() >= quantity);

        setQuantity(getQuantity() - quantity); //decreases total quantity
        for (Ingredient ingredient : ingredients.values()) { //decreases each ingredient's quantity
            ingredient.setQuantity(ingredient.getQuantity() - (quantity / ingredients.size()));
        }

        ProcessedIngredient newProcessedIngredient = clone(this, quantity);
        for (Ingredient ingredient : newProcessedIngredient.getIngredients().values()) {
            ingredient.setQuantity((quantity / ingredients.size()));
        }
        return newProcessedIngredient;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getName())) {
            ingredients.get(ingredient.getName()).setQuantity(ingredients.get(ingredient.getName()).getQuantity() + ingredient.getQuantity());
        } else {
            ingredients.put(ingredient.getName(), ingredient);
        }
        setQuantity(getQuantity());
    }

    @Override
    public int getQuantity() {
        int quantity = 0;
        for (Ingredient ingredient : ingredients.values()) {
            quantity += ingredient.getQuantity();
        }
        return quantity;
    }


    private ProcessedIngredient clone(ProcessedIngredient oldProcessedIngredient, int quantity) {
        ProcessedIngredient newProcessedIngredient = new ProcessedIngredient(oldProcessedIngredient.getName(), quantity,
                oldProcessedIngredient.getConsumableType());

        for (Ingredient ingredient : oldProcessedIngredient.getIngredients().values()) {
            if (ingredient instanceof Powder) {
                newProcessedIngredient.getIngredients().put(ingredient.getName(), new Powder(ingredient.getName(),
                        ingredient.getQuantity()));
            } else if (ingredient instanceof Liquid) {
                newProcessedIngredient.getIngredients().put(ingredient.getName(), new Liquid(ingredient.getName(),
                        ingredient.getQuantity()));
            }
        }
        return newProcessedIngredient;
    }
}
