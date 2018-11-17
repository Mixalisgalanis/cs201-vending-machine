package behaviour;

import devices.Device;

/**
 * This general behavioural interface describes the function of locking and unlocking devices
 * Whichever class implements this interfaces unlocks the skill of locking and unlocking itself, usually when it's an
 * external module that needs to prevent access to the user when it doesn't need to
 */
public interface Lockable extends Device {

    /**
     * Locks this specific device to prevent access to the user
     */
    void lock();

    /**
     * Unlocks this specific device to provide access to the user
     */
    void unLock();

    /**
     * This is a getter method to the locked boolean variable found in the class implementing this interface
     *
     * @return the boolean locked variable itself
     */
    boolean isLocked();
}
