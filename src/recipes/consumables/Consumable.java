package recipes.consumables;

public class Consumable {

    private String name;
    private int quantity;
    private String consumableType;

    public Consumable(String name, int quantity, String consumableType) {
        this.name = name;
        this.quantity = quantity;
        this.consumableType = consumableType;
    }

    public Consumable getPart(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity = this.quantity - quantity;
            return new Consumable(name, quantity, consumableType);
        }
        return null;
    }

    public void refillPart(int containerCapacity, int quantity) {
        this.quantity += ((quantity + this.quantity > containerCapacity) ? (containerCapacity - this.quantity) : quantity);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getConsumableType() {
        return consumableType;
    }
}
