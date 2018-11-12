package recipes.consumables;

public class Consumable {
	
	private String name;
	private int quantity;
	private String consumableType;
	
	public Consumable(String name, int quantity, String consumableType) {
		this.name=name;
		this.quantity=quantity;
		this.consumableType = consumableType;
}
	
	/*public Consumable getPart(int quantity) {}*/


	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getConsumableType() {
		return consumableType;
	}
}
