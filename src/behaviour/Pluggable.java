package behaviour;

/**
 * This general behavioural interface describes the function of plugging a provider into a consumer
 * Whichever class implements this interfaces unlocks the skill of plugging itself to other consumers
 */
public interface Pluggable {

    /**
     * Whenever a class implements this interface plugs itself to a consumer and vice-versa.
     * The plugging is done on both ends.
     * @param consumer to be plugged into/from
     */
    void plug(Consumer consumer);

    /**
     * Whenever a class implements this interface unplugs itself from a consumer and vice-versa.
     * The unplugging is done on both ends.
     * @param consumer to be unplugged from/into
     */
    void unPlug(Consumer consumer);

    /**
     * No one knows what this method does
     */
    void unPlugAll();

    /**
     * This method is a getter to the plugged boolean variable found in the class implementing this interface
     * @return the boolean plugged variable itself
     */
    boolean isPlugged();

    /**
     * This method is a setter to the plugged boolean variable found in the class implementing this interface
     */
    void setPlugged(boolean plugged);
}
