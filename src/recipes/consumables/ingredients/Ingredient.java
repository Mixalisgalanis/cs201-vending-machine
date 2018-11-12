package recipes.consumables.ingredients;

import recipes.consumables.Consumable;

abstract public class Ingredient extends Consumable{
	
	public Ingredient(String name, int quantity, String ingredientType) {
		super(name, quantity, ingredientType);
	}


	public String describe(){
		return getConsumableType().substring(0, 2).toUpperCase() + ":"
				+ getName().toUpperCase() + ":" +
				getQuantity();
	}

}
