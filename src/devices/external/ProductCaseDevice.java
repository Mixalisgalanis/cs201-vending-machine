package devices.external;

import interfaces.Lockable;

public interface ProductCaseDevice extends Lockable{

	void getProduct();
	
	void putMaterial(String type);
	
	void loadIngredient (String type);
}
