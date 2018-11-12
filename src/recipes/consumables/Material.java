package recipes.consumables;

abstract public class Material extends Consumable {
	
	public Material(String name,int quantity) {
		super(name,quantity, "Material");
	}

}