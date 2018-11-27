package recipes.consumables.ingredients;

public class Powder extends Ingredient {

    public Powder(String name, int quantity) {
        super(name, quantity, "Powder");
    }

    @Override
    public Powder getPart(int quantity) {
        if (quantity > 0 && getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return new Powder(getName(), quantity);
        }
        return null;
    }
}
