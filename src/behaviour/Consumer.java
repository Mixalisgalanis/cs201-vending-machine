package behaviour;

import recipes.consumables.Consumable;

/**
 * This general behavioural interface describes the function of accepting and loading consumables into a consumer
 * Whichever class implements this interfaces unlocks the skill of accepting and loading to itself a desired consumable
 */
public interface Consumer extends Pluggable {

    /**
     * Checks whether to accept or reject a consumable based on quantity & capacity restrictions.
     * If the consumable is accepted, it then loads it to the container
     * @param consumable to be set in the container
     */
    void acceptAndLoad(Consumable consumable);
}
