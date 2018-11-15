package modules.external;

import behaviour.Consumer;
import modules.Module;
import recipes.consumables.Consumable;

public class ProductCase extends Module implements Consumer {

    public ProductCase() {
        super("Product Case");
    }

	/*public Product getProduct() {
	}*/

    @Override
    public void acceptAndLoad(Consumable consumable) {

    }
}
