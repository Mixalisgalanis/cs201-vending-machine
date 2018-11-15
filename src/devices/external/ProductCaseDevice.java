package devices.external;

import behaviour.Lockable;

public interface ProductCaseDevice extends Lockable {

    void getProduct();

    void putMaterial(String type);

    void loadIngredient(String type);
}
