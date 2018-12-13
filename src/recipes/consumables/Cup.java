package recipes.consumables;

public class Cup extends Material {
    private final String size;

    public Cup(String name, int quantity, String size) {
        super(name, quantity, "Cup");
        this.size = size;
    }

    @Override
    public Cup getPart(int quantity) {
        assert (quantity > 0 && getQuantity() >= quantity);
        setQuantity(getQuantity() - quantity);
        return new Cup(getName(), quantity, size);
    }
}
