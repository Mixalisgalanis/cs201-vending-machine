package behaviour;

import recipes.consumables.Consumable;

public interface Consumer {

    void load(Consumable consumable);

    boolean accepts(Consumable consumable);
}
