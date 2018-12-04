package recipes.consumables.ingredients;

import recipes.consumables.Consumable;

abstract public class Ingredient extends Consumable {

    //Constructors
    public Ingredient(String name, int quantity, String ingredientType) {
        super(name, quantity, ingredientType);
    }

    public Ingredient(String name, int quantity ) {
        super(name, quantity);
    }


    //Other Methods

    /**
     * Creates a string which describes this ingredient - ex: "POW:COFFEE:40"
     *
     * @return the String created
     */
    public String describe() {
        return getConsumableType().substring(0, 2).toUpperCase() + ":"
                + getName().toUpperCase() + ":" +
                getQuantity();
    }

}
