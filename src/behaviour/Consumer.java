package behaviour;

import recipes.consumables.Consumable;

public interface Consumer extends Pluggable {

    void acceptAndLoad(Consumable consumable);


}
