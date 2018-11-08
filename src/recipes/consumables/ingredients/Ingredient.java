package recipes.consumables.ingredients;

import recipes.consumables.Consumable;

abstract public class Ingredient extends Consumable{
	
	public Ingredient(String name, int quantity) {
		super(name, quantity);
	}

}
