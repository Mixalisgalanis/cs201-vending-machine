package recipes.consumables.ingredients;

public class Liquid extends Ingredient {

    public Liquid(String name, int quantity) {
        super(name, quantity, "Liquid");
    }

    @Override
    public Liquid getPart(int quantity) {
        if (quantity > 0 && getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return new Liquid(getName(), quantity);
        }
        return null;
    }
}
