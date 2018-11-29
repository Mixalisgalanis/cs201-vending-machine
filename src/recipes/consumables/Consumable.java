package recipes.consumables;

abstract public class Consumable {

    //Class variables
    private String name;
    private int quantity;
    private String consumableType;

    //Constructors
    public Consumable(String name, int quantity, String consumableType) {
        this.name = name;
        this.quantity = quantity;
        this.consumableType = consumableType;
    }

    public Consumable(int quantity, String consumableType) {
        this.quantity = quantity;
        this.consumableType = consumableType;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //Other Methods
    abstract public Consumable getPart(int quantity);

    public void refillPart(int containerCapacity, int quantity) {
        this.quantity += ((quantity + this.quantity > containerCapacity) ? (containerCapacity - this.quantity) : quantity);
    }

    public void refill(int containerCapacity) {
        this.quantity = containerCapacity;
    }


}
