package behaviour;

/**
 * This general behavioural interface describes the function of providing consumables to a specific Consumer
 * Whichever class implements this interfaces unlocks the skill of providing consumables to other consumers
 */
public interface Provider extends Pluggable {

    /**
     * Provides part of a consumable of the class that's implementing this interface to a specific consumer
     *
     * @param consumer to give part of a consumable
     * @param quantity the quantity of the consumable to provide
     */
    void provide(Consumer consumer, int quantity);

    /**
     * Provides the whole consumable quantity to a specific consumer
     *
     * @param consumer to give the whole consumable
     */
    void provide(Consumer consumer);
}
