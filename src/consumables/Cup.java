package consumables;

public class Cup extends Material {
	private String size;
	public Cup(String name,int quantity,String size) {
		super("Cup",quantity);
		this.size=size;
	}
}
