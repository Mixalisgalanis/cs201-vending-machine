package recipes.product;

import recipes.consumables.Consumable;
import recipes.consumables.Cup;
import recipes.consumables.ingredients.Ingredient;
import recipes.consumables.ingredients.ProcessedIngredient;

public class ProductBuilder {

    //Class variables
    private Product product;


    //Other Methods
    public void createProduct(String productName) {
        product = new Product(productName);
    }

    public void addConsumable(Consumable consumable) {
        assert product != null;
        if (consumable instanceof ProcessedIngredient) {
            for (Ingredient ingredient : ((ProcessedIngredient) consumable).getIngredients().values()) {
                product.getConsumables().put(ingredient.getName(), ingredient);
            }
        } else if (consumable instanceof Cup) {
            product.setCup((Cup) consumable);
        }
    }

    public Product getProduct() {
        return product;
    }
}