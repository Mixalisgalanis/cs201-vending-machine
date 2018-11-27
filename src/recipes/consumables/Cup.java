package recipes.consumables;

public class Cup extends Material {
    private String size;

    public Cup(String name, int quantity, String size) {
        super("Cup", quantity);
        this.size = size;
    }

    @Override
    public Cup getPart(int quantity) {
        if (quantity > 0 && getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return new Cup(getName(), quantity, size);
        }
        return null;
    }
}
