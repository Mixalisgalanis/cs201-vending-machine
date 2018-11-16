package behaviour;

import devices.Device;

public interface Lockable extends Device {

    void lock();

    void unLock();

    boolean isLocked();
}
