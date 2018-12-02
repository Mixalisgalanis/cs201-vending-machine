package recipes.consumables;

abstract public class Material extends Consumable {

    public Material(String name, int quantity, String consumableType) {
        super(name, quantity, consumableType);
    }

}